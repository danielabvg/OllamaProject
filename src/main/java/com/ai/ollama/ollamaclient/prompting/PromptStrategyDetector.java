package com.ai.ollama.ollamaclient.prompting;

// =====================================================
// DETECTOR CENTRAL DE PROMPT STRATEGIES
// =====================================================
//
// Esta clase detecta automáticamente:
//
// - Zero-Shot
// - Few-Shot
// - Chain-of-Thought
//
// Puede analizar:
//
// - preguntas normales
// - prompts manuales
//
// =====================================================

public class PromptStrategyDetector {

    // =====================================================
    // DETECCIÓN GENERAL
    // =====================================================

    public PromptStrategy detectar(String texto) {

        texto = texto.toLowerCase();

        // =====================================
        // CHAIN OF THOUGHT
        // =====================================

        if (

                texto.contains("paso a paso")
                        ||

                        texto.contains("analiza")
                        ||

                        texto.contains("razona")
                        ||

                        texto.contains("resolver")
                        ||

                        texto.contains("resuelve")
                        ||

                        texto.contains("procedimiento")
                        ||

                        texto.contains("piensa")
                        ||

                        texto.contains("step by step")
                        ||

                        texto.contains("explica tu razonamiento")
                        ||

                        texto.contains("desglosa")
        ) {

            return new ChainOfThoughtPromptStrategy();
        }

        // =====================================
        // FEW SHOT
        // =====================================

        if (

                texto.contains("ejemplo:")
                        ||

                        texto.contains("ejemplos:")
                        ||

                        texto.contains("ejemplo 1")
                        ||

                        texto.contains("ejemplo 2")
                        ||

                        texto.contains("entrada:")
                        ||

                        texto.contains("salida:")
                        ||

                        texto.contains("aprende de estos ejemplos")
                        ||

                        texto.contains("example")
        ) {

            return new FewShotPromptStrategy();
        }

        // =====================================
        // ZERO SHOT
        // =====================================

        return new ZeroShotPromptStrategy();
    }
}