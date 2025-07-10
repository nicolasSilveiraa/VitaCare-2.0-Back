// src/main/java/org/vitacare/model/Enum/SexoDoPaciente.java
package org.vitacare.model.Enum;

import com.fasterxml.jackson.annotation.JsonValue; // Opcional, mas bom para APIs REST
import lombok.Getter;

@Getter // Lombok para gerar o getter para o campo "valor"
public enum SexoDoPaciente {
    MASCULINO("Masculino"),
    FEMININO("Feminino"),
    OUTRO("Outro");

    private final String valor;

    SexoDoPaciente(String valor) {
        this.valor = valor;
    }

    // Método para permitir a conversão de String para Enum (útil no conversor)
    public static SexoDoPaciente fromValor(String valor) {
        for (SexoDoPaciente sexo : SexoDoPaciente.values()) {
            if (sexo.valor.equalsIgnoreCase(valor)) {
                return sexo;
            }
        }
        throw new IllegalArgumentException("Sexo inválido: " + valor);
    }

    // Anotação para garantir que APIs REST usem o valor formatado
    @JsonValue
    public String getValor() {
        return valor;
    }
}