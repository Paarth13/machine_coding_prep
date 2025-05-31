package repository;

import exceptions.InvalidKeyEntered;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryPendencyRepo implements IPendencyRepository{
// Concurrent hashmaps used for thread safety
    ConcurrentHashMap<Integer,List<String>> idHeirarchyMap;
    ConcurrentHashMap<String, Integer> heirarchyCountMap;

    public InMemoryPendencyRepo() {
        this.heirarchyCountMap = new ConcurrentHashMap<>();
        this.idHeirarchyMap = new ConcurrentHashMap<>();
    }

    @Override
    public int getCount(List<String> heirarchy) {
        String key = "";
        for(String s:heirarchy)
        {
            key+=s;
        }
        return heirarchyCountMap.getOrDefault(key,0);
    }

    @Override
    public void startTracking(Integer id, List<String> heirarchy) {
        idHeirarchyMap.put(id,heirarchy);
        String key = "";
        for(String s:heirarchy)
        {
            key+=s;
            heirarchyCountMap.put(key,heirarchyCountMap.getOrDefault(key,0)+1);
        }
    }

    @Override
    public void endTracking(Integer id) {
        if(!idHeirarchyMap.containsKey(id))
        {
            throw new InvalidKeyEntered("Invalid id inserted");
        }
        List<String> heirarchy = idHeirarchyMap.get(id);
        String key = "";
        for(String s:heirarchy)
        {
            key+=s;
            if(heirarchyCountMap.containsKey(key)) {
                if(heirarchyCountMap.get(key)-1 == 0) {
                    heirarchyCountMap.remove(key);
                }
                else
                {
                    heirarchyCountMap.put(key,heirarchyCountMap.get(key)-1);
                }
            }
        }

        idHeirarchyMap.remove(id);
    }
}
