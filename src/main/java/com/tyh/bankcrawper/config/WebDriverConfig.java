package com.tyh.bankcrawper.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PreDestroy;
import java.util.concurrent.TimeUnit;

@Configuration
public class WebDriverConfig {

    /**
     * WebDriver初始化
     */
    @Bean(destroyMethod = "quit")
    public WebDriver WebDriverProducer(){
        System.setProperty("webdriver.gecko.driver","/Applications/Firefox.app/Contents/MacOS/geckodriver");
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("-headless");
        WebDriver webDriver = new FirefoxDriver(options);
        // webDriver.manage().window().maximize();
        // webDriver.manage().deleteAllCookies();
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        System.out.println("驱动已启动！");
        return webDriver;
    }

}
