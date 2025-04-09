package ca.uqam.mgl7230.tp3.part2;

import java.io.IOException;

import static ca.uqam.mgl7230.tp3.part2.config.ServerInitializer.initServer;

public class Application {

    public static void main(String[] args) throws IOException {
        System.out.println("Server service started...");
        initServer();
    }
}
