package gg.warcraft.monolith.app.core;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import gg.warcraft.monolith.api.core.PersistenceCache;
import gg.warcraft.monolith.api.core.PersistenceService;

import java.util.*;

@Singleton
public class InMemoryPersistenceService implements PersistenceService {
    private final PersistenceCache persistenceCache;

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
        return new ArrayList<>();
    }

    @Override
    public void setList(String key, List<String> values) {

    }

    @Override
    public void pushList(String key, String value) {
    }

    @Override
    public Map<String, String> getMap(String key) {
        return new HashMap<>();
    }

    @Override
    public void setMap(String key, Map<String, String> values) {
    }

    @Override
    public void removeMap(String key, List<String> fields) {

    }

    @Override
    public Set<String> getSet(String key) {
        return new HashSet<>();
    }

    @Override
    public void addSet(String key, List<String> values) {
    }

    @Override
    public void removeSet(String key, List<String> values) {

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
