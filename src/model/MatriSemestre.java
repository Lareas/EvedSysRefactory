/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Date;

/**
 *
 * @author ticoa
 */
public class MatriSemestre {

    private int cms; // código da matrícula do semestre
    private Date dataMatri;
    private Short anoLetivo;
    private Short semestre;
    private int numDis;
    private int numCre;

    public MatriSemestre() {
    }

    public MatriSemestre(int cms, Date dataMatri, Short anoLetivo, Short semestre, int numDis, int numCre) {
        this.cms = cms;
        this.dataMatri = dataMatri;
        this.anoLetivo = anoLetivo;
        this.semestre = semestre;
        this.numDis = numDis;
        this.numCre = numCre;
    }

    public MatriSemestre(int cms, Date dataMatri, Short anoLetivo, Short semestre) {
        this.cms = cms;
        this.dataMatri = dataMatri;
        this.anoLetivo = anoLetivo;
        this.semestre = semestre;
    }
    
    public int getCms() {
        return cms;
    }

    public void setCms(int cms) {
        this.cms = cms;
    }

    public Date getDataMatri() {
        return dataMatri;
    }

    public void setDataMatri(Date dataMatri) {
        this.dataMatri = dataMatri;
    }

    public Short getAnoLetivo() {
        return anoLetivo;
    }

    public void setAnoLetivo(Short anoLetivo) {
        this.anoLetivo = anoLetivo;
    }

    public Short getSemestre() {
        return semestre;
    }

    public void setSemestre(Short semestre) {
        this.semestre = semestre;
    }

    public int getNumDis() {
        return numDis;
    }

    public void setNumDis(int numDis) {
        this.numDis = numDis;
    }

    public int getNumCre() {
        return numCre;
    }

    public void setNumCre(int numCre) {
        this.numCre = numCre;
    }

}
