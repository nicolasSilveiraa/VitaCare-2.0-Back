
package org.vitacare.model.Converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.vitacare.model.Enum.ConvenioDoPaciente;
import org.vitacare.model.Enum.EstadoCivilDoPaciente;
import org.vitacare.model.Enum.SexoDoPaciente;

@Converter(autoApply = true) // Aplica este conversor automaticamente para todos os campos do tipo SexoDoPaciente
public class ConvenioDoPacienteConverter implements AttributeConverter<ConvenioDoPaciente, String> {

    @Override
        public String convertToDatabaseColumn(ConvenioDoPaciente convenio) {
        // Converte o Enum para a String que será salva no banco
        if (convenio == null) {
            return null;
        }
        return convenio.getValor(); // Usa o nosso novo campo "valor"
    }

    @Override
    public ConvenioDoPaciente convertToEntityAttribute(String valorDoBanco) {
        // Converte a String do banco de volta para o Enum
        if (valorDoBanco == null) {
            return null;
        }
        return ConvenioDoPaciente.fromValor(valorDoBanco); // Usa nosso método estático
    }
}