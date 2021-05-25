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
public class ChamadasCalc {
    private Integer cadDis;
    private Integer codAluno;
    private Boolean verificar;
    private Integer totFaltas;
    private Short numAulas;

    public ChamadasCalc() {
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

    public Boolean getVerificar() {
        return verificar;
    }

    public void setVerificar(Boolean verificar) {
        this.verificar = verificar;
    }

    public Integer getTotFaltas() {
        return totFaltas;
    }

    public void setTotFaltas(Integer totFaltas) {
        this.totFaltas = totFaltas;
    }

    public Short getNumAulas() {
        return numAulas;
    }

    public void setNumAulas(Short numAulas) {
        this.numAulas = numAulas;
    }
    
}
