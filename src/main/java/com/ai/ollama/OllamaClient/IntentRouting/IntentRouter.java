// Intelligent routing system based on user intent
package com.ai.ollama.OllamaClient.IntentRouting;

public class IntentRouter {

    public String determinarRol(String input) {

        input = input.toLowerCase();

        if (input.contains("java")
                || input.contains("patrón")) {

            return "Arquitecto de Software Senior";
        }

        if (input.contains("ia")
                || input.contains("inteligencia artificial")) {

            return "Profesor de Inteligencia Artificial";
        }

        if (input.contains("clima")) {

            return "Meteorólogo Profesional";
        }

        return "Asistente Virtual General";
    }

    public String optimizarInstrucciones(String input) {

        if (input.toLowerCase().contains("ia")) {

            return input
                    + " Explica con ejemplos sencillos.";
        }

        return input;
    }
}