package ca.uqam.mgl7230.tp3.part1;

import java.io.IOException;

import static ca.uqam.mgl7230.tp3.part1.config.ApplicationInitializer.init;
import static ca.uqam.mgl7230.tp3.part1.config.ServerInitializer.initServer;
import static ca.uqam.mgl7230.tp3.part1.service.ExecuteService.execute;

public class Application {

    public static void main(String[] args) throws IOException {
        System.out.println("Service started...");
        init();
        initServer();
        execute();
    }
}
