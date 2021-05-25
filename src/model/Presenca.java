/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Date;

/**
 *
 * @author luiza
 */
public class Presenca {
    private Integer codChamada;
    private Integer numAula;
    private Date dataAula;
    private String chamada;
    private String obsChamada;

    public Presenca(Integer codChamada, Integer numAula, Date dataAula, String chamada, String obsChamada) {
        this.codChamada = codChamada;
        this.numAula = numAula;
        this.dataAula = dataAula;
        this.chamada = chamada;
        this.obsChamada = obsChamada;
    }

    public Integer getCodChamada() {
        return codChamada;
    }

    public void setCodChamada(Integer codChamada) {
        this.codChamada = codChamada;
    }

    public Integer getNumAula() {
        return numAula;
    }

    public void setNumAula(Integer numAula) {
        this.numAula = numAula;
    }

    public Date getDataAula() {
        return dataAula;
    }

    public void setDataAula(Date dataAula) {
        this.dataAula = dataAula;
    }

    public String getChamada() {
        return chamada;
    }

    public void setChamada(String chamada) {
        this.chamada = chamada;
    }

    public String getObsChamada() {
        return obsChamada;
    }

    public void setObsChamada(String obsChamada) {
        this.obsChamada = obsChamada;
    }
    
    
    
}
