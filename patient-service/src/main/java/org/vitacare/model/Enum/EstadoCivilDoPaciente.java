package org.vitacare.model.Enum;

import com.fasterxml.jackson.annotation.JsonValue; // Opcional, mas bom para APIs REST
import lombok.Getter;

@Getter // Lombok para gerar o getter para o campo "valor"
public enum EstadoCivilDoPaciente {
    SOLTEIRO("Solteiro"),
    CASADO("Casado"),
    VIUVO("Viuvo");

    private final String valor;

    EstadoCivilDoPaciente(String valor) {
        this.valor = valor;
    }

    // Método para permitir a conversão de String para Enum (útil no conversor)
    public static EstadoCivilDoPaciente fromValor(String valor) {
        for (EstadoCivilDoPaciente estadocivil : EstadoCivilDoPaciente.values()) {
            if (estadocivil.valor.equalsIgnoreCase(valor)) {
                return estadocivil;
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