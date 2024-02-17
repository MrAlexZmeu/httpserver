package ru.alexzmeu.otus.java.httpserver.processors;

import ru.alexzmeu.otus.java.httpserver.HttpRequest;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.alexzmeu.otus.java.httpserver.HttpServer;

public class UnknownRequestProcessor implements RequestProcessor {
    private final Logger LOGGER = LogManager.getLogger(String.valueOf(UnknownRequestProcessor.class));
    @Override
    public void execute(HttpRequest httpRequest, OutputStream output) throws IOException {
        String response = "HTTP/1.1 404 Not found";
        LOGGER.info("Отправили 404");
        output.write(response.getBytes(StandardCharsets.UTF_8));
    }
}
