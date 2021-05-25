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
public class DisMSgrid {
    private int codgrade;
    private String tipoRegOuv;
    private String labelData;
    private String labelHora;
    private int coddis;
    private String disciplina;
    private String professor;
    private Short credito;

    public DisMSgrid() {
    }

    public DisMSgrid(int codgrade, String tipoRegOuv, String labelData, String labelHora, int coddis, String disciplina, String professor, Short credito) {
        this.codgrade = codgrade;
        this.tipoRegOuv = tipoRegOuv;
        this.labelData = labelData;
        this.labelHora = labelHora;
        this.coddis = coddis;
        this.disciplina = disciplina;
        this.professor = professor;
        this.credito = credito;
    }

    public DisMSgrid(int codgrade, String labelData, String labelHora, int coddis, String disciplina, String professor, Short credito) {
        this.codgrade = codgrade;
        this.labelData = labelData;
        this.labelHora = labelHora;
        this.coddis = coddis;
        this.disciplina = disciplina;
        this.professor = professor;
        this.credito = credito;
    }

    public int getCodgrade() {
        return codgrade;
    }

    public void setCodgrade(int codgrade) {
        this.codgrade = codgrade;
    }

    public String getTipoRegOuv() {
        return tipoRegOuv;
    }

    public void setTipoRegOuv(String tipoRegOuv) {
        this.tipoRegOuv = tipoRegOuv;
    }

    public String getLabelData() {
        return labelData;
    }

    public void setLabelData(String labelData) {
        this.labelData = labelData;
    }

    public String getLabelHora() {
        return labelHora;
    }

    public void setLabelHora(String labelHora) {
        this.labelHora = labelHora;
    }

    public int getCoddis() {
        return coddis;
    }

    public void setCoddis(int coddis) {
        this.coddis = coddis;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public Short getCredito() {
        return credito;
    }

    public void setCredito(Short credito) {
        this.credito = credito;
    }

    
}
