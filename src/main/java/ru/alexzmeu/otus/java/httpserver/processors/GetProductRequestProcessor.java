package ru.alexzmeu.otus.java.httpserver.processors;

import com.google.gson.Gson;
import ru.alexzmeu.otus.java.httpserver.HttpRequest;
import ru.alexzmeu.otus.java.httpserver.entities.Category;
import ru.alexzmeu.otus.java.httpserver.entities.Product;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class GetProductRequestProcessor implements RequestProcessor{
    private Gson gson;

    public GetProductRequestProcessor() {
        gson = new Gson();
    }
    @Override
    public void execute(HttpRequest httpRequest, OutputStream output) throws IOException {
        Product product = new Product(1L,"orange",new Category(2L,"fruit"));
        String response = "HTTP/1.1 200 OK\r\nContent-Type: application/json\r\n\r\n" +  gson.toJson(product);
        output.write(response.getBytes(StandardCharsets.UTF_8));
    }
}
