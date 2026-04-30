package com.ai.ollama.OllamaClient.IntentRouting;

public class IntentRouter {

    public String determinarRol(String instruccionesUsuario) {
        String input = instruccionesUsuario.toLowerCase();

        if (input.contains("clima") || input.contains("tiempo")) {
            return "Meteorólogo Profesional Certificado";
        }
        if (input.contains("patrón") || input.contains("código") || input.contains("java")) {
            return "Arquitecto de Software Senior y experto en Clean Code";
        }
        if (input.contains("tarea") || input.contains("explica")) {
            return "Profesor de Inteligencia Artificial";
        }

        return "Asistente Virtual General";
    }

    public String optimizarInstrucciones(String instrucciones) {

        String input = instrucciones.toLowerCase();

        if (input.contains("clima")) {
            return instrucciones + " (Responde solo con la temperatura y condición)";
        }

        if (input.contains("inteligencia artificial") || input.contains("ia")) {
            return instrucciones + " (Explica con ejemplos y analogías)";
        }

        if (input.contains("videojuegos") || input.contains("gaming")) {
            return instrucciones + " (Incluye referencias a juegos populares)";
        }

        return instrucciones;
    }
}