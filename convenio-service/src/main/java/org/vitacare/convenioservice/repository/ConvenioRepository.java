package org.vitacare.convenioservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.vitacare.convenioservice.model.ConvenioModel;

@Repository
public interface ConvenioRepository extends JpaRepository<ConvenioModel, Long> {

    Boolean existsByCnpjConvenio(String cnpj);


}
