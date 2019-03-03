package gg.warcraft.monolith.app.core;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import gg.warcraft.monolith.api.core.PersistenceCache;
import gg.warcraft.monolith.api.core.PersistenceService;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;
import redis.clients.jedis.Tuple;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.function.Consumer;
import java.util.function.Function;

@Singleton
public class JedisPersistenceService implements PersistenceService {
    private final JedisPool pool;
    private final PersistenceCache persistenceCache;

    @Inject
    public JedisPersistenceService(JedisPool pool, PersistenceCache persistenceCache) {
        this.pool = pool;
        this.persistenceCache = persistenceCache;
    }

    private void retry(Consumer<Jedis> action) {
        try (Jedis jedis = pool.getResource()) {
            action.accept(jedis);
        } catch (Exception ex) {
            retry(action);
        }
    }

    private <T> T retryWithResult(Function<Jedis, T> action) {
        try (Jedis jedis = pool.getResource()) {
            return action.apply(jedis);
        } catch (Exception ex) {
            return retryWithResult(action);
        }
    }

    @Override
    public String get(String key) {
        String result = persistenceCache.get(key);
        if (result == null) {
            result = retryWithResult(jedis -> jedis.get(key));
            persistenceCache.save(key, result);
        }
        return result;
    }

    @Override
    public void set(String key, String value) {
        retry(jedis -> jedis.set(key, value));
        persistenceCache.save(key, value);
    }

    @Override
    public void delete(String key) {
        retry(jedis -> jedis.del(key));
        persistenceCache.delete(key);
    }

    @Override
    public List<String> getList(String key) {
        return retryWithResult(jedis -> jedis.lrange(key, 0, Integer.MAX_VALUE));
    }

    @Override
    public void setList(String key, List<String> values) {
        retry(jedis -> jedis.del(key));
        if (!values.isEmpty()) {
            retry(jedis -> jedis.lpush(key, values.toArray(new String[0])));
        }
    }

    @Override
    public void pushList(String key, String value) {
        retry(jedis -> jedis.lpush(key, value));
    }

    @Override
    public Map<String, String> getMap(String key) {
        return retryWithResult(jedis -> jedis.hgetAll(key));
    }

    @Override
    public void setMap(String key, Map<String, String> values) {
        retry(jedis -> values.forEach((field, value) -> jedis.hset(key, field, value)));
    }

    @Override
    public void removeMap(String key, List<String> fields) {
        retry(jedis -> jedis.hdel(key, fields.toArray(new String[0])));
    }

    @Override
    public Set<String> getSet(String key) {
        return retryWithResult(jedis -> jedis.smembers(key));
    }

    @Override
    public void addSet(String key, List<String> values) {
        if (!values.isEmpty()) {
            retry(jedis -> jedis.sadd(key, values.toArray(new String[0])));
        }
    }

    @Override
    public void removeSet(String key, List<String> values) {
        if (!values.isEmpty()) {
            retry(jedis -> jedis.srem(key, values.toArray(new String[0])));
        }
    }

    @Override
    public SortedMap<String, Double> getSortedSet(String key) {
        int size = retryWithResult(jedis -> jedis.zcard(key).intValue());
        return getSortedSet(key, 0, size);
    }

    @Override
    public SortedMap<String, Double> getSortedSet(String key, int start, int end) {
        Set<Tuple> result = retryWithResult(jedis -> jedis.zrevrangeWithScores(key, start, end));
        SortedMap<String, Double> resultAsMap = new TreeMap<>();
        result.forEach(tuple -> resultAsMap.put(tuple.getElement(), tuple.getScore()));
        return resultAsMap;
    }

    @Override
    public void setSortedSet(String key, String field, double value) {
        retry(jedis -> jedis.zadd(key, value, field));
    }

    @Override
    public void incrSortedSet(String key, String field, double amount) {
        retry(jedis -> jedis.zadd(key, amount, field));
    }

    @Override
    public void decrSortedSet(String key, String field, double amount) {
        retry(jedis -> jedis.zadd(key, -amount, field));
    }

    @Override
    public void removeSortedSet(String key, String field) {
        retry(jedis -> jedis.zrem(key, field));
    }

    @Override
    public List<String> getAllKeys(String keyPrefix) {
        try (Jedis jedis = pool.getResource()) {
            List<String> keys = new ArrayList<>();

            ScanParams scanParams = new ScanParams().match(keyPrefix + "*");
            String cur = ScanParams.SCAN_POINTER_START;
            do {
                ScanResult<String> scanResult = jedis.scan(cur, scanParams);

                keys.addAll(scanResult.getResult());
                cur = scanResult.getStringCursor();
            } while (!cur.equals(ScanParams.SCAN_POINTER_START));
            return keys;
        }
    }
}
