package org.vitacare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.vitacare.model.ConvenioModel;

@Repository
public interface ConvenioRepository extends JpaRepository<ConvenioModel, Long> {

    Boolean existsByCnpjConvenio(String cnpj);


}
