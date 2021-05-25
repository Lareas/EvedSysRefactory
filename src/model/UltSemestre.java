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
public class UltSemestre {
    private Short ultAno;
    private Short ultSem;
    private int codGrade;

    public UltSemestre() {
    }

    public UltSemestre(Short ultAno, Short ultSem, int codGrade) {
        this.ultAno = ultAno;
        this.ultSem = ultSem;
        this.codGrade = codGrade;
    }

    public Short getUltAno() {
        return ultAno;
    }

    public void setUltAno(Short ultAno) {
        this.ultAno = ultAno;
    }

    public Short getUltSem() {
        return ultSem;
    }

    public void setUltSem(Short ultSem) {
        this.ultSem = ultSem;
    }

    public int getCodGrade() {
        return codGrade;
    }

    public void setCodGrade(int codGrade) {
        this.codGrade = codGrade;
    }
    

    
    
}
