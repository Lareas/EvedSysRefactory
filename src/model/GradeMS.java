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
public class GradeMS {
    private int cadastroAlunoDisciplinaId;
    private String nomeDaDis;

    public GradeMS() {
    }

    public GradeMS(int cadastroAlunoDisciplinaId, String nomeDaDis) {
        this.cadastroAlunoDisciplinaId = cadastroAlunoDisciplinaId;
        this.nomeDaDis = nomeDaDis;
    }

    public int getCadastroAlunoDisciplinaId() {
        return cadastroAlunoDisciplinaId;
    }

    public void setCadastroAlunoDisciplinaId(int cadastroAlunoDisciplinaId) {
        this.cadastroAlunoDisciplinaId = cadastroAlunoDisciplinaId;
    }

    public String getNomeDaDis() {
        return nomeDaDis;
    }

    public void setNomeDaDis(String nomeDaDis) {
        this.nomeDaDis = nomeDaDis;
    }

    
    
}
