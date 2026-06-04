package com.ai.ollama.ollamaclient.evaluation;

import java.util.HashSet;
import java.util.Set;

// =====================================
// EVALUADOR DE COBERTURA CONCEPTUAL
// =====================================
//
// Mide qué porcentaje de la respuesta
// contiene conceptos presentes en el
// prompt.
//
// Se utiliza como una aproximación
// heurística de alineación entre
// prompt y respuesta.
//
// =====================================

public class SemanticEvaluator {

    private final TextTokenizer tokenizer =
            new TextTokenizer();

    public double calcularSimilitudSemantica(

            String prompt,

            String respuesta
    ) {

        Set<String> tokensPrompt =
                tokenizer.tokenizar(
                        prompt
                );

        Set<String> tokensRespuesta =
                tokenizer.tokenizar(
                        respuesta
                );

        Set<String> coincidencias =
                new HashSet<>(
                        tokensRespuesta
                );

        coincidencias.retainAll(
                tokensPrompt
        );

        if (tokensRespuesta.isEmpty()) {

            return 0;
        }

        return (

                (double)
                        coincidencias.size()

                        / tokensRespuesta.size()

        ) * 100;
    }
}
