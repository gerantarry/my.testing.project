package utils;


import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

final public class FileUtils {

    private FileUtils(){

    }

    public static final String TEST_RESOURCES_ROOT_PATH = "src/test/resources/";
    public static final String TEST_REQUESTS_PATH = TEST_RESOURCES_ROOT_PATH + "request/";
    public static final String URLS_FILE_PATH = TEST_RESOURCES_ROOT_PATH + "url.properties";

    /**
     *
     * @param fileName without root context
     * @param classType for transformation
     * @return T class
     */
    public static <T> T readJsonFile(String fileName, Class<T> classType) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
       return mapper.readValue(new File(fileName), classType);
    }

    public static Properties readPropertiesFile(final String path){
        try (InputStream input = new FileInputStream(path)){
            Properties properties = new Properties();
            properties.load(input);

            return properties;

        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
}
