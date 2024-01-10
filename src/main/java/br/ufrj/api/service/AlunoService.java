package br.ufrj.api.service;

import br.ufrj.api.model.Aluno;
import br.ufrj.api.repository.AlunoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class AlunoService {
    private final AlunoRepository alunoRepository;

    public AlunoService(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }
    public Aluno getAlunoByMatricula(String matricula){
        Optional<Aluno> optionalAluno = alunoRepository.findAlunoByMatricula(matricula);
        if(optionalAluno.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Aluno não encontrado.");
        }
        return optionalAluno.get();
    }
}
