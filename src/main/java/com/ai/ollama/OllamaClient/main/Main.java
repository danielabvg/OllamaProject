package com.ai.ollama.OllamaClient.main;

import com.ai.ollama.OllamaClient.Context.Llama3Strategy ;
import com.ai.ollama.OllamaClient.IntentRouting.IntentRouter ;
import com.ai.ollama.OllamaClient.Strategy.IAStrategy;
import com.ai.ollama.OllamaClient.Template.PromptConfig;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        IntentRouter router = new IntentRouter();
        IAStrategy estrategia = new Llama3Strategy();

        System.out.println("=== Sistema IA Arquitectura Pro ===");
        System.out.println("Escribe 'salir' para terminar\n");

        while (true) {

            System.out.print("Tú: ");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("salir")) break;

            String rol = router.determinarRol(input);
            String instrucciones = router.optimizarInstrucciones(input);

            PromptConfig config = new PromptConfig(
                    rol,
                    instrucciones,
                    input
            );

            String respuesta = estrategia.generarRespuesta(config);

            System.out.println("\nIA:\n" + respuesta + "\n");
        }

        scanner.close();
    }
}
