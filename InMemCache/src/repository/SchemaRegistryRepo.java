package repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SchemaRegistryRepo {
    public SchemaRegistryRepo() {
        keyDataTypeMap=new ConcurrentHashMap<>();
    }

    public static Map<String, String> getKeyDataTypeMap() {
        return keyDataTypeMap;
    }

    public static void setKeyDataTypeMap(Map<String, String> keyDataTypeMap) {
        SchemaRegistryRepo.keyDataTypeMap = keyDataTypeMap;
    }

    static Map<String,String> keyDataTypeMap;
}
