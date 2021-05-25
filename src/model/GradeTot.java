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
public class GradeTot {

    private int cadastroDisciplinaId;
    private int codGrade;
    private int totAlunos;
    

    public GradeTot() {
    }

    public GradeTot(int cadastroDisciplinaId, int codGrade, int totAlunos) {
        this.cadastroDisciplinaId = cadastroDisciplinaId;
        this.codGrade = codGrade;
        this.totAlunos = totAlunos;
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

    public int getTotAlunos() {
        return totAlunos;
    }

    public void setTotAlunos(int totAlunos) {
        this.totAlunos = totAlunos;
    }
    

}
