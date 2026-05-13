package com.ai.ollama.OllamaClient.main;

// =====================================
// IMPORTACIÓN DE CONTEXTO Y ESTRATEGIAS
// =====================================

import com.ai.ollama.OllamaClient.Context.AgenteConversacional;
import com.ai.ollama.OllamaClient.Context.Llama3Strategy;
import com.ai.ollama.OllamaClient.Context.MistralStrategy;
import com.ai.ollama.OllamaClient.Context.Phi3Strategy;

// =====================================
// IMPORTACIÓN DE MÉTRICAS
// =====================================

import com.ai.ollama.OllamaClient.Evaluation.HallucinationDetector;
import com.ai.ollama.OllamaClient.Evaluation.ResponseEvaluator;

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
// Sistema multimodelo con:
// - Strategy Pattern
// - Prompt Engineering
// - Benchmarking
// - Evaluation Metrics
// - Intent Routing
// =====================================

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        while (true) {

            // =====================================
            // MENÚ PRINCIPAL
            // =====================================

            System.out.println("""

                    ====================================
                    IA MULTIMODELO CON OLLAMA
                    ====================================

                    1. Usar Llama3
                    2. Usar Mistral
                    3. Usar Phi3 Mini
                    4. Comparar TODOS los modelos
                    5. Salir

                    ====================================
                    """);

            System.out.print(
                    "Selecciona una opción: "
            );

            int opcion = scanner.nextInt();

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

            String pregunta = scanner.nextLine();

            // =====================================
            // GENERADOR DE PROMPTS
            // =====================================

            GeneradorPrompt generador =
                    new GeneradorPrompt();

            PromptConfig config =
                    generador.generar(pregunta);

            // =====================================
            // ROUTER AUTOMÁTICO DE PROMPT STRATEGY
            // =====================================
            //
            // El sistema detecta automáticamente
            // qué técnica de Prompt Engineering
            // conviene utilizar según la intención
            // de la pregunta del usuario.
            //
            // Esto transforma el sistema en una
            // arquitectura de razonamiento dinámico.
            //
            // =====================================

            PromptStrategyRouter router =
                    new PromptStrategyRouter();

            PromptStrategy promptStrategy =
                    router.detectarStrategy(
                            pregunta
                    );

            // =====================================
            // PROMPT STRATEGY DETECTADA
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
            // EJECUCIÓN NORMAL
            // =====================================

            ejecutarSistema(
                    opcion,
                    config,
                    promptStrategy
            );
        }

        scanner.close();
    }

    // =====================================
    // EJECUCIÓN DEL SISTEMA
    // =====================================

    public static void ejecutarSistema(

            int opcion,

            PromptConfig config,

            PromptStrategy promptStrategy
    ) {

        // =====================================
        // PROMPT BUILDER
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
        // GENERACIÓN DEL PROMPT
        // =====================================

        String promptFinal =
                promptStrategy
                        .definirEstructuraPrompt(
                                builder
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
        // SWITCH PRINCIPAL
        // =====================================

        switch (opcion) {

            case 1 -> ejecutarModelo(
                    new Llama3Strategy(),
                    config,
                    promptStrategy
            );

            case 2 -> ejecutarModelo(
                    new MistralStrategy(),
                    config,
                    promptStrategy
            );

            case 3 -> ejecutarModelo(
                    new Phi3Strategy(),
                    config,
                    promptStrategy
            );

            case 4 -> compararModelos(
                    config,
                    promptStrategy
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

        AgenteConversacional agente =
                new AgenteConversacional(
                        estrategia
                );

        ResponseEvaluator evaluator =
                new ResponseEvaluator();

        HallucinationDetector detector =
                new HallucinationDetector();

        // =====================================
        // MEDICIÓN DE TIEMPO
        // =====================================

        long inicio =
                System.currentTimeMillis();

        String respuesta =
                agente.preguntar(config);

        long fin =
                System.currentTimeMillis();

        long latency =
                fin - inicio;

        // =====================================
        // KEYWORDS
        // =====================================

        String[] keywords = {

                "java",
                "software",
                "arquitectura",
                "clase",
                "objeto"
        };

        // =====================================
        // MÉTRICAS
        // =====================================

        double precision =
                evaluator.calcularSemanticPrecision(
                        respuesta,
                        keywords
                );

        int tokens =
                evaluator.calcularTokens(
                        respuesta
                );

        double hallucination =
                detector.detectarHallucinationRate(
                        respuesta,
                        keywords
                );

        String segundaRespuesta =
                agente.preguntar(config);

        double consistency =
                evaluator.calcularConsistencyScore(
                        respuesta,
                        segundaRespuesta
                );

        // =====================================
        // RESULTADOS
        // =====================================

        System.out.println("""

                ====================================
                RESULTADO DEL MODELO
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
                "Latency: "
                        + latency + " ms"
        );

        System.out.println(
                "Semantic Precision: "
                        + precision + "%"
        );

        System.out.println(
                "Consistency Score: "
                        + consistency + "%"
        );

        System.out.println(
                "Hallucination Rate: "
                        + hallucination + "%"
        );

        System.out.println(
                "Token Count: "
                        + tokens
        );

        System.out.println("""

                ====================================
                RESPUESTA
                ====================================
                """);

        System.out.println(respuesta);
    }

    // =====================================
    // COMPARACIÓN MULTIMODELO
    // =====================================

    public static void compararModelos(

            PromptConfig config,

            PromptStrategy promptStrategy
    ) {

        IAStrategy[] modelos = {

                new Llama3Strategy(),

                new MistralStrategy(),

                new Phi3Strategy()
        };

        for (IAStrategy modelo : modelos) {

            ejecutarModelo(
                    modelo,
                    config,
                    promptStrategy
            );
        }
    }
}