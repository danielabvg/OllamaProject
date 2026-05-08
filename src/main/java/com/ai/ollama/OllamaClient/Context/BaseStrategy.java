// Reusable abstract strategy for local AI models
package com.ai.ollama.OllamaClient.Context;

public abstract class BaseStrategy {

    protected String extraerRespuesta(String json) {

        try {

            int inicio =
                    json.indexOf("\"response\":\"") + 12;

            int fin =
                    json.indexOf("\",", inicio);

            return json.substring(inicio, fin)
                    .replace("\\n", "\n")
                    .replace("\\\"", "\"");

        } catch (Exception e) {

            return json;
        }
    }
}
