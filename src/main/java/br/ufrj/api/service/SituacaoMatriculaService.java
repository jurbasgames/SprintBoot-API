package br.ufrj.api.service;

import br.ufrj.api.model.SituacaoMatricula;
import br.ufrj.api.repository.SituacaoMatriculaRepository;
import org.springframework.stereotype.Service;

@Service
public class SituacaoMatriculaService {
    private final SituacaoMatriculaRepository situacaoMatriculaRepository;
    public SituacaoMatriculaService(SituacaoMatriculaRepository situacaoMatriculaRepository) {
        this.situacaoMatriculaRepository = situacaoMatriculaRepository;
    }

public SituacaoMatricula findByCodigo(String codigo){
        return situacaoMatriculaRepository.findByCodigo(codigo);
}

}
