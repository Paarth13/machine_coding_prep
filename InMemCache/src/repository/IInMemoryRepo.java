package repository;

import dto.KeyValueMap;

import java.util.List;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public interface IInMemoryRepo {
    public  ConcurrentHashMap<String, KeyValueMap> getMap();
    public List<String> getAllKeys();
    public void put(String key, Map<String, Object> parameters);
    public void deleteKey(String key);
    public String getAttributeValue(String key);
}
