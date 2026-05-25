package com.ai.ollama.OllamaClient.main;

// =====================================
// IMPORTACIÓN DE CONTEXTO
// =====================================

import com.ai.ollama.OllamaClient.Context.AgenteConversacional;
import com.ai.ollama.OllamaClient.Context.ModeloStrategy;

// =====================================
// IMPORTACIÓN DE MÉTRICAS
// =====================================

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
// Sistema multimodelo con:
//
// - Strategy Pattern
// - Prompt Engineering
// - Benchmarking
// - Evaluation Metrics
// - Intent Routing
// - Arquitectura desacoplada
//
// =====================================

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // =====================================
        // CLIENTE PRINCIPAL DE OLLAMA
        // =====================================
        //
        // Esta clase centraliza toda la
        // comunicación HTTP con Ollama.
        //
        // Gracias a esto:
        //
        // - evitamos duplicación
        // - reducimos acoplamiento
        // - reutilizamos conexión
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
            // SALIR DEL SISTEMA
            // =====================================

            if (opcion == 5) {

                System.out.println("""

                        Cerrando sistema...
                        """);

                break;
            }

            // =====================================
            // INPUT DEL USUARIO
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
            // Esta clase detecta:
            //
            // - intención
            // - rol
            // - instrucciones
            //
            // y construye automáticamente
            // una configuración contextual.
            //
            // =====================================

            GeneradorPrompt generador =
                    new GeneradorPrompt();

            PromptConfig config =
                    generador.generar(
                            pregunta
                    );

            // =====================================
            // ROUTER AUTOMÁTICO
            // =====================================
            //
            // El sistema detecta automáticamente
            // qué técnica de Prompt Engineering
            // conviene utilizar.
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
    // EJECUCIÓN PRINCIPAL DEL SISTEMA
    // =====================================

    public static void ejecutarSistema(

            int opcion,

            PromptConfig config,

            PromptStrategy promptStrategy,

            OllamaClient cliente
    ) {

        // =====================================
        // PROMPT BUILDER
        // =====================================
        //
        // Construcción dinámica y modular
        // del prompt final.
        //
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
        // SWITCH PRINCIPAL
        // =====================================
        //
        // Gracias a ModeloStrategy:
        //
        // - eliminamos duplicación
        // - reducimos clases innecesarias
        // - hacemos el sistema escalable
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
        // AGENTE CONVERSACIONAL
        // =====================================
        //
        // El agente depende de la
        // abstracción IAStrategy y NO
        // de implementaciones concretas.
        //
        // Esto aplica:
        //
        // - Dependency Inversion
        // - Polimorfismo
        // - Bajo acoplamiento
        //
        // =====================================

        AgenteConversacional agente =
                new AgenteConversacional(
                        estrategia
                );

        // =====================================
        // SISTEMAS DE EVALUACIÓN
        // =====================================

        ResponseEvaluator evaluator =
                new ResponseEvaluator();

        HallucinationDetector detector =
                new HallucinationDetector();

        // =====================================
        // MEDICIÓN DE LATENCIA
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
        // PALABRAS CLAVE
        // =====================================

        String[] keywords = {

                "java",
                "software",
                "arquitectura",
                "clase",
                "objeto"
        };

        // =====================================
        // MÉTRICAS DE EVALUACIÓN
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

        // =====================================
        // CONSISTENCY SCORE
        // =====================================
        //
        // Se vuelve a ejecutar el prompt
        // para comparar estabilidad
        // entre respuestas.
        //
        // =====================================

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

            PromptStrategy promptStrategy,

            OllamaClient cliente
    ) {

        // =====================================
        // ARREGLO POLIMÓRFICO
        // =====================================
        //
        // Todas las estrategias comparten
        // la misma abstracción IAStrategy.
        //
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
        // BENCHMARK MULTIMODELO
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