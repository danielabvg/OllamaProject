package com.ai.ollama.ollamaclient.utils;

import java.util.Scanner;

// =====================================================
// ADMINISTRADOR CENTRAL DE CONSOLA
// =====================================================
//
// Esta clase centraliza:
//
// - menús
// - lectura de entradas
// - interacción con el usuario
//
// Responsabilidades:
//
// - mostrar menús
// - leer opciones
// - leer preguntas
// - leer prompts manuales
//
// =====================================================

@SuppressWarnings("java:S106")
public class ConsoleManager {

    // =====================================================
    // SCANNER CENTRAL
    // =====================================================

    private final Scanner scanner =
            new Scanner(System.in);

    // =====================================================
    // MENÚ PRINCIPAL
    // =====================================================

    public void mostrarMenuPrincipal() {

        System.out.println("""

                ==================================================
                PLATAFORMA DE IA Y PROMPT ENGINEERING
                ==================================================

                1. Prompt automático
                2. Prompt manual
                3. Salir

                ==================================================
                """);
    }

    // =====================================================
    // MENÚ DE MODELOS
    // =====================================================

    public void mostrarMenuModelos() {

        System.out.println("""

                ==================================================
                SELECCIÓN DE MODELO
                ==================================================

                1. Llama3
                2. Mistral
                3. Phi3 Mini
                4. Benchmark multimodelo

                ==================================================
                """);
    }

    // =====================================================
    // LEER OPCIÓN
    // =====================================================

    public int leerOpcion() {

        System.out.print(
                "Selecciona una opción: "
        );

        int opcion =
                scanner.nextInt();

        scanner.nextLine();

        return opcion;
    }

    // =====================================================
    // LEER PREGUNTA
    // =====================================================

    public String leerPregunta() {

        System.out.println("""

                ==================================================
                ESCRIBE TU PREGUNTA
                ==================================================
                """);

        return scanner.nextLine();
    }

    // =====================================================
    // LEER PROMPT MANUAL
    // =====================================================

    public String leerPromptManual() {

        System.out.println("""

            ==================================================
            INGRESA TU PROMPT MANUAL
            (Escribe FIN en una línea nueva y presiona ENTER para enviar)
            ==================================================
            """);

        StringBuilder prompt = new StringBuilder();

        while (true) {

            String linea = scanner.nextLine();

            if ("FIN".equalsIgnoreCase(linea.trim())) {
                break;
            }

            prompt.append(linea)
                    .append("\n");
        }

        return prompt.toString().trim();
    }

    // =====================================================
    // OBTENER SCANNER
    // =====================================================
    //
    // Permite reutilizar el scanner
    // desde otras clases cuando sea
    // necesario.
    //
    // =====================================================

    public Scanner getScanner() {

        return scanner;
    }

    // =====================================================
    // MENSAJE DE DESPEDIDA
    // =====================================================

    public void mostrarDespedida() {

        System.out.println("""

                ==================================================
                CERRANDO SISTEMA...
                ==================================================
                """);
    }

    // =====================================================
    // OPCIÓN INVÁLIDA
    // =====================================================

    public void mostrarOpcionInvalida() {

        System.out.println("""

                ==================================================
                OPCIÓN INVÁLIDA
                ==================================================
                """);
    }

    // =====================================================
    // CERRAR RECURSOS
    // =====================================================

    public void cerrar() {

        scanner.close();
    }
}