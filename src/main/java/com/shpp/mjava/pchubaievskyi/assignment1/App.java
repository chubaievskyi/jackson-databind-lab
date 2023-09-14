package com.shpp.mjava.pchubaievskyi.assignment1;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class App {
    public static final Logger LOGGER = LoggerFactory.getLogger(App.class);
//    private static final String JSON_FORMAT = "json";
    private static final String XML_FORMAT = "xml";
    private static final String OUTPUT_FORMAT = "outputFormat";

    public static void main(String[] args) {

        LOGGER.info("Just a log message.");
        LOGGER.debug("Message for debug level.");

        if (args.length == 0) {
            LOGGER.error("External properties file path is missing.");
            return;
        }
        String outputFormat = System.getProperty(OUTPUT_FORMAT);

        String externalPropertiesPath = args[0];
        Properties externalProperties = readProperties(externalPropertiesPath);
        String username = externalProperties.getProperty("username");
        String message = "Hello " + username;

        ObjectMapper objectMapper = XML_FORMAT.equals(outputFormat) ? new XmlMapper() : new ObjectMapper();
        try {
            writeMessage(message, objectMapper);
        } catch (JsonProcessingException e) {
            LOGGER.error("JsonProcessing failed", e);
        }
    }


    private static Properties readProperties(String filePath) {
        Properties properties = new Properties();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath),
                StandardCharsets.UTF_8))) {
            properties.load(reader);
        } catch (IOException e) {
            LOGGER.error("Failed to read properties from file.", e);
        }
        return properties;
    }

    private static void writeMessage(String message, ObjectMapper objectMapper) throws JsonProcessingException {
        String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(new Message(message));
        LOGGER.info(json);

    }
}
