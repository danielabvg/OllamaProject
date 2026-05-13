// =====================================
// CHAIN-OF-THOUGHT STRATEGY
// =====================================
//
// Esta estrategia aplica la técnica
// Chain-of-Thought Prompting.
//
// Su objetivo es forzar al modelo
// a razonar paso a paso antes de
// generar una respuesta final.
//
// Esto suele mejorar:
// - lógica
// - precisión
// - explicaciones complejas
// - razonamiento matemático
// =====================================

package com.ai.ollama.OllamaClient.PromptingEngine.Impl;

import com.ai.ollama.OllamaClient.Template.PromptBuilder;

public class ChainOfThoughtPromptStrategy
        implements PromptStrategy {

    // =====================================
    // DEFINICIÓN DEL PROMPT
    // =====================================
    //
    // Agrega instrucciones para que
    // el modelo piense paso a paso.
    // =====================================

    @Override
    public String definirEstructuraPrompt(
            PromptBuilder builder
    ) {

        builder.conInstrucciones(
                """
                Piensa paso a paso.

                Primero analiza el problema.
                Luego explica el concepto.
                Finalmente da un ejemplo claro.
                """
        );

        // Construcción final del prompt.

        return builder.build();


    }
}
