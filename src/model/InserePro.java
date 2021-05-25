/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import javafx.scene.image.Image;

/**
 *
 * @author ticoa
 */
public class InserePro {
    
    private int anoLetivo;
    private int semestre;
    private int codAluno;
    private String nomeAluno;
    private Image fotoMaior;

    public InserePro() {
    }

    public int getAnoLetivo() {
        return anoLetivo;
    }

    public void setAnoLetivo(int anoLetivo) {
        this.anoLetivo = anoLetivo;
    }

    public int getSemestre() {
        return semestre;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }

    public int getCodAluno() {
        return codAluno;
    }

    public void setCodAluno(int codAluno) {
        this.codAluno = codAluno;
    }

    public String getNomeAluno() {
        return nomeAluno;
    }

    public void setNomeAluno(String nomeAluno) {
        this.nomeAluno = nomeAluno;
    }

    public Image getFotoMaior() {
        return fotoMaior;
    }

    public void setFotoMaior(Image fotoMaior) {
        this.fotoMaior = fotoMaior;
    }

    
}
