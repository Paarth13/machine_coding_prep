package repository;

import dto.KeyValueMap;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryRepository implements IInMemoryRepo{
    ConcurrentHashMap<String, KeyValueMap> memCache;

    public InMemoryRepository() {
        this.memCache = new ConcurrentHashMap<>();
    }

    public  ConcurrentHashMap<String, KeyValueMap> getMap()
    {
        return memCache;
    }
    @Override
    public List<String> getAllKeys() {
        return memCache.keySet().stream().toList();
    }

    @Override
    public void put(String key, Map<String, Object> parameters) {
        KeyValueMap mp = new KeyValueMap(key,parameters);
        memCache.put(key,mp);
    }

    @Override
    public void deleteKey(String key) {
        memCache.remove(key);
    }

    @Override
    public String getAttributeValue(String key) {
        return "";
    }

    private String ToString(HashMap<String,Type> map)
    {
        StringBuilder sb = new StringBuilder();
        map.forEach(
                (key,value)-> sb.append(key).append(" = ").append(value).append(", "));
        return sb.toString();
    }
}
