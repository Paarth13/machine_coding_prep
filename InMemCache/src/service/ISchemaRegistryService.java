package service;

import javafx.util.Pair;

import java.util.List;

public interface ISchemaRegistryService {
    public void registerSchema(List<Pair<String, String>> listOfAttributePairs);

    public boolean validateSchema(List<Pair<String, String>> listOfAttributePairs);
}
