package br.ufrj.api.controller;

import br.ufrj.api.model.Aluno;
import br.ufrj.api.model.SituacaoMatricula;
import br.ufrj.api.repository.AlunoRepository;
import br.ufrj.api.service.AlunoService;
import br.ufrj.api.service.SituacaoMatriculaService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/aluno")
public class AlunoController {
    private final AlunoRepository alunoRepository;
    private final AlunoService alunoService;
    private final SituacaoMatriculaService situacaoMatriculaService;

    public AlunoController(AlunoRepository alunoRepository, AlunoService alunoService, SituacaoMatriculaService situacaoMatriculaService) {
        this.alunoRepository = alunoRepository;
        this.alunoService = alunoService;
        this.situacaoMatriculaService = situacaoMatriculaService;
    }
    @GetMapping("")
    public List<Aluno> findAll(){
        return alunoRepository.findAll();
    }

    @GetMapping("/{matricula}")
    public Aluno getAlunoByMatricula(@PathVariable String matricula){
        return alunoService.getAlunoByMatricula(matricula);

    }
    @PostMapping(path = "/novo", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public Aluno createAluno(@RequestParam String nome, @RequestParam String matricula, @RequestParam String hobbies){
        Aluno aluno = new Aluno();
        aluno.setNome(nome);
        aluno.setHobbies(hobbies);
        aluno.setMatricula(matricula);
        SituacaoMatricula ativo = situacaoMatriculaService.findByCodigo(SituacaoMatricula.ATIVO);
        aluno.setSituacaoMatricula(ativo);
        return alunoRepository.save(aluno);
    }
    @DeleteMapping("/{matricula}")
    public String deleteAluno(@PathVariable String matricula){
        Aluno aluno = alunoService.getAlunoByMatricula(matricula);
        alunoRepository.delete(aluno);
        return "Aluno deletado";
    }
    @PutMapping(path = "/{matriculaAtual}", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public Aluno updateAluno(@PathVariable String matriculaAtual, @RequestParam String nome, @RequestParam String matricula, @RequestParam String hobbies){
        Aluno aluno = alunoService.getAlunoByMatricula(matriculaAtual);
        aluno.setNome(nome);
        aluno.setMatricula(matricula);
        aluno.setHobbies(hobbies);
        return alunoRepository.save(aluno);
    }
}
