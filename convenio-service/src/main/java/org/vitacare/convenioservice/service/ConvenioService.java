package org.vitacare.convenioservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.vitacare.convenioservice.model.Especialidade;
import org.vitacare.convenioservice.model.Planos;
import org.vitacare.convenioservice.repository.EspecialidadeRepository;
import org.vitacare.convenioservice.repository.PlanoRepository;
import org.vitacare.dtos.healthplan.ConvenioComPlanosRequest;
import org.vitacare.dtos.healthplan.ConvenioRequest;
import org.vitacare.dtos.healthplan.ConvenioComPlanosResponse;
import org.vitacare.dtos.healthplan.PlanoComEspecialidadesResponse;
import org.vitacare.convenioservice.exceptions.convenioExceptions.ConvenioCadastradoExceptions;
import org.vitacare.convenioservice.exceptions.convenioExceptions.ConvenioNaoExisteException;
import org.vitacare.convenioservice.model.ConvenioModel;
import org.vitacare.convenioservice.repository.ConvenioRepository;
import org.vitacare.dtos.healthplan.EspecialidadeEnum;
import org.vitacare.dtos.healthplan.PlanosRequest;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ConvenioService {

    private final ConvenioRepository convenio;
    private final ObjectMapper objectMapper;
    private final PlanoRepository planoRepository;
    private final EspecialidadeRepository especialidadeRepository;


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
        Boolean byCnpj = convenio.existsByCnpjConvenio(convenioRequest.cnpjConvenio());
        if (byCnpj) {
            throw new ConvenioCadastradoExceptions();
        }
    }

    public void cadastrarConvenio(ConvenioRequest convenioRequest) throws Exception{
        verificarConvenioCadastrado(convenioRequest);
        ConvenioModel convenioModel = objectMapper.convertValue(convenioRequest, ConvenioModel.class);
        convenio.save(convenioModel);
    }

    public void cadastrarConvenioComPlanos(ConvenioComPlanosRequest request) throws Exception {
        ConvenioModel convenioModel = new ConvenioModel();
        convenioModel.setNomeConvenio(request.nomeConvenio());
        convenioModel.setCnpjConvenio(request.cnpjConvenio());

        List<Planos> listaPlanos = request.planos().stream().map(planoReq -> {
            Planos plano = new Planos();
            plano.setNome(planoReq.nome());
            plano.setConvenioModel(convenioModel);

            List<EspecialidadeEnum> especialidadesEnum = planoReq.especialidades() != null
                    ? planoReq.especialidades()
                    : Collections.<EspecialidadeEnum>emptyList();

            List<Especialidade> especialidades = especialidadesEnum.stream()
                    .map(enumNome -> especialidadeRepository.findByNome(enumNome)
                            .orElseGet(() -> {
                                Especialidade novaEspecialidade = new Especialidade();
                                novaEspecialidade.setNome(enumNome);
                                return especialidadeRepository.save(novaEspecialidade);
                            }))
                    .toList();

            plano.setEspecialidades(especialidades);
            return plano;
        }).toList();

        convenioModel.setPlanos(listaPlanos);
        convenio.save(convenioModel);
    }


    public void alterarConvenioComPlanos(Long idConvenio, ConvenioComPlanosRequest request) throws Exception {
        verificarConvenioExiste(idConvenio);
        ConvenioModel convenioModel = convenio.getReferenceById(idConvenio);

        convenioModel.setNomeConvenio(request.nomeConvenio());
        convenioModel.setCnpjConvenio(request.cnpjConvenio());

        // Limpar planos existentes
        convenioModel.getPlanos().clear();

        // Criar novos planos com especialidades
        List<Planos> novosPlanos = request.planos().stream().map(planoReq -> {
            Planos plano = new Planos();
            plano.setNome(planoReq.nome());
            plano.setConvenioModel(convenioModel);

            // Processar especialidades (igual ao método de cadastro)
            List<EspecialidadeEnum> especialidadesEnum = planoReq.especialidades() != null
                    ? planoReq.especialidades()
                    : Collections.<EspecialidadeEnum>emptyList();

            List<Especialidade> especialidades = especialidadesEnum.stream()
                    .map(enumNome -> especialidadeRepository.findByNome(enumNome)
                            .orElseGet(() -> {
                                Especialidade novaEspecialidade = new Especialidade();
                                novaEspecialidade.setNome(enumNome);
                                return especialidadeRepository.save(novaEspecialidade);
                            }))
                    .toList();

            plano.setEspecialidades(especialidades);
            return plano;
        }).toList();

        convenioModel.setPlanos(novosPlanos);
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

    public ConvenioComPlanosResponse buscarConvenioComPlanosResponse(Long id) throws Exception {
        verificarConvenioExiste(id);
        ConvenioModel convenioModel = convenio.findById(id).get();
        
        List<PlanoComEspecialidadesResponse> planosResponse = convenioModel.getPlanos().stream()
            .map(plano -> new PlanoComEspecialidadesResponse(
                plano.getId(),
                plano.getNome(),
                plano.getEspecialidades().stream()
                    .map(Especialidade::getNome)
                    .toList()
            ))
            .toList();
        
        return new ConvenioComPlanosResponse(
            convenioModel.getIdConvenio(),
            convenioModel.getNomeConvenio(),
            convenioModel.getCnpjConvenio(),
            planosResponse
        );
    }
}
