// =====================================
// ABSTRACCIÓN DE ESTRATEGIAS DE PROMPT
// =====================================
//
// Esta interfaz define el contrato
// para las diferentes técnicas de
// Prompt Engineering.
//
// Ejemplos:
// - Zero-Shot
// - Few-Shot
// - Chain-of-Thought
// - Role Prompting
//
// Permite cambiar dinámicamente
// la estructura del prompt.
// =====================================

package com.ai.ollama.ollamaclient.prompting;

import com.ai.ollama.ollamaclient.template.PromptBuilder;

public interface PromptStrategy {

    // Construye un prompt utilizando
    // una técnica específica de prompting.

    String definirEstructuraPrompt(
            PromptBuilder builder
    );
}
