package com.example.reactiveprogrammingallpractice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.tools.agent.ReactorDebugAgent;

@SpringBootApplication
public class ReactiveProgrammingAllPracticeApplication {

    public static void main(String[] args) {
        ReactorDebugAgent.init();
        SpringApplication.run(ReactiveProgrammingAllPracticeApplication.class, args);
    }

}
