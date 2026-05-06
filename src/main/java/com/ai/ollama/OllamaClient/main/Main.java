package com.ai.ollama.OllamaClient.main;

import com.ai.ollama.OllamaClient.Context.AgenteConversacional;
import com.ai.ollama.OllamaClient.Context.Llama3Strategy;
import com.ai.ollama.OllamaClient.Context.MistralStrategy;
import com.ai.ollama.OllamaClient.PromptingEngine.Impl.GeneradorPrompt;
import com.ai.ollama.OllamaClient.Strategy.IAStrategy;
import com.ai.ollama.OllamaClient.Template.PromptConfig;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        GeneradorPrompt generador =
                new GeneradorPrompt();

        while (true) {

            System.out.println("""
                    
                    ===== SISTEMA IA MULTIMODELO =====
                    
                    1. Usar Llama3
                    2. Usar Mistral
                    3. Comparar ambos modelos
                    4. Salir
                    """);

            System.out.print("Selecciona una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            if (opcion == 4) {
                System.out.println("Finalizando sistema...");
                break;
            }

            System.out.print("\nEscribe tu pregunta: ");

            String entrada = scanner.nextLine();

            PromptConfig config =
                    generador.generar(entrada);

            switch (opcion) {

                // =====================================
                // LLAMA3
                // =====================================

                case 1 -> {

                    IAStrategy estrategia =
                            new Llama3Strategy();

                    AgenteConversacional agente =
                            new AgenteConversacional(
                                    estrategia
                            );

                    long inicio =
                            System.currentTimeMillis();

                    String respuesta =
                            agente.preguntar(config);

                    long fin =
                            System.currentTimeMillis();

                    System.out.println("""
                            
                            ===== RESPUESTA LLAMA3 =====
                            """);

                    System.out.println(respuesta);

                    System.out.println("""
                            
                            Tiempo de respuesta:
                            """ + (fin - inicio) + " ms");
                }

                // =====================================
                // MISTRAL
                // =====================================

                case 2 -> {

                    IAStrategy estrategia =
                            new MistralStrategy();

                    AgenteConversacional agente =
                            new AgenteConversacional(
                                    estrategia
                            );

                    long inicio =
                            System.currentTimeMillis();

                    String respuesta =
                            agente.preguntar(config);

                    long fin =
                            System.currentTimeMillis();

                    System.out.println("""
                            
                            ===== RESPUESTA MISTRAL =====
                            """);

                    System.out.println(respuesta);

                    System.out.println("""
                            
                            Tiempo de respuesta:
                            """ + (fin - inicio) + " ms");
                }

                // =====================================
                // COMPARAR MODELOS
                // =====================================

                case 3 -> {

                    IAStrategy llama =
                            new Llama3Strategy();

                    IAStrategy mistral =
                            new MistralStrategy();

                    AgenteConversacional agenteLlama =
                            new AgenteConversacional(
                                    llama
                            );

                    AgenteConversacional agenteMistral =
                            new AgenteConversacional(
                                    mistral
                            );

                    // ===== LLAMA3 =====

                    long inicioLlama =
                            System.currentTimeMillis();

                    String respuestaLlama =
                            agenteLlama.preguntar(config);

                    long finLlama =
                            System.currentTimeMillis();

                    // ===== MISTRAL =====

                    long inicioMistral =
                            System.currentTimeMillis();

                    String respuestaMistral =
                            agenteMistral.preguntar(config);

                    long finMistral =
                            System.currentTimeMillis();

                    // =====================================
                    // RESULTADOS
                    // =====================================

                    System.out.println("""
                            
                            ===== RESULTADOS COMPARATIVOS =====
                            """);

                    System.out.println("""
                            
                            --- LLAMA3 ---
                            """);

                    System.out.println(respuestaLlama);

                    System.out.println("""
                            
                            Tiempo:
                            """ +
                            (finLlama - inicioLlama)
                            + " ms");

                    System.out.println("""
                            
                            --- MISTRAL ---
                            """);

                    System.out.println(respuestaMistral);

                    System.out.println("""
                            
                            Tiempo:
                            """ +
                            (finMistral - inicioMistral)
                            + " ms");

                    // =====================================
                    // ANÁLISIS AUTOMÁTICO
                    // =====================================

                    System.out.println("""
                            
                            ===== ANÁLISIS =====
                            """);

                    if ((finLlama - inicioLlama)
                            < (finMistral - inicioMistral)) {

                        System.out.println("""
                                Llama3 respondió más rápido
                                en este equipo.
                                """);

                    } else {

                        System.out.println("""
                                Mistral respondió más rápido
                                en este equipo.
                                """);
                    }

                    if (respuestaLlama.length()
                            > respuestaMistral.length()) {

                        System.out.println("""
                                Llama3 generó respuestas
                                más detalladas.
                                """);

                    } else {

                        System.out.println("""
                                Mistral generó respuestas
                                más compactas.
                                """);
                    }
                }

                default ->

                        System.out.println("""
                                Opción inválida.
                                """);
            }
        }

        scanner.close();
    }
}
