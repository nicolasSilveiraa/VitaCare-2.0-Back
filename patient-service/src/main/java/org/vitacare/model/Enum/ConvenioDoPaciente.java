package org.vitacare.model.Enum;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum ConvenioDoPaciente {
    PARTICULAR("Particular"),
    PUBLICO("Publico");

    private final String valor;

    ConvenioDoPaciente(String valor) {
        this.valor = valor;
    }
    // Método para permitir a conversão de String para Enum (útil no conversor)
    public static ConvenioDoPaciente fromValor(String valor) {
        for (ConvenioDoPaciente convenio : ConvenioDoPaciente.values()) {
            if (convenio.valor.equalsIgnoreCase(valor)) {
                return convenio;
            }
        }
        throw new IllegalArgumentException("Convenio invalido: " + valor);
    }

    // Anotação para garantir que APIs REST usem o valor formatado
    @JsonValue
    public String getValor() {
        return valor;
    }
}
