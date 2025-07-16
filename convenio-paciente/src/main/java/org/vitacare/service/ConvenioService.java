package org.vitacare.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.vitacare.dto.request.ConvenioRequest;
import org.vitacare.exceptions.convenioExceptions.ConvenioCadastradoExceptions;
import org.vitacare.exceptions.convenioExceptions.ConvenioNaoExisteException;
import org.vitacare.model.ConvenioModel;
import org.vitacare.repository.ConvenioRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConvenioService {

    private final ConvenioRepository convenio;
    private final ObjectMapper objectMapper;


    public List<ConvenioModel> listaConvenio() {
        return convenio.findAll();
    }

    public ConvenioModel buscarConvenioPorId(Long id) throws Exception{
        verificarConvenioExiste(id);
        return convenio.findById(id).get();
    }

    public void verificarConvenioExiste(Long id) throws Exception {
        if (!convenio.existsById(id)) {
            throw new ConvenioNaoExisteException();
        }
    }

    public void verificarConvenioCadastrado(ConvenioRequest convenioRequest) throws Exception{
        Boolean byCnpj = convenio.existsByCnpjConvenio(convenioRequest.getCnpjConvenio());
        if (byCnpj) {
            throw new ConvenioCadastradoExceptions();
        }
    }

    public void cadastrarConvenio(ConvenioRequest convenioRequest) throws Exception{
        verificarConvenioCadastrado(convenioRequest);
        ConvenioModel convenioModel = objectMapper.convertValue(convenioRequest, ConvenioModel.class);
        convenio.save(convenioModel);
    }

    public void atualizarConvenio(Long id, ConvenioRequest convenioRequest) throws Exception {
        verificarConvenioExiste(id);
        ConvenioModel convenioModel = objectMapper.convertValue(convenioRequest, ConvenioModel.class);
        convenioModel.setIdConvenio(id);
        convenio.save(convenioModel);
    }

    public void deletarConvenio(Long id) throws Exception {
        verificarConvenioExiste(id);
        convenio.deleteById(id);
    }


}
