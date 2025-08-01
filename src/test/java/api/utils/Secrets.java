package api.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;

public class Secrets {

    private static final HashMap<String, String> cache = new HashMap<>();
    private static final Properties props = new Properties();

    static {
        try (InputStream input = new FileInputStream("src/test/resources/config.properties")) {
            props.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Błąd ładowania sekretu: ", e);
        }
    }

    public static String get(String keyName) {
        if (cache.containsKey(keyName)) return cache.get(keyName);

        String env = System.getenv(keyName);
        if (env != null && !env.isEmpty()) {
            cache.put(keyName, env);
            return env;
        }

        String value = props.getProperty(keyName);
        if (value != null && !value.isEmpty()) {
            cache.put(keyName, value);
            return value;
        }

        throw new RuntimeException("Nie udało się załadować sekretu: " + keyName);
    }
}
