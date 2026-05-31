package com.ai.ollama.ollamaclient.evaluation;

import java.util.Set;

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

public class SemanticEvaluator {

    // =====================================
    // TOKENIZER DESACOPLADO
    // =====================================
    //
    // SemanticEvaluator ahora delega
    // completamente el procesamiento
    // textual.
    //
    // Esto permite:
    //
    // - bajo acoplamiento
    // - reutilización
    // - arquitectura limpia
    // - SRP más puro
    //
    // =====================================

    private final TextTokenizer tokenizer =
            new TextTokenizer();

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
                tokenizer.tokenizar(respuesta);

        Set<String> tokensReferencia =
                tokenizer.tokenizar(referencia);

        // =====================================
        // INTERSECCIÓN
        // =====================================

        Set<String> interseccion =
                new java.util.HashSet<>(tokensRespuesta);

        interseccion.retainAll(
                tokensReferencia
        );

        // =====================================
        // UNIÓN
        // =====================================

        Set<String> union =
                new java.util.HashSet<>(tokensRespuesta);

        union.addAll(tokensReferencia);

        // =====================================
        // CÁLCULO FINAL
        // =====================================

        return ((double)

                interseccion.size()

                / union.size()) * 100;
    }
}
