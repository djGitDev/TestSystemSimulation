package ca.uqam.mgl7230.tp3.part1.config;

import ca.uqam.mgl7230.tp3.part1.rest.FlightBookingController;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class HttpServerStarter {

    public void startServer() throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8081), 0);
        server.createContext("/flight/frontend", new FlightBookingController());
        server.setExecutor(null); // creates a default executor
        server.start();
        System.out.println("Server started on port 8080");
    }
}
