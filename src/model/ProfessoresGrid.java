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
public class ProfessoresGrid {
    private int codProf;
    private String nomeProf;
    private String especProf;
    

    public ProfessoresGrid(int codProf, String nomeProf, String especProf) {
        this.codProf = codProf;
        this.nomeProf = nomeProf;
        this.especProf = especProf;
    }

    public int getCodProf() {
        return codProf;
    }

    public void setCodProf(int codProf) {
        this.codProf = codProf;
    }

    public String getNomeProf() {
        return nomeProf;
    }

    public void setNomeProf(String nomeProf) {
        this.nomeProf = nomeProf;
    }

    public String getEspecProf() {
        return especProf;
    }

    public void setEspecProf(String especProf) {
        this.especProf = especProf;
    }
    
    
    
}
