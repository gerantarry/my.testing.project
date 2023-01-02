package utils;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

final public class FileUtils {

    private FileUtils(){

    }

    public static final String URLS_FILE_PATH = "src/test/resources/url.properties";

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
