package http;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Headers {

    private Map<String, String> headers;

    public Headers () {
        this.headers = new HashMap<>();
    }

    public void put(String key, String value) {
        headers.put(key, value);
    }
    
    public void put(String str) {
        String[] split = str.split(": ");
        put(split[0], split[1]);
    }

    public String toString() {
        return headers.keySet()
                .stream()
                .map(key -> String.format("%s: %s ", key, headers.get(key)))
                .collect(Collectors.joining("\r\n"));
    }
}
