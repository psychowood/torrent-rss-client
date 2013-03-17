package ar.com.kache;

import java.util.ArrayList;
import java.util.List;

import ar.com.kache.config.AppConfiguration;
import ar.com.kache.config.ConfigFeed;
import ar.com.kache.config.Configuration;


public class Main {
	
    /**
     * @param args
     */
    public static void main(String[] args) {
    	boolean install = false;
    	if (args.length > 0) {
    		if ("install".equals(args[0])) {
    			install = true;
    		}
    	}
    	
    	if (install) {
    		if (!AppConfiguration.appHome.isDirectory()) {
				AppConfiguration.appHome.mkdirs();
			}
    		if (!AppConfiguration.configFile.isFile()) {
    			Configuration example = new Configuration();
    			example.setWatchDir("Absolute watch dir path");
    			List<ConfigFeed> configFeeds = new ArrayList<ConfigFeed>();
    			
    			ConfigFeed configFeed = new ConfigFeed();
    			configFeed.setTitle("a feed title");
    			configFeed.setUrl("a feed url");
    			configFeeds.add(configFeed);
    			
    			ConfigFeed configFeed2 = new ConfigFeed();
    			configFeed2.setTitle("a second feed title");
    			configFeed2.setUrl("a second feed url");
    			configFeeds.add(configFeed2);
    			
    			example.setConfigFeeds(configFeeds);
    			
    			AppConfiguration.saveConfiguration(example, AppConfiguration.configFile);
    		}
    		System.out.println("Created fake configuration file");
    		System.exit(0);
    	}
    	
    	AppConfiguration appConfiguration = new AppConfiguration();
    	App app = new App(appConfiguration);
    	app.execute();
    }

}