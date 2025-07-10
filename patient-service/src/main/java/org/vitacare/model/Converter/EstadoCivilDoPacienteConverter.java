
package org.vitacare.model.Converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.vitacare.model.Enum.EstadoCivilDoPaciente;
import org.vitacare.model.Enum.SexoDoPaciente;

@Converter(autoApply = true) // Aplica este conversor automaticamente para todos os campos do tipo SexoDoPaciente
public class EstadoCivilDoPacienteConverter implements AttributeConverter<EstadoCivilDoPaciente, String> {

    @Override
    public String convertToDatabaseColumn(EstadoCivilDoPaciente estadocivil) {
        // Converte o Enum para a String que será salva no banco
        if (estadocivil == null) {
            return null;
        }
        return estadocivil.getValor(); // Usa o nosso novo campo "valor"
    }

    @Override
    public EstadoCivilDoPaciente convertToEntityAttribute(String valorDoBanco) {
        // Converte a String do banco de volta para o Enum
        if (valorDoBanco == null) {
            return null;
        }
        return EstadoCivilDoPaciente.fromValor(valorDoBanco); // Usa nosso método estático
    }
}