package ru.alexzmeu.otus.java.httpserver.processors;

import com.google.gson.Gson;
import ru.alexzmeu.otus.java.httpserver.HttpRequest;
import ru.alexzmeu.otus.java.httpserver.entities.Product;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class PostBodyDemoRequestProcessor implements RequestProcessor {
    private Gson gson;

    public PostBodyDemoRequestProcessor() {
        gson = new Gson();
    }

    @Override
    public void execute(HttpRequest httpRequest, OutputStream output) throws IOException {
        Product product = gson.fromJson(httpRequest.getBody(), Product.class);
        System.out.println(product);

        String response = "HTTP/1.1 200 OK\r\nContent-Type: text/html\r\n\r\n<html><body></body></html>";
        output.write(response.getBytes(StandardCharsets.UTF_8));
    }
}
