package ca.uqam.mgl7230.tp3.part1.config;

import java.io.IOException;

public class ServerInitializer {

    public static void initServer() throws IOException {
        HttpServerStarter httpServerStarter = new HttpServerStarter();
        httpServerStarter.startServer();
    }

}
