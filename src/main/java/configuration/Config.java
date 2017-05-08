package configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by Kozak on 08.05.2017.
 */
public class Config {
    public static final Properties prop = new Properties();
    static{
        try {
            prop.load(new FileInputStream("config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
