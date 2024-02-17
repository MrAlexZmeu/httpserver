package ru.alexzmeu.otus.java.httpserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HttpServer {
    private int port;
    private Dispatcher dispatcher;
    private ExecutorService executorService;
    private final Logger LOGGER = LogManager.getLogger(String.valueOf(HttpServer.class));

    public HttpServer(int port) {
        this.port = port;
        this.dispatcher = new Dispatcher();
        this.executorService = Executors.newFixedThreadPool(5);
    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Сервер запущен на порту: " + port);
            while (true) {
                Socket socket = serverSocket.accept();
                LOGGER.info("Подключился клиент ");
                executorService.execute(() -> {
                    try {
                        executeRequest(socket);
                    } catch (IOException e) {
                        LOGGER.error(e.getMessage());

                    }

                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void executeRequest(Socket socket) throws IOException {
        byte[] buffer = new byte[8192];
        int n = socket.getInputStream().read(buffer);
        if (n > 0) {
            String rawRequest = new String(buffer, 0, n);
            HttpRequest httpRequest = new HttpRequest(rawRequest);
            dispatcher.execute(httpRequest, socket.getOutputStream());
        }

        try {
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            e.getMessage();
        }
    }
}
