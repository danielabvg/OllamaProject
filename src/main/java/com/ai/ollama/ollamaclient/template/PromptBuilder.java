// =====================================
// BUILDER DE PROMPTS
// =====================================
//
// Esta clase implementa el patrón Builder
// para construir prompts estructurados
// de forma dinámica.
//
// Permite configurar:
// - rol
// - instrucciones
// - ejemplos
// - entrada del usuario
//
// Esto mejora reutilización y legibilidad.
// =====================================

package com.ai.ollama.ollamaclient.template;

import java.util.ArrayList;
import java.util.List;

public class PromptBuilder {

    // =====================================
    // COMPONENTES DEL PROMPT
    // =====================================

    private String rol;
    private String instrucciones;
    private final List<String> ejemplos = new ArrayList<>();
    private String entradaUsuario;

    // Configura el rol/persona.

    public PromptBuilder conRol(String rol) {

        this.rol = rol;
        return this;
    }

    // Configura instrucciones principales.

    public PromptBuilder conInstrucciones(String instrucciones) {

        this.instrucciones = instrucciones;
        return this;
    }

    // =====================================
    // FEW-SHOT EXAMPLES
    // =====================================
    //
    // Agrega ejemplos para guiar
    // el comportamiento del modelo.
    // =====================================

    public void agregarEjemplo(
            String entrada,
            String salida
    ) {

        ejemplos.add(
                String.format(
                        "<ejemplo>%nEntrada: %s%nSalida: %s%n</ejemplo>",
                        entrada,
                        salida
                )
        );

    }

    // Configura la entrada del usuario.

    public PromptBuilder conEntrada(
            String entradaUsuario
    ) {

        this.entradaUsuario = entradaUsuario;
        return this;
    }

    // =====================================
    // CONSTRUCCIÓN FINAL DEL PROMPT
    // =====================================
    //
    // Genera el prompt estructurado
    // utilizando delimitadores XML.
    // =====================================

    public String build() {

        StringBuilder sb = new StringBuilder();

        // =====================================
        // SYSTEM PROMPT
        // =====================================

        sb.append("<system>\n");

        sb.append("Eres un: ")
                .append(rol)
                .append("\n");

        sb.append("Instrucciones: ")
                .append(instrucciones)
                .append("\n");

        sb.append("</system>\n");

        // =====================================
        // FEW-SHOT SECTION
        // =====================================

        if (!ejemplos.isEmpty()) {

            sb.append("<ejemplos>\n");

            ejemplos.forEach(e ->
                    sb.append(e).append("\n"));

            sb.append("</ejemplos>\n");
        }

        // =====================================
        // USER INPUT
        // =====================================

        sb.append("<user>\n");
        sb.append(entradaUsuario);
        sb.append("\n</user>");

        return sb.toString();
    }
}
