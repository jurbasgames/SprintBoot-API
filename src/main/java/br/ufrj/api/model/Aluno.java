package br.ufrj.api.model;

import br.ufrj.api.repository.SituacaoMatriculaRepository;
import br.ufrj.api.service.SituacaoMatriculaService;
import jakarta.persistence.*;

@Entity
public class Aluno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String matricula;
    private String hobbies;
    @ManyToOne
    @JoinColumn(name = "situacaoMatriculaId")
    private SituacaoMatricula situacaoMatricula;

    public Aluno(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getHobbies() {
        return hobbies;
    }

    public void setHobbies(String hobbies) {
        this.hobbies = hobbies;
    }

    public SituacaoMatricula getSituacaoMatricula() {
        return situacaoMatricula;
    }

    public void setSituacaoMatricula(SituacaoMatricula situacaoMatricula) {
        this.situacaoMatricula = situacaoMatricula;
    }

    public Aluno(String nome, String matricula, String hobbies, SituacaoMatricula situacaoMatricula) {
        this.nome = nome;
        this.matricula = matricula;
        this.hobbies = hobbies;
        this.situacaoMatricula = situacaoMatricula;
    }
}
