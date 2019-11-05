package gg.warcraft.monolith.app.core;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import gg.warcraft.monolith.api.core.PersistenceCache;
import gg.warcraft.monolith.api.core.PersistenceService;

import java.util.*;

@Singleton
public class InMemoryPersistenceService implements PersistenceService {
    private final PersistenceCache persistenceCache;
    private final Map<String, Map<String, String>> maps = new HashMap<>();
    private final Map<String, List<String>> lists = new HashMap<>();
    private final Map<String, Set<String>> sets = new HashMap<>();

    @Inject
    public InMemoryPersistenceService(PersistenceCache persistenceCache) {
        this.persistenceCache = persistenceCache;
    }

    @Override
    public String get(String key) {
        return persistenceCache.get(key);
    }

    @Override
    public void set(String key, String value) {
        persistenceCache.save(key, value);
    }

    @Override
    public void delete(String key) {
        persistenceCache.delete(key);
    }

    @Override
    public List<String> getList(String key) {
        return lists.getOrDefault(key, new ArrayList<>());
    }

    @Override
    public void setList(String key, List<String> values) {
        lists.put(key, values);
    }

    @Override
    public void pushList(String key, String value) {
        List list = lists.getOrDefault(key, new ArrayList<>());
        list.add(value);
        lists.put(key, list);
    }

    @Override
    public Map<String, String> getMap(String key) {
        return maps.getOrDefault(key, new HashMap<>());
    }

    @Override
    public void setMap(String key, Map<String, String> values) {
        maps.put(key, values);
    }

    @Override
    public void removeMap(String key, List<String> fields) {
        maps.remove(key);
    }

    @Override
    public Set<String> getSet(String key) {
        return sets.getOrDefault(key, new HashSet<>());
    }

    @Override
    public void addSet(String key, List<String> values) {
        Set set = sets.getOrDefault(key, new HashSet<>());
        set.addAll(values);
        sets.put(key, set);
    }

    @Override
    public void removeSet(String key, List<String> values) {
        Set set = sets.getOrDefault(key, new HashSet<>());
        set.removeAll(values);
        sets.put(key, set);
    }

    @Override
    public Map<String, Double> getSortedSet(String key) {
        return new HashMap<>();
    }

    @Override
    public Map<String, Double> getSortedSet(String key, int start, int end) {
        return new HashMap<>();
    }

    @Override
    public void setSortedSet(String key, String field, double value) {

    }

    @Override
    public void incrSortedSet(String key, String field, double amount) {

    }

    @Override
    public void decrSortedSet(String key, String field, double amount) {

    }

    @Override
    public void removeSortedSet(String key, String field) {

    }

    @Override
    public List<String> getAllKeys(String keyPrefix) {
        return new ArrayList<>();
    }
}
