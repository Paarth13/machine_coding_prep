package service;

import dto.KeyValueMap;
import javafx.util.Pair;
import repository.IInMemoryRepo;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class InMemoryService {
    IInMemoryRepo memoryRepo;
    ISchemaRegistryService schemaRegistryService;

    public InMemoryService(IInMemoryRepo memoryRepo) {
        this.memoryRepo = memoryRepo;
        this.schemaRegistryService =  new SchemaRegistryService();
    }

    public String getAllKeys() {
        List<String> keys = memoryRepo.getAllKeys();
        if(keys.isEmpty())
            return "No keys present";
        return keys.toString();
    }


    public String put(String key, List<Pair<String, String>> listOfAttributePairs) {
        if(!memoryRepo.getAllKeys().isEmpty() && schemaRegistryService.validateSchema(listOfAttributePairs))
        {
            return "Data Type Error";
        }
        Map<String,Object> attributesMap = listOfAttributePairs.stream()
                .collect(Collectors.toMap(pair -> pair.getKey(), pair->pair.getValue()));;
        schemaRegistryService.registerSchema(listOfAttributePairs);
        if (memoryRepo.getMap().get(key)==null)
        {
            memoryRepo.put(key,attributesMap);
        }else {
            memoryRepo.getMap().get(key).setAttributes(attributesMap);
        }
        return "";
    }


    public void deleteKey(String key) {
        memoryRepo.deleteKey(key);
    }

    public String getAttributeValue(String key) {
        return "";
    }
}
