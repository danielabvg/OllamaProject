package com.ai.ollama.OllamaClient.main;

import com.ai.ollama.OllamaClient.Context.*;
import com.ai.ollama.OllamaClient.PromptingEngine.Impl.GeneradorPrompt;
import com.ai.ollama.OllamaClient.Strategy.IAStrategy;
import com.ai.ollama.OllamaClient.Template.PromptConfig;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner =
                new Scanner(System.in);

        GeneradorPrompt generador =
                new GeneradorPrompt();

        while (true) {

            System.out.println("""
                    
                    ===== IA MULTIMODELO =====
                    
                    1. Llama3
                    2. Mistral
                    3. Phi3 Mini
                    4. Comparar TODOS
                    5. Salir
                    """);

            System.out.print(
                    "Selecciona opción: "
            );

            int opcion =
                    scanner.nextInt();

            scanner.nextLine();

            if (opcion == 5) {
                break;
            }

            System.out.print(
                    "\nEscribe tu pregunta: "
            );

            String entrada =
                    scanner.nextLine();

            PromptConfig config =
                    generador.generar(entrada);

            switch (opcion) {

                case 1 -> ejecutarModelo(
                        new Llama3Strategy(),
                        config
                );

                case 2 -> ejecutarModelo(
                        new MistralStrategy(),
                        config
                );

                case 3 -> ejecutarModelo(
                        new Phi3Strategy(),
                        config
                );

                case 4 -> compararModelos(config);

                default ->
                        System.out.println(
                                "Opción inválida."
                        );
            }
        }

        scanner.close();
    }

    // =====================================
    // EJECUTAR MODELO
    // =====================================

    public static void ejecutarModelo(
            IAStrategy estrategia,
            PromptConfig config
    ) {

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

        System.out.println(
                "\n===== "
                        + estrategia.getNombreModelo()
                        + " ====="
        );

        System.out.println(respuesta);

        System.out.println(
                "\nTiempo: "
                        + (fin - inicio)
                        + " ms"
        );
    }

    // =====================================
    // COMPARACIÓN
    // =====================================

    public static void compararModelos(
            PromptConfig config
    ) {

        IAStrategy[] modelos = {
                new Llama3Strategy(),
                new MistralStrategy(),
                new Phi3Strategy()
        };

        long mejorTiempo =
                Long.MAX_VALUE;

        String modeloMasRapido = "";

        int respuestaMasLarga = 0;
        String modeloMasDetallado = "";

        for (IAStrategy estrategia : modelos) {

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

            long tiempo =
                    fin - inicio;

            System.out.println(
                    "\n===== "
                            + estrategia.getNombreModelo()
                            + " ====="
            );

            System.out.println(respuesta);

            System.out.println(
                    "\nTiempo: "
                            + tiempo + " ms"
            );

            if (tiempo < mejorTiempo) {

                mejorTiempo = tiempo;

                modeloMasRapido =
                        estrategia.getNombreModelo();
            }

            if (respuesta.length()
                    > respuestaMasLarga) {

                respuestaMasLarga =
                        respuesta.length();

                modeloMasDetallado =
                        estrategia.getNombreModelo();
            }
        }

        // =====================================
        // ANÁLISIS
        // =====================================

        System.out.println("""
                
                ===== ANÁLISIS FINAL =====
                """);

        System.out.println(
                "Modelo más rápido: "
                        + modeloMasRapido
        );

        System.out.println(
                "Modelo más detallado: "
                        + modeloMasDetallado
        );
    }
}
