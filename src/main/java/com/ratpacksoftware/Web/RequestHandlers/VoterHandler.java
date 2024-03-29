package com.ratpacksoftware.Web.RequestHandlers;

import com.google.gson.Gson;
import com.ratpacksoftware.Managers.VoterManager;
import com.ratpacksoftware.Web.Parsers.RequestParser;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Michel on 1-6-2017.
 */
public class VoterHandler implements HttpHandler {
    private VoterManager _voterManager;
    private RequestParser _requestParser;

    public VoterHandler(VoterManager voterManager) {
        _voterManager = voterManager;
        _requestParser = new RequestParser();
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String response = "";

        String requestPath = httpExchange.getRequestURI().getPath();
        String requestQuery = httpExchange.getRequestURI().getQuery();

        switch(requestPath) {
            case "/api/request/id":
                response += new Gson().toJson(_voterManager.createNewVoter());
                break;
            case "/api/voter/get":
                Map<String, String> result = _requestParser.parseQueryString(requestQuery);

                String userId = result.get("id");

                response += new Gson().toJson(_voterManager.getVoterById(UUID.fromString(userId)));
                break;
        }

        httpExchange.sendResponseHeaders(200, response.length());

        try(OutputStream os = httpExchange.getResponseBody()) {
            os.write(response.toString().getBytes());
            os.flush();
        }
    }
}
