package com.drawing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application
{
    public static void main(String[] args) throws InterruptedException, IOException, ParseException
    {
        SpringApplication.run(Application.class, args);

        //this will display on server terminal
        System.out.println("\n\nOpen Drawing Online is running\n\n");
    }
}
