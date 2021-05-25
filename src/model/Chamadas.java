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
public class Chamadas {
    private Integer cadDis;
    private Integer codAluno;
    private String chamada;
    private Integer totCham;

    public Chamadas() {
        
    }

    public Chamadas(Integer cadDis, Integer codAluno, String chamada, Integer totCham) {
        this.cadDis = cadDis;
        this.codAluno = codAluno;
        this.chamada = chamada;
        this.totCham = totCham;
    }
    
    public Integer getCadDis() {
        return cadDis;
    }

    public void setCadDis(Integer cadDis) {
        this.cadDis = cadDis;
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

    public Integer getTotCham() {
        return totCham;
    }

    public void setTotCham(Integer totCham) {
        this.totCham = totCham;
    }
}
