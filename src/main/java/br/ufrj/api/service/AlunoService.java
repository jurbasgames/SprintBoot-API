package br.ufrj.api.service;

import br.ufrj.api.model.Aluno;
import br.ufrj.api.model.SituacaoMatricula;
import br.ufrj.api.repository.AlunoRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {
    private final AlunoRepository alunoRepository;
    private final SituacaoMatriculaService situacaoMatriculaService;

    public AlunoService(AlunoRepository alunoRepository, SituacaoMatriculaService situacaoMatriculaService) {
        this.alunoRepository = alunoRepository;
        this.situacaoMatriculaService = situacaoMatriculaService;
    }
    public Aluno getAlunoByMatricula(String matricula){
        Optional<Aluno> optionalAluno = alunoRepository.findAlunoByMatricula(matricula);
        if(optionalAluno.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Aluno não encontrado.");
        }
        return optionalAluno.get();
    }
    public List<Aluno> findAll(){
        return alunoRepository.findAll();
    }
    public Aluno novoAluno(String nome, String matricula, String hobbies) {
        if (nome.isEmpty() || matricula.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Campos nome e matrícula são obrigatórios.");
        }
        if (alunoRepository.findAlunoByMatricula(matricula).isPresent()){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Esta matrícula já está em uso.");
        }
        Aluno aluno = new Aluno();
        aluno.setNome(nome);
        aluno.setHobbies(hobbies);
        aluno.setMatricula(matricula);
        SituacaoMatricula ativo = situacaoMatriculaService.findByCodigo(SituacaoMatricula.ATIVO);
        aluno.setSituacaoMatricula(ativo);
        return alunoRepository.save(aluno);
    }
    @Transactional
    public String deleteAluno(String matricula){
        Aluno aluno = this.getAlunoByMatricula(matricula);
        alunoRepository.delete(aluno);
        return "Aluno deletado com sucesso.";
    }
    public Aluno updateAluno(String matriculaAtual, String nome, String matricula, String hobbies){
        if (nome.isEmpty() || matricula.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Campos nome e matrícula são obrigatórios.");
        }
        if (alunoRepository.findAlunoByMatricula(matricula).isPresent()){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Esta matrícula já está em uso.");
        }
        Aluno aluno = this.getAlunoByMatricula(matriculaAtual);
        aluno.setNome(nome);
        aluno.setMatricula(matricula);
        aluno.setHobbies(hobbies);
        return alunoRepository.save(aluno);
    }
    public Aluno cancelarMatricula(String matricula){
        Aluno aluno = this.getAlunoByMatricula(matricula);
        SituacaoMatricula cancelado = situacaoMatriculaService.findByCodigo(SituacaoMatricula.CANCELADO);
        aluno.setSituacaoMatricula(cancelado);
        return alunoRepository.save(aluno);
    }
}
