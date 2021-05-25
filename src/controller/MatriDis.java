/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author ticoa
 */
public class MatriDis {
    private SimpleStringProperty tipoAluno;
    private String data;
    private String horario;
    private String disciplina;
    private String professor;
    private Short creditos;

    public MatriDis() {
    }
    
    public String getTipoAluno() {
        return tipoAluno.get();
    }

    public void setTipoAluno(String tipoAluno) {
        this.tipoAluno = new SimpleStringProperty(tipoAluno); 
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
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

    public Short getCreditos() {
        return creditos;
    }

    public void setCreditos(Short creditos) {
        this.creditos = creditos;
    }
    
    
            
}
