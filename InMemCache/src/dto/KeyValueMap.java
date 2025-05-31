package dto;

import java.util.Map;

public class KeyValueMap {
    String key;

    public KeyValueMap(String key, Map<String, Object> attributes) {
        this.key = key;
        this.attributes = attributes;
    }

    public String getKey() {
        return key;
    }

    @Override
    public String toString() {
        StringBuilder stb = new StringBuilder();
        for (Map.Entry<String,Object> entry : this.attributes.entrySet()) {
            stb.append(entry.getKey()).append(": ")
                    .append(entry.getValue()).append(", ");
        }
        return stb.toString();
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    Map<String, Object> attributes;
}
