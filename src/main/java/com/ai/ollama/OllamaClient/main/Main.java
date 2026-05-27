package com.ai.ollama.OllamaClient.main;

// =====================================
// IMPORTACIÓN DE CONTEXTO
// =====================================

import com.ai.ollama.OllamaClient.Context.AgenteConversacional;
import com.ai.ollama.OllamaClient.Context.ModeloStrategy;

// =====================================
// IMPORTACIÓN DE EVALUACIÓN
// =====================================

import com.ai.ollama.OllamaClient.Evaluation.BenchmarkPipeline;
import com.ai.ollama.OllamaClient.Evaluation.EvaluationResult;

// =====================================
// IMPORTACIÓN DE OLLAMA CLIENT
// =====================================

import com.ai.ollama.OllamaClient.OllamaClient;

// =====================================
// IMPORTACIÓN DE PROMPT ENGINEERING
// =====================================

import com.ai.ollama.OllamaClient.PromptingEngine.Impl.GeneradorPrompt;
import com.ai.ollama.OllamaClient.PromptingEngine.Impl.PromptStrategy;
import com.ai.ollama.OllamaClient.PromptingEngine.Impl.PromptStrategyRouter;

// =====================================
// IMPORTACIÓN DE ABSTRACCIONES
// =====================================

import com.ai.ollama.OllamaClient.Strategy.IAStrategy;

// =====================================
// IMPORTACIÓN DE TEMPLATES
// =====================================

import com.ai.ollama.OllamaClient.Template.PromptBuilder;
import com.ai.ollama.OllamaClient.Template.PromptConfig;

// =====================================
// LIBRERÍAS
// =====================================

import java.util.Scanner;

// =====================================
// MAIN PRINCIPAL
// =====================================
//
// Framework conversacional multimodelo
// basado en:
//
// - Strategy Pattern
// - Prompt Engineering
// - Benchmarking Modular
// - Evaluation Pipelines
// - Arquitectura Desacoplada
//
// =====================================

public class Main {

    // =====================================
    // MAIN
    // =====================================

    public static void main(String[] args) {

        Scanner scanner =
                new Scanner(System.in);

        // =====================================
        // CLIENTE CENTRAL DE OLLAMA
        // =====================================
        //
        // Esta instancia centraliza toda
        // la comunicación HTTP.
        //
        // Beneficios:
        //
        // - reutilización
        // - bajo acoplamiento
        // - modularidad
        //
        // =====================================

        OllamaClient cliente =
                new OllamaClient();

        while (true) {

            // =====================================
            // MENÚ PRINCIPAL
            // =====================================

            System.out.println("""

                    ====================================
                    FRAMEWORK MULTIMODELO IA
                    ====================================

                    1. Ejecutar Llama3
                    2. Ejecutar Mistral
                    3. Ejecutar Phi3 Mini
                    4. Benchmark Multimodelo
                    5. Salir

                    ====================================
                    """);

            System.out.print(
                    "Selecciona una opción: "
            );

            int opcion =
                    scanner.nextInt();

            scanner.nextLine();

            // =====================================
            // SALIR
            // =====================================

            if (opcion == 5) {

                System.out.println("""

                        Cerrando sistema...
                        """);

                break;
            }

            // =====================================
            // INPUT USUARIO
            // =====================================

            System.out.print("""

                    Escribe tu pregunta:
                    """);

            String pregunta =
                    scanner.nextLine();

            // =====================================
            // GENERADOR DE PROMPTS
            // =====================================
            //
            // Detecta:
            //
            // - rol contextual
            // - instrucciones
            // - intención
            //
            // =====================================

            GeneradorPrompt generador =
                    new GeneradorPrompt();

            PromptConfig config =
                    generador.generar(
                            pregunta
                    );

            // =====================================
            // ROUTER DE PROMPT STRATEGIES
            // =====================================
            //
            // Selecciona automáticamente:
            //
            // - Zero-Shot
            // - Few-Shot
            // - Chain-of-Thought
            //
            // =====================================

            PromptStrategyRouter router =
                    new PromptStrategyRouter();

            PromptStrategy promptStrategy =
                    router.detectarStrategy(
                            pregunta
                    );

            // =====================================
            // VISUALIZACIÓN DE STRATEGY
            // =====================================

            System.out.println("""

                    ====================================
                    PROMPT STRATEGY DETECTADA
                    ====================================
                    """);

            System.out.println(
                    promptStrategy
                            .getClass()
                            .getSimpleName()
            );

            // =====================================
            // EJECUCIÓN PRINCIPAL
            // =====================================

            ejecutarSistema(

                    opcion,

                    config,

                    promptStrategy,

                    cliente
            );
        }

        scanner.close();
    }

    // =====================================
    // EJECUCIÓN PRINCIPAL
    // =====================================

    public static void ejecutarSistema(

            int opcion,

            PromptConfig config,

            PromptStrategy promptStrategy,

            OllamaClient cliente
    ) {

        // =====================================
        // BUILDER DEL PROMPT
        // =====================================

        PromptBuilder builder =
                new PromptBuilder()

                        .conRol(
                                config.getRol()
                        )

                        .conInstrucciones(
                                config.getInstrucciones()
                        )

                        .conEntrada(
                                config.getEntrada()
                        );

        // =====================================
        // GENERACIÓN FINAL DEL PROMPT
        // =====================================

        String promptFinal =
                promptStrategy
                        .definirEstructuraPrompt(
                                builder
                        );

        // =====================================
        // GUARDAR PROMPT FINAL
        // =====================================

        config.setPromptFinal(
                promptFinal
        );

        // =====================================
        // VISUALIZACIÓN DEL PROMPT
        // =====================================

        System.out.println("""

                ====================================
                PROMPT GENERADO
                ====================================
                """);

        System.out.println(promptFinal);

        // =====================================
        // SELECCIÓN DE MODELO
        // =====================================
        //
        // ModeloStrategy permite:
        //
        // - reutilización
        // - escalabilidad
        // - integración dinámica
        //
        // =====================================

        switch (opcion) {

            case 1 -> ejecutarModelo(

                    new ModeloStrategy(
                            "llama3",
                            "Llama3",
                            cliente
                    ),

                    config,

                    promptStrategy
            );

            case 2 -> ejecutarModelo(

                    new ModeloStrategy(
                            "mistral",
                            "Mistral",
                            cliente
                    ),

                    config,

                    promptStrategy
            );

            case 3 -> ejecutarModelo(

                    new ModeloStrategy(
                            "phi3",
                            "Phi3 Mini",
                            cliente
                    ),

                    config,

                    promptStrategy
            );

            case 4 -> compararModelos(

                    config,

                    promptStrategy,

                    cliente
            );

            default -> System.out.println(
                    "Opción inválida."
            );
        }
    }

    // =====================================
    // EJECUCIÓN INDIVIDUAL
    // =====================================

    public static void ejecutarModelo(

            IAStrategy estrategia,

            PromptConfig config,

            PromptStrategy promptStrategy
    ) {

        // =====================================
        // CONTEXTO CONVERSACIONAL
        // =====================================
        //
        // El contexto depende de la
        // abstracción IAStrategy.
        //
        // Esto aplica:
        //
        // - Polimorfismo
        //
        // =====================================

        AgenteConversacional agente =
                new AgenteConversacional(
                        estrategia
                );

        // =====================================
        // MEDICIÓN DE LATENCIA
        // =====================================

        long inicio =
                System.currentTimeMillis();

        // =====================================
        // RESPUESTA PRINCIPAL
        // =====================================

        String respuesta =
                agente.preguntar(config);

        long fin =
                System.currentTimeMillis();

        long latency =
                fin - inicio;

        // =====================================
        // SEGUNDA RESPUESTA
        // =====================================
        //
        // Se ejecuta nuevamente el prompt
        // para medir consistencia.
        //
        // =====================================

        String segundaRespuesta =
                agente.preguntar(config);

        // =====================================
        // REFERENCIA ESPERADA
        // =====================================
        //
        // Simula respuesta esperada
        // para evaluación semántica.
        //
        // =====================================

        String referencia =
                """
                arquitectura modular orientada
                a objetos utilizando patrones
                de diseño y principios SOLID
                """;

        // =====================================
        // PIPELINE DE BENCHMARKING
        // =====================================

        BenchmarkPipeline pipeline =
                new BenchmarkPipeline();

        EvaluationResult resultado =
                pipeline.ejecutarEvaluacion(

                        respuesta,

                        referencia,

                        segundaRespuesta,

                        latency
                );

        // =====================================
        // RESULTADOS
        // =====================================

        System.out.println("""

                ====================================
                RESULTADOS DEL BENCHMARK
                ====================================
                """);

        System.out.println(
                "Modelo: "
                        + estrategia.getNombreModelo()
        );

        System.out.println(
                "Prompt Strategy: "
                        + promptStrategy
                        .getClass()
                        .getSimpleName()
        );

        System.out.println(
                "Semantic Similarity: "
                        + resultado
                        .getSemanticSimilarity()
                        + "%"
        );

        System.out.println(
                "Consistency Score: "
                        + resultado
                        .getConsistencyScore()
                        + "%"
        );

        System.out.println(
                "Hallucination Risk: "
                        + resultado
                        .getHallucinationRisk()
                        + "%"
        );

        System.out.println(
                "Latency Score: "
                        + resultado
                        .getLatencyScore()
                        + "%"
        );

        System.out.println(
                "Final Composite Score: "
                        + resultado
                        .getFinalScore()
                        + "%"
        );

        System.out.println(
                "Quality Analysis: "
                        + resultado
                        .getQuality()
        );

        // =====================================
        // RESPUESTA FINAL
        // =====================================

        System.out.println("""

                ====================================
                RESPUESTA DEL MODELO
                ====================================
                """);

        System.out.println(
                respuesta
        );
    }

    // =====================================
    // BENCHMARK MULTIMODELO
    // =====================================

    public static void compararModelos(

            PromptConfig config,

            PromptStrategy promptStrategy,

            OllamaClient cliente
    ) {

        // =====================================
        // ARREGLO POLIMÓRFICO
        // =====================================

        IAStrategy[] modelos = {

                new ModeloStrategy(
                        "llama3",
                        "Llama3",
                        cliente
                ),

                new ModeloStrategy(
                        "mistral",
                        "Mistral",
                        cliente
                ),

                new ModeloStrategy(
                        "phi3",
                        "Phi3 Mini",
                        cliente
                )
        };

        // =====================================
        // EJECUCIÓN ITERATIVA
        // =====================================

        for (IAStrategy modelo : modelos) {

            ejecutarModelo(

                    modelo,

                    config,

                    promptStrategy
            );
        }
    }
}