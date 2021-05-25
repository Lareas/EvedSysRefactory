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
public class PainelMatriSem {
    private Integer codMatriSem;
    private Short anoLetivo;
    private Short semestre;

    public PainelMatriSem() {
    }

    public PainelMatriSem(Integer codMatriSem, Short anoLetivo, Short semestre) {
        this.codMatriSem = codMatriSem;
        this.anoLetivo = anoLetivo;
        this.semestre = semestre;
    }
    
    public Integer getCodMatriSem() {
        return codMatriSem;
    }

    public void setCodMatriSem(Integer codMatriSem) {
        this.codMatriSem = codMatriSem;
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

    
    
}
