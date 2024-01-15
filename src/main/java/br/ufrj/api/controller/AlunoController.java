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
@CrossOrigin
@RequestMapping("/aluno")
public class AlunoController {
    private final AlunoService alunoService;

    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }
    @GetMapping("")
    public List<Aluno> findAll(){
        return alunoService.findAll();
    }

    @GetMapping("/{matricula}")
    public Aluno getAlunoByMatricula(@PathVariable String matricula){
        return alunoService.getAlunoByMatricula(matricula);

    }
    @PostMapping(path = "/novo", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public Aluno createAluno(@RequestParam String nome, @RequestParam String matricula, @RequestParam String hobbies){
        return alunoService.novoAluno(nome, matricula, hobbies);
    }
    @DeleteMapping("/{matricula}")
    public String deleteAluno(@PathVariable String matricula){
        return alunoService.deleteAluno(matricula);
    }
    @PutMapping(path = "/{matriculaAtual}", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public Aluno updateAluno(@PathVariable String matriculaAtual, @RequestParam String nome, @RequestParam String matricula, @RequestParam String hobbies){
        return alunoService.updateAluno(matriculaAtual, nome, matricula, hobbies);
    }
    @PutMapping(path = "/cancelarMatricula/{matriculaAtual}")
    public Aluno cancelarMatricula(@PathVariable String matriculaAtual){
        return alunoService.cancelarMatricula(matriculaAtual);
    }
}
