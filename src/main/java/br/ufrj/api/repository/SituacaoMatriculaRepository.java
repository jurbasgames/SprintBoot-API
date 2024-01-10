package br.ufrj.api.repository;

import br.ufrj.api.model.SituacaoMatricula;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SituacaoMatriculaRepository extends JpaRepository<SituacaoMatricula,Long> {

        SituacaoMatricula findByCodigo(String codigo);
}
