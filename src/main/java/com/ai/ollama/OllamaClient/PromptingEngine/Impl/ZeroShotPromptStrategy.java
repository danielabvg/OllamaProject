// =====================================
// ZERO-SHOT PROMPT STRATEGY
// =====================================
//
// Esta estrategia aplica la técnica
// Zero-Shot Prompting.
//
// El modelo responde únicamente
// utilizando instrucciones directas,
// sin ejemplos previos.
//
// Es la forma más simple y rápida
// de interacción con un LLM.
// =====================================

package com.ai.ollama.OllamaClient.PromptingEngine.Impl;

import com.ai.ollama.OllamaClient.Template.PromptBuilder;

public class ZeroShotPromptStrategy
        implements PromptStrategy {

    // =====================================
    // DEFINICIÓN DEL PROMPT
    // =====================================
    //
    // Construye el prompt utilizando
    // únicamente la configuración base.
    // =====================================

    @Override
    public String definirEstructuraPrompt(
            PromptBuilder builder
    ) {

        // Construcción directa del prompt.

        return builder.build();
    }
}
