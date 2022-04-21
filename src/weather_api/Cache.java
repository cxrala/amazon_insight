package weather_api;

import java.util.*;

public class Cache {
    private Map<String, Pair> cache;
    int size;
    Queue<String> cities;
    long millis;

    public Cache(int size) {
        this.cache = new HashMap<>();
        this.cities = new LinkedList<>();
        this.size = size;
        this.millis = 10000;
    }

    public void put(String key, String value) {
        if (cache.containsKey(key)) {
            cache.put(key, new Pair(value));
            return;
        }
        if (cache.size() >= size) {
            String poppedCity = cities.poll();
            cache.remove(poppedCity);
        }
        cache.put(key, new Pair(value));
        cities.add(key);
    }

    public String get(String key) {
        cities.remove(key);
        cities.add(key);
        return cache.get(key).getTemp();
    }

    public boolean cacheContainsKey(String key) {
        if (cache.containsKey(key)) {
            Date current = new Date();
            long difference = current.getTime() - cache.get(key).getDate().getTime();
            if (difference > millis) {
                return false;
            }
        }

        return cache.containsKey(key);
    }

}
