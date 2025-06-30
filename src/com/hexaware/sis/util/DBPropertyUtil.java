package com.hexaware.sis.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DBPropertyUtil {

    public static Properties loadProperties(String filename) {
        Properties props = new Properties();
        try (InputStream input = DBPropertyUtil.class.getClassLoader().getResourceAsStream(filename)) {
            if (input == null) {
                System.out.println("❌ File not found in classpath: " + filename);
                return props;
            }
            props.load(input);
        } catch (IOException e) {
            System.out.println("❌ Error reading properties file: " + e.getMessage());
        }
        return props;
    }
}
