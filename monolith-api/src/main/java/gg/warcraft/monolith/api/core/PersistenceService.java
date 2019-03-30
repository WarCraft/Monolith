package gg.warcraft.monolith.api.core;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * This service is injectable.
 * <p>
 * The PersistenceService serves as a point of entry into the persistence module implementation. It provides methods to
 * get and set data in the key-value/document store.
 * <p>
 * All operations exposed by this service are heavily inspired by the Redis API (as such Redis is also the recommended
 * implementation), but to make adding your own implementation as simple as possible Monolith only relies on the {@code
 * get}, {@code set}, and {@code delete} operations. Other operations could throw an {@code
 * UnsupportedOperationException}.
 */
public interface PersistenceService {

    /**
     * @param key The key of the value to retrieve. Can not be null or empty.
     * @return The value. Can be null or empty.
     */
    String get(String key);

    /**
     * @param key   The key to set the value at. Can not be null or empty.
     * @param value The value. Can be null or empty.
     */
    void set(String key, String value);

    /**
     * @param key The key to delete any associated value or value map for. Can not be null or empty.
     */
    void delete(String key);

    /**
     * @param key The key of the value list to retrieve. Can not be null or empty.
     * @return The value list. Can be null or empty.
     */
    List<String> getList(String key);

    /**
     * Pushes a value onto the list at key. Care should be taken that this method will overwrite the existing list in
     * its entirety with the new values provided.
     *
     * @param key    The key to set the value list at. Can not be null or empty.
     * @param values The value list. Can be null or empty.
     */
    void setList(String key, List<String> values);

    /**
     * @param key   The key of the list to push the value onto. Can not be null or empty.
     * @param value The value. Can be null or empty.
     */
    void pushList(String key, String value);

    /**
     * @param key The key of the value map to retrieve. Can not be null or empty.
     * @return The value map. Can be null or empty.
     */
    Map<String, String> getMap(String key);

    /**
     * @param key    The key to set the value map at. Can not be null or empty.
     * @param values The value map. Can not be null, but can be empty.
     */
    void setMap(String key, Map<String, String> values);

    /**
     * @param key    The key of the map to remove the values from. Can not be null or empty.
     * @param fields The fields. Can not be null, but can be empty.
     */
    void removeMap(String key, List<String> fields);

    /**
     * @param key The key of the value set to retrieve. Can not be null or empty.
     * @return The value set. Can be null or empty.
     */
    Set<String> getSet(String key);

    /**
     * @param key    The key of the value set to add the values to. Can not be null or empty.
     * @param values The values to add to the set. Can be null or empty.
     */
    void addSet(String key, List<String> values);

    /**
     * @param key    The key of the value set to remove the values from. Can not be null or empty.
     * @param values The values to remove from the set. Can be null or empty.
     */
    void removeSet(String key, List<String> values);

    Map<String, Double> getSortedSet(String key);

    Map<String, Double> getSortedSet(String key, int start, int end);

    void setSortedSet(String key, String field, double value);

    void incrSortedSet(String key, String field, double amount);

    void decrSortedSet(String key, String field, double amount);

    void removeSortedSet(String key, String field);

    /**
     * @param keyPrefix The prefix. Can not be null or empty.
     * @return All keys matching the prefix. Never null, but can be empty. Items are never null or empty.
     */
    List<String> getAllKeys(String keyPrefix);
}
