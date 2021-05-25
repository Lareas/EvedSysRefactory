/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author ticoa
 */
public class GraDisAluPro {
    private Short codGrade;
    private Short cadastroAlunoDisciplinaId;
    private String programa;
    private String aluno;
    private int dadoCadastroGeralId;
    private Float media;
    private Short falta;
    private String situacao;
    private String frequencia;
    private String tipo;

    public GraDisAluPro(Short codGrade, Short cadastroAlunoDisciplinaId, String programa, String aluno, Float media, Short falta, String situacao, String frequencia) {
        this.codGrade = codGrade;
        this.cadastroAlunoDisciplinaId = cadastroAlunoDisciplinaId;
        this.programa = programa;
        this.aluno = aluno;
        this.media = media;
        this.falta = falta;
        this.situacao = situacao;
        this.frequencia = frequencia;
        
    }

    public GraDisAluPro(String programa, String aluno, Float media, Short falta, String situacao, String frequencia, Short cadastroAlunoDisciplinaId, int dadoCadastroGeralId) {
        this.programa = programa;
        this.aluno = aluno;
        this.media = media;
        this.falta = falta;
        this.situacao = situacao;
        this.frequencia = frequencia;
        this.cadastroAlunoDisciplinaId = cadastroAlunoDisciplinaId;
        this.dadoCadastroGeralId = dadoCadastroGeralId;
    }
    
    public GraDisAluPro(String programa, String aluno, Float media, Short falta, String situacao, String frequencia, Short cadastroAlunoDisciplinaId, int dadoCadastroGeralId, String tipo) {
        this.programa = programa;
        this.aluno = aluno;
        this.media = media;
        this.falta = falta;
        this.situacao = situacao;
        this.frequencia = frequencia;
        this.cadastroAlunoDisciplinaId = cadastroAlunoDisciplinaId;
        this.dadoCadastroGeralId = dadoCadastroGeralId;
        this.tipo = tipo;
    }

    public int getDadoCadastroGeralId() {
        return dadoCadastroGeralId;
    }

    public void setDadoCadastroGeralId(int dadoCadastroGeralId) {
        this.dadoCadastroGeralId = dadoCadastroGeralId;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    
    public Short getCodGrade() {
        return codGrade;
    }

    public void setCodGrade(Short codGrade) {
        this.codGrade = codGrade;
    }

    public Short getCadastroAlunoDisciplinaId() {
        return cadastroAlunoDisciplinaId;
    }

    public void setCadastroAlunoDisciplinaId(Short cadastroAlunoDisciplinaId) {
        this.cadastroAlunoDisciplinaId = cadastroAlunoDisciplinaId;
    }

    public String getPrograma() {
        return programa;
    }

    public void setPrograma(String programa) {
        this.programa = programa;
    }

    public String getAluno() {
        return aluno;
    }

    public void setAluno(String aluno) {
        this.aluno = aluno;
    }

    public Float getMedia() {
        return media;
    }

    public void setMedia(Float media) {
        this.media = media;
    }

    public Short getFalta() {
        return falta;
    }

    public void setFalta(Short falta) {
        this.falta = falta;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public String getFrequencia() {
        return frequencia;
    }

    public void setFrequencia(String frequencia) {
        this.frequencia = frequencia;
    }


    
}
