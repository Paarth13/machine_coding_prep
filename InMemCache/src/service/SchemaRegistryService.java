package service;

import repository.SchemaRegistryRepo;
import utils.DataTypeCheckerUtil;

import java.security.KeyPair;
import java.util.List;
import java.util.Map;
import javafx.util.Pair;

public class SchemaRegistryService implements ISchemaRegistryService{
    Map<String,String> schemaRegistryRepository= SchemaRegistryRepo.getKeyDataTypeMap();

    @Override
    public void registerSchema(List<Pair<String, String>> listOfAttributePairs) {
        listOfAttributePairs.forEach(pair->
                schemaRegistryRepository.put(pair.getKey(), DataTypeCheckerUtil.validateType(pair.getValue()))
        );
    }

    @Override
    public boolean validateSchema(List<Pair<String, String>> listOfAttributePairs) {
        Object isInvalid= listOfAttributePairs.stream().filter(pair->
                !schemaRegistryRepository.get(pair.getKey()).equals(DataTypeCheckerUtil.validateType(pair.getValue()))
        ).findFirst().orElse(null);

        return isInvalid == null;
    }
}
