package entities;

public class DatasLP {

    private int codListaAula;
    private int NumAuLa;

    public DatasLP(int codListaAula, int NumAuLa) {
        this.codListaAula = codListaAula;
        this.NumAuLa = NumAuLa;
    }

    public int getCodListaAula() {
        return codListaAula;
    }

    public void setCodListaAula(int codListaAula) {
        this.codListaAula = codListaAula;
    }

    public int getNumAuLa() {
        return NumAuLa;
    }

    public void setNumAuLa(int NumAuLa) {
        this.NumAuLa = NumAuLa;
    }
    
}
