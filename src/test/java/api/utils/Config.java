package api.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {

    public static String get(String keyName) {
        String env = System.getenv(keyName);
        if (env != null && !env.isEmpty()) return env;

        Properties props = new Properties();
        try (InputStream input = new FileInputStream("src/test/resources/config.properties")) {
            props.load(input);
            return props.getProperty(keyName);
        } catch (IOException e) {
            throw new RuntimeException("Błąd ładowania konfiguracji: " + keyName);
        }
    }
}
