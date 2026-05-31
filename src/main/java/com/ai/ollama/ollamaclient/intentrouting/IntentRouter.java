// =====================================
// ROUTER DE INTENCIONES
// =====================================
//
// Esta clase analiza el texto del usuario
// para determinar automáticamente:
// - el rol más adecuado
// - optimizaciones del prompt
//
// Funciona como una capa de routing
// inteligente antes de enviar la petición
// al modelo de IA.
// =====================================

package com.ai.ollama.ollamaclient.intentrouting;

public class IntentRouter {

    // =====================================
    // DETERMINACIÓN DE ROL
    // =====================================
    //
    // Detecta palabras clave para asignar
    // automáticamente una personalidad
    // o contexto especializado.
    // =====================================

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

        // Rol por defecto.

        return "Asistente Virtual General";
    }

    // =====================================
    // OPTIMIZACIÓN DE INSTRUCCIONES
    // =====================================
    //
    // Mejora automáticamente el prompt
    // agregando contexto adicional.
    // =====================================

    public String optimizarInstrucciones(String input) {

        if (input.toLowerCase().contains("ia")) {

            return input
                    + " Explica con ejemplos sencillos.";
        }

        return input;
    }
}