package com.ai.ollama.OllamaClient.Template;

import java.util.ArrayList;
import java.util.List;

public class PromptBuilder {

    private String rol;
    private String instrucciones;
    private List<String> ejemplos = new ArrayList<>();
    private String entradaUsuario;

    public PromptBuilder conRol(String rol) {
        this.rol = rol;
        return this;
    }

    public PromptBuilder conInstrucciones(String instrucciones) {
        this.instrucciones = instrucciones;
        return this;
    }

    public PromptBuilder agregarEjemplo(
            String entrada,
            String salida
    ) {

        ejemplos.add(String.format(
                "<ejemplo>\nEntrada: %s\nSalida: %s\n</ejemplo>",
                entrada,
                salida
        ));

        return this;
    }

    public PromptBuilder conEntrada(
            String entradaUsuario
    ) {

        this.entradaUsuario = entradaUsuario;
        return this;
    }

    public String build() {

        StringBuilder sb = new StringBuilder();

        sb.append("<system>\n");
        sb.append("Eres un: ")
                .append(rol)
                .append("\n");

        sb.append("Instrucciones: ")
                .append(instrucciones)
                .append("\n");

        sb.append("</system>\n");

        if (!ejemplos.isEmpty()) {

            sb.append("<ejemplos>\n");

            ejemplos.forEach(e ->
                    sb.append(e).append("\n"));

            sb.append("</ejemplos>\n");
        }

        sb.append("<user>\n");
        sb.append(entradaUsuario);
        sb.append("\n</user>");

        return sb.toString();
    }
}
