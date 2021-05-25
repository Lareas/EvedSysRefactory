/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author ticoa
 */
public class GridDisMatriSem {
    private SimpleStringProperty tipoRegOuv;
    private String labelData;
    private String labelHora;
    private String disciplina;
    private String professor1;
    private Short credito;

    public GridDisMatriSem(SimpleStringProperty tipoRegOuv, String labelData, String labelHora, String disciplina, String professor1, Short credito) {
        this.tipoRegOuv = tipoRegOuv;
        this.labelData = labelData;
        this.labelHora = labelHora;
        this.disciplina = disciplina;
        this.professor1 = professor1;
        this.credito = credito;
    }

    public GridDisMatriSem() {
    }

    public String getTipoRegOuv() {
        return tipoRegOuv.get();
    }

    public void setTipoRegOuv(String tipoRegOuv) {
        this.tipoRegOuv = new SimpleStringProperty(tipoRegOuv);
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

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    public String getProfessor1() {
        return professor1;
    }

    public void setProfessor1(String professor1) {
        this.professor1 = professor1;
    }

    public Short getCredito() {
        return credito;
    }

    public void setCredito(Short credito) {
        this.credito = credito;
    }
    
    
    
}
