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
public class TotAluPro {

    private String programa;
    private String sigla;
    private Integer totAluno;

    public TotAluPro(String programa, String sigla, Integer totAluno) {
        this.programa = programa;
        this.sigla = sigla;
        this.totAluno = totAluno;
    }

    public TotAluPro(String sigla, Integer totAluno) {
        this.sigla = sigla;
        this.totAluno = totAluno;
    }

    public String getPrograma() {
        return programa;
    }

    public void setPrograma(String programa) {
        this.programa = programa;
    }

    public Integer getTotAluno() {
        return totAluno;
    }

    public void setTotAluno(Integer totAluno) {
        this.totAluno = totAluno;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

}
