// =====================================
// EVALUADOR SEMÁNTICO
// =====================================
//
// Esta clase calcula similitud semántica
// aproximada entre:
//
// - respuesta generada
// - conceptos esperados
//
// Utiliza:
//
// - Jaccard Similarity
// - Token Overlap
//
// Inspirado en métricas utilizadas
// en benchmarking de NLP.
//
// =====================================

package com.ai.ollama.OllamaClient.Evaluation;

import java.util.HashSet;
import java.util.Set;

public class SemanticEvaluator {

    // =====================================
    // JACCARD SIMILARITY
    // =====================================
    //
    // Fórmula:
    //
    // intersección / unión
    //
    // Retorna porcentaje de similitud.
    //
    // =====================================

    public double calcularSimilitudSemantica(

            String respuesta,

            String referencia
    ) {

        Set<String> tokensRespuesta =
                tokenizar(respuesta);

        Set<String> tokensReferencia =
                tokenizar(referencia);

        // =====================================
        // INTERSECCIÓN
        // =====================================

        Set<String> interseccion =
                new HashSet<>(tokensRespuesta);

        interseccion.retainAll(
                tokensReferencia
        );

        // =====================================
        // UNIÓN
        // =====================================

        Set<String> union =
                new HashSet<>(tokensRespuesta);

        union.addAll(tokensReferencia);

        // =====================================
        // CÁLCULO FINAL
        // =====================================

        return ((double)

                interseccion.size()

                / union.size()) * 100;
    }

    // =====================================
    // TOKENIZACIÓN
    // =====================================

    private Set<String> tokenizar(
            String texto
    ) {

        String[] tokens =
                texto.toLowerCase()
                        .replaceAll("[^a-zA-ZáéíóúÁÉÍÓÚñÑ ]", "")
                        .split("\\s+");

        return new HashSet<>(
                java.util.Arrays.asList(tokens)
        );
    }
}
