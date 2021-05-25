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
public class DisOfe {

    private int cadastroAlunoDisciplinaId;
    private int cadastroDisciplinaId;
    private int codGrade;
    private int anoletivo;
    private int semestre;

    public DisOfe() {
    }

    public DisOfe(int cadastroAlunoDisciplinaId, int cadastroDisciplinaId, int codGrade, int anoletivo, int semestre) {
        this.cadastroAlunoDisciplinaId = cadastroAlunoDisciplinaId;
        this.cadastroDisciplinaId = cadastroDisciplinaId;
        this.codGrade = codGrade;
        this.anoletivo = anoletivo;
        this.semestre = semestre;
    }

    public DisOfe(int cadastroDisciplinaId, int anoletivo, int semestre) {
        this.cadastroDisciplinaId = cadastroDisciplinaId;
        this.anoletivo = anoletivo;
        this.semestre = semestre;
    }

    public DisOfe(int cadastroAlunoDisciplinaId, int codGrade, int anoletivo, int semestre) {
        this.cadastroAlunoDisciplinaId = cadastroAlunoDisciplinaId;
        this.codGrade = codGrade;
        this.anoletivo = anoletivo;
        this.semestre = semestre;
    }
    

    public int getCadastroAlunoDisciplinaId() {
        return cadastroAlunoDisciplinaId;
    }

    public void setCadastroAlunoDisciplinaId(int cadastroAlunoDisciplinaId) {
        this.cadastroAlunoDisciplinaId = cadastroAlunoDisciplinaId;
    }

    public int getCadastroDisciplinaId() {
        return cadastroDisciplinaId;
    }

    public void setCadastroDisciplinaId(int cadastroDisciplinaId) {
        this.cadastroDisciplinaId = cadastroDisciplinaId;
    }

    public int getCodGrade() {
        return codGrade;
    }

    public void setCodGrade(int codGrade) {
        this.codGrade = codGrade;
    }

    public int getAnoletivo() {
        return anoletivo;
    }

    public void setAnoletivo(int anoletivo) {
        this.anoletivo = anoletivo;
    }

    public int getSemestre() {
        return semestre;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }

}
