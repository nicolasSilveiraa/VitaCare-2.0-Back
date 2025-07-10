
package org.vitacare.model.Converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.vitacare.model.Enum.SexoDoPaciente;

@Converter(autoApply = true) // Aplica este conversor automaticamente para todos os campos do tipo SexoDoPaciente
public class SexoDoPacienteConverter implements AttributeConverter<SexoDoPaciente, String> {

    @Override
    public String convertToDatabaseColumn(SexoDoPaciente sexo) {
        // Converte o Enum para a String que será salva no banco
        if (sexo == null) {
            return null;
        }
        return sexo.getValor(); // Usa o nosso novo campo "valor"
    }

    @Override
    public SexoDoPaciente convertToEntityAttribute(String valorDoBanco) {
        // Converte a String do banco de volta para o Enum
        if (valorDoBanco == null) {
            return null;
        }
        return SexoDoPaciente.fromValor(valorDoBanco); // Usa nosso método estático
    }
}