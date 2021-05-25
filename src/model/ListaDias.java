package entities;

import java.util.Date;

public class ListaDias {

    private int codLista_Aula;
    private int numAula;
    private Date dataAula;

    public ListaDias(int codLista_Aula, int numAula, Date dataAula) {
        this.codLista_Aula = codLista_Aula;
        this.numAula = numAula;
        this.dataAula = dataAula;
    }

    public int getCodLista_Aula() {
        return codLista_Aula;
    }

    public void setCodLista_Aula(int codLista_Aula) {
        this.codLista_Aula = codLista_Aula;
    }

    public int getNumAula() {
        return numAula;
    }

    public void setNumAula(int numAula) {
        this.numAula = numAula;
    }

    public Date getDataAula() {
        return dataAula;
    }

    public void setDataAula(Date dataAula) {
        this.dataAula = dataAula;
    }
    
    

}
