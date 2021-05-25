/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author luiza
 */
public class TotsChamada {
    
    private Integer codAluno;
    private String chamada;
    private Integer totChamada;

    public TotsChamada() {
    }

    public TotsChamada(Integer codAluno, String chamada, Integer totChamada) {
        this.codAluno = codAluno;
        this.chamada = chamada;
        this.totChamada = totChamada;
    }

    public Integer getCodAluno() {
        return codAluno;
    }

    public void setCodAluno(Integer codAluno) {
        this.codAluno = codAluno;
    }

    public String getChamada() {
        return chamada;
    }

    public void setChamada(String chamada) {
        this.chamada = chamada;
    }

    public Integer getTotChamada() {
        return totChamada;
    }

    public void setTotChamada(Integer totChamada) {
        this.totChamada = totChamada;
    }
    
}
