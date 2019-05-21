/*
 * Copyright (c) 2018 Chris Robinson. All rights reserved.
 */

package bot.configuration;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {

    private Properties prop;
    private String guildId;
    private String commandPrefix;

    public ConfigManager() {
        this.prop = new Properties();
        InputStream input = null;
        String filename = "config.properties";

        try {
            input = getClass().getClassLoader().getResourceAsStream(filename);
            if(input==null) {
                System.out.println("Sorry, unable to find " + filename);
                return;
            }
            //load a properties file from class path, inside static method
            prop.load(input);
            this.guildId = getProperty("guildID");
            this.commandPrefix = getProperty("commandPrefix");
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally{
            if(input!=null){
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private String getProperty(String key) {
        return this.prop.getProperty(key);
    }

    public String getGuildId() {
        return guildId;
    }

    public String getCommandPrefix() {
        return commandPrefix;
    }

    public String getBotToken() {
        return "NTgwNTAxNjI4MTM4ODE1NDk4.XORoSA.G4sMzzS5omIyV9Z6h2xjNLzy8Ms";
    }
}
