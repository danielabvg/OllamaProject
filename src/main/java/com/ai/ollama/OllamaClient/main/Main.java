// Dynamic multimodel benchmarking and evaluation system
package com.ai.ollama.OllamaClient.main;

import com.ai.ollama.OllamaClient.Context.*;
import com.ai.ollama.OllamaClient.Evaluation.ResponseEvaluator;
import com.ai.ollama.OllamaClient.Strategy.IAStrategy;
import com.ai.ollama.OllamaClient.Template.PromptConfig;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        while (true) {

            System.out.println("""
                    
                    ===== IA MULTIMODELO =====
                    
                    1. Llama3
                    2. Mistral
                    3. Phi3 Mini
                    4. Comparar TODOS
                    5. Salir
                    """);

            System.out.print("Selecciona una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            if (opcion == 5) {
                break;
            }

            System.out.print("\nEscribe tu pregunta: ");

            String pregunta = scanner.nextLine();

            PromptConfig config = new PromptConfig(
                    "Arquitecto de Software Senior",
                    "Responde de forma clara, técnica y educativa",
                    pregunta
            );

            switch (opcion) {

                case 1 ->
                        ejecutarModelo(
                                new Llama3Strategy(),
                                config
                        );

                case 2 ->
                        ejecutarModelo(
                                new MistralStrategy(),
                                config
                        );

                case 3 ->
                        ejecutarModelo(
                                new Phi3Strategy(),
                                config
                        );

                case 4 ->
                        compararModelos(config);

                default ->
                        System.out.println(
                                "Opción inválida."
                        );
            }
        }

        scanner.close();
    }

    // =====================================
    // EJECUCIÓN INDIVIDUAL
    // =====================================

    public static void ejecutarModelo(
            IAStrategy estrategia,
            PromptConfig config
    ) {

        AgenteConversacional agente =
                new AgenteConversacional(
                        estrategia
                );

        ResponseEvaluator evaluator =
                new ResponseEvaluator();

        // =====================================
        // KEYWORDS ESPERADAS
        // =====================================

        String[] keywords = {
                "java",
                "patrón",
                "encapsulamiento",
                "clase",
                "objeto",
                "software",
                "arquitectura"
        };

        // =====================================
        // TIEMPO
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
        // MÉTRICAS
        // =====================================

        double precision =
                evaluator.calcularSemanticPrecision(
                        respuesta,
                        keywords
                );

        int longitud =
                evaluator.calcularLongitud(
                        respuesta
                );

        int tokens =
                evaluator.calcularTokens(
                        respuesta
                );

        double hallucination =
                evaluator.calcularHallucinationRate(
                        respuesta,
                        keywords
                );

        // =====================================
        // CONSISTENCY
        // =====================================

        String segundaRespuesta =
                agente.preguntar(config);

        double consistency =
                evaluator.calcularConsistencyScore(
                        respuesta,
                        segundaRespuesta
                );

        // =====================================
        // OUTPUT
        // =====================================

        System.out.println("""
                
                ====================================
                """);

        System.out.println(
                "MODELO: "
                        + estrategia.getNombreModelo()
        );

        System.out.println("""
                
                ====================================
                """);

        System.out.println(respuesta);

        System.out.println("""
                
                ===== MÉTRICAS =====
                """);

        System.out.println(
                "Semantic Precision: "
                        + precision + "%"
        );

        System.out.println(
                "Latency Score: "
                        + latency + " ms"
        );

        System.out.println(
                "Response Length: "
                        + longitud
        );

        System.out.println(
                "Token Count: "
                        + tokens
        );

        System.out.println(
                "Consistency Score: "
                        + consistency + "%"
        );

        System.out.println(
                "Hallucination Rate: "
                        + hallucination + "%"
        );
    }

    // =====================================
    // COMPARACIÓN TOTAL
    // =====================================

    public static void compararModelos(
            PromptConfig config
    ) {

        IAStrategy[] modelos = {

                new Llama3Strategy(),
                new MistralStrategy(),
                new Phi3Strategy()
        };

        double mejorPrecision = 0;
        String modeloMasPreciso = "";

        long mejorTiempo =
                Long.MAX_VALUE;

        String modeloMasRapido = "";

        int mejorLongitud = 0;
        String modeloMasDetallado = "";

        double mejorConsistency = 0;
        String modeloMasConsistente = "";

        double menorHallucination =
                Double.MAX_VALUE;

        String modeloMasConfiable = "";

        for (IAStrategy estrategia : modelos) {

            AgenteConversacional agente =
                    new AgenteConversacional(
                            estrategia
                    );

            ResponseEvaluator evaluator =
                    new ResponseEvaluator();

            String[] keywords = {
                    "java",
                    "patrón",
                    "encapsulamiento",
                    "clase",
                    "objeto",
                    "software",
                    "arquitectura"
            };

            long inicio =
                    System.currentTimeMillis();

            String respuesta =
                    agente.preguntar(config);

            long fin =
                    System.currentTimeMillis();

            long latency =
                    fin - inicio;

            double precision =
                    evaluator.calcularSemanticPrecision(
                            respuesta,
                            keywords
                    );

            int longitud =
                    evaluator.calcularLongitud(
                            respuesta
                    );

            double hallucination =
                    evaluator.calcularHallucinationRate(
                            respuesta,
                            keywords
                    );

            String respuesta2 =
                    agente.preguntar(config);

            double consistency =
                    evaluator.calcularConsistencyScore(
                            respuesta,
                            respuesta2
                    );

            // =====================================
            // MOSTRAR RESULTADOS
            // =====================================

            System.out.println("""
                    
                    ====================================
                    """);

            System.out.println(
                    "MODELO: "
                            + estrategia.getNombreModelo()
            );

            System.out.println("""
                    
                    ====================================
                    """);

            System.out.println(respuesta);

            System.out.println("""
                    
                    ===== MÉTRICAS =====
                    """);

            System.out.println(
                    "Semantic Precision: "
                            + precision + "%"
            );

            System.out.println(
                    "Latency Score: "
                            + latency + " ms"
            );

            System.out.println(
                    "Response Length: "
                            + longitud
            );

            System.out.println(
                    "Consistency Score: "
                            + consistency + "%"
            );

            System.out.println(
                    "Hallucination Rate: "
                            + hallucination + "%"
            );

            // =====================================
            // COMPARATIVAS
            // =====================================

            if (precision > mejorPrecision) {

                mejorPrecision =
                        precision;

                modeloMasPreciso =
                        estrategia.getNombreModelo();
            }

            if (latency < mejorTiempo) {

                mejorTiempo =
                        latency;

                modeloMasRapido =
                        estrategia.getNombreModelo();
            }

            if (longitud > mejorLongitud) {

                mejorLongitud =
                        longitud;

                modeloMasDetallado =
                        estrategia.getNombreModelo();
            }

            if (consistency > mejorConsistency) {

                mejorConsistency =
                        consistency;

                modeloMasConsistente =
                        estrategia.getNombreModelo();
            }

            if (hallucination
                    < menorHallucination) {

                menorHallucination =
                        hallucination;

                modeloMasConfiable =
                        estrategia.getNombreModelo();
            }
        }

        // =====================================
        // ANÁLISIS FINAL
        // =====================================

        System.out.println("""
                
                
                ====================================
                ANÁLISIS FINAL
                ====================================
                """);

        System.out.println(
                "Modelo más preciso: "
                        + modeloMasPreciso
        );

        System.out.println(
                "Modelo más rápido: "
                        + modeloMasRapido
        );

        System.out.println(
                "Modelo más detallado: "
                        + modeloMasDetallado
        );

        System.out.println(
                "Modelo más consistente: "
                        + modeloMasConsistente
        );

        System.out.println(
                "Modelo más confiable: "
                        + modeloMasConfiable
        );
    }
}
