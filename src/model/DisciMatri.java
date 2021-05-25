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
public class DisciMatri {
    
    private String tipoRegOuv;
    private String turno;
    private String datas;
    private String horarios;
    private String disciplina;
    private String professor1;
    private Short credito;

    public DisciMatri() {
    }

    public DisciMatri(String tipoRegOuv, String turno, String datas, String horarios, String disciplina, String professor1, Short credito) {
        this.tipoRegOuv = tipoRegOuv;
        this.turno = turno;
        this.datas = datas;
        this.horarios = horarios;
        this.disciplina = disciplina;
        this.professor1 = professor1;
        this.credito = credito;
    }
    
    public String getTipoRegOuv() {
        return tipoRegOuv;
    }

    public void setTipoRegOuv(String tipoRegOuv) {
        this.tipoRegOuv = tipoRegOuv;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public String getDatas() {
        return datas;
    }

    public void setDatas(String datas) {
        this.datas = datas;
    }

    public String getHorarios() {
        return horarios;
    }

    public void setHorarios(String horarios) {
        this.horarios = horarios;
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
