package ru.alexzmeu.otus.java.httpserver;

import ru.alexzmeu.otus.java.httpserver.processors.HelloWorldRequestProcessor;
import ru.alexzmeu.otus.java.httpserver.processors.OperationAddRequestProcessor;
import ru.alexzmeu.otus.java.httpserver.processors.RequestProcessor;
import ru.alexzmeu.otus.java.httpserver.processors.UnknownRequestProcessor;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

public class Dispatcher {
    private Map<String, RequestProcessor> router;
    private RequestProcessor unknownRequestProcessor;

    public Dispatcher() {
        this.router = new HashMap<>();
        this.router.put("/add", new OperationAddRequestProcessor());
        this.router.put("/hello_world", new HelloWorldRequestProcessor());
        this.unknownRequestProcessor = new UnknownRequestProcessor();
    }

    public void execute(HttpRequest httpRequest, OutputStream output) throws IOException {
        if (!router.containsKey(httpRequest.getUri())) {
            unknownRequestProcessor.execute(httpRequest, output);
            return;
        }
        router.get(httpRequest.getUri()).execute(httpRequest, output);
    }
}
