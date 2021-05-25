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
public class DataCrudMS {

    private int cadastroAlunoDisciplinaId;
    private Cadastrodisciplina nomeCadDis;
    private Dadocadastroprograma nomeProg;
    private Short cadastroAlunoDisciplinaSituacaoId;
    private Short frequenciaId;
    private EsMatrisem nomeMatriSem;
    private String tipoRegOuv;

    public DataCrudMS() {
    }

    public int getCadastroAlunoDisciplinaId() {
        return cadastroAlunoDisciplinaId;
    }

    public void setCadastroAlunoDisciplinaId(int cadastroAlunoDisciplinaId) {
        this.cadastroAlunoDisciplinaId = cadastroAlunoDisciplinaId;
    }

    public Cadastrodisciplina getNomeCadDis() {
        return nomeCadDis;
    }

    public void setNomeCadDis(Cadastrodisciplina nomeCadDis) {
        this.nomeCadDis = nomeCadDis;
    }

    public Dadocadastroprograma getNomeProg() {
        return nomeProg;
    }

    public void setNomeProg(Dadocadastroprograma nomeProg) {
        this.nomeProg = nomeProg;
    }

    public Short getCadastroAlunoDisciplinaSituacaoId() {
        return cadastroAlunoDisciplinaSituacaoId;
    }

    public void setCadastroAlunoDisciplinaSituacaoId(Short cadastroAlunoDisciplinaSituacaoId) {
        this.cadastroAlunoDisciplinaSituacaoId = cadastroAlunoDisciplinaSituacaoId;
    }

    public Short getFrequenciaId() {
        return frequenciaId;
    }

    public void setFrequenciaId(Short frequenciaId) {
        this.frequenciaId = frequenciaId;
    }

    public EsMatrisem getNomeMatriSem() {
        return nomeMatriSem;
    }

    public void setNomeMatriSem(EsMatrisem nomeMatriSem) {
        this.nomeMatriSem = nomeMatriSem;
    }

    public String getTipoRegOuv() {
        return tipoRegOuv;
    }

    public void setTipoRegOuv(String tipoRegOuv) {
        this.tipoRegOuv = tipoRegOuv;
    }

}
