package com.zenika.fdevback;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class FdevBackApplication implements ApplicationRunner {
    @Autowired
    Environment environment;

    private static final Logger logger = LogManager.getLogger(FdevBackApplication.class);

    public static void main(String[] args) { SpringApplication.run(FdevBackApplication.class, args); }


    @Override
    public void run(ApplicationArguments args) throws Exception {
    	logger.trace("Application started; is listening on port " + environment.getProperty("local.server.port"));
    }
}
