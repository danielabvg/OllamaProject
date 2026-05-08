// =====================================
// FEW-SHOT PROMPT STRATEGY
// =====================================
//
// Esta estrategia aplica la técnica
// Few-Shot Prompting.
//
// Su objetivo es guiar al modelo
// utilizando ejemplos previos para
// mejorar precisión y consistencia.
//
// Esto ayuda al modelo a detectar
// patrones antes de responder.
// =====================================

package com.ai.ollama.OllamaClient.PromptingEngine.Impl;

import com.ai.ollama.OllamaClient.Template.PromptBuilder;

public class FewShotPromptStrategy
        implements PromptStrategy {

    // =====================================
    // DEFINICIÓN DEL PROMPT
    // =====================================
    //
    // Agrega ejemplos previos para
    // orientar el comportamiento del modelo.
    // =====================================

    @Override
    public String definirEstructuraPrompt(
            PromptBuilder builder
    ) {

        // =====================================
        // EJEMPLO 1
        // =====================================

        builder.agregarEjemplo(

                "¿Qué es encapsulamiento?",

                "Es el principio de ocultar "
                        + "los datos internos de una clase."
        );

        // =====================================
        // EJEMPLO 2
        // =====================================

        builder.agregarEjemplo(

                "¿Qué es una clase en Java?",

                "Es una plantilla para crear objetos."
        );

        // =====================================
        // EJEMPLO 3
        // =====================================

        builder.agregarEjemplo(

                "¿Qué es polimorfismo?",

                "Es la capacidad de un objeto "
                        + "de comportarse de múltiples formas."
        );

        // Construcción final del prompt.

        return builder.build();
    }
}
