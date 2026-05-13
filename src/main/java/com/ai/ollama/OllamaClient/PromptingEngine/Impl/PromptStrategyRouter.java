package com.ai.ollama.OllamaClient.PromptingEngine.Impl;

// ======================================================
// ROUTER AUTOMÁTICO DE PROMPT STRATEGIES
// ======================================================
//
// Esta clase detecta automáticamente qué tipo
// de Prompt Engineering conviene usar según
// la intención de la pregunta del usuario.
//
// El objetivo es automatizar el razonamiento
// del sistema y eliminar la selección manual.
//
// ======================================================

public class PromptStrategyRouter {

    // ======================================================
    // DETECCIÓN AUTOMÁTICA DE STRATEGY
    // ======================================================

    public PromptStrategy detectarStrategy(

            String pregunta
    ) {

        String texto =
                pregunta.toLowerCase();

        // ======================================================
        // CHAIN OF THOUGHT
        // ======================================================
        //
        // Ideal para:
        // - matemáticas
        // - lógica
        // - programación
        // - análisis paso a paso
        //
        // ======================================================

        if (

                texto.contains("paso a paso")
                        ||

                        texto.contains("explica")
                        ||

                        texto.contains("analiza")
                        ||

                        texto.contains("algoritmo")
                        ||

                        texto.contains("matemática")
                        ||

                        texto.contains("ecuación")
                        ||

                        texto.contains("programa")
                        ||

                        texto.contains("código")
                        ||

                        texto.contains("resolver")
        ) {

            return new ChainOfThoughtPromptStrategy();
        }

        // ======================================================
        // FEW SHOT
        // ======================================================
        //
        // Ideal para:
        // - ejemplos
        // - aprendizaje
        // - enseñanza
        //
        // ======================================================

        if (

                texto.contains("ejemplo")
                        ||

                        texto.contains("aprende")
                        ||

                        texto.contains("enséñame")
                        ||

                        texto.contains("compara")
                        ||

                        texto.contains("diferencia")
        ) {

            return new FewShotPromptStrategy();
        }

        // ======================================================
        // ZERO SHOT
        // ======================================================
        //
        // Preguntas simples o generales.
        //
        // ======================================================

        return new ZeroShotPromptStrategy();
    }
}
