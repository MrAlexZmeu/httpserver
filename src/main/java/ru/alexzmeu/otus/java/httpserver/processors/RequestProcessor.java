package ru.alexzmeu.otus.java.httpserver.processors;

import ru.alexzmeu.otus.java.httpserver.HttpRequest;

import java.io.IOException;
import java.io.OutputStream;

public interface RequestProcessor {
    void execute(HttpRequest httpRequest, OutputStream output) throws IOException;
}
