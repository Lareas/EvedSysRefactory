package entities;

import java.util.Date;

public class DiasAulas {

    private int numAula;
    private Date dataAUla;

    public DiasAulas(int numAula, Date dataAUla) {
        this.numAula = numAula;
        this.dataAUla = dataAUla;
    }

    public int getNumAula() {
        return numAula;
    }

    public void setNumAula(int numAula) {
        this.numAula = numAula;
    }

    public Date getDataAUla() {
        return dataAUla;
    }

    public void setDataAUla(Date dataAUla) {
        this.dataAUla = dataAUla;
    }
    
}
