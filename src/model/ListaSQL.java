package entities;

import java.util.Date;

public class ListaSQL {

    private String nomeAluno;
    private String tipoAluno;
    private int codAluno;
    private int codLista;
    private int numAula;
    private Date dataAula;
    private String chamada;

    public ListaSQL(String nomeAluno, String tipoAluno, int codAluno, int codLista, int numAula, Date dataAula, String chamada) {
        this.nomeAluno = nomeAluno;
        this.tipoAluno = tipoAluno;
        this.codAluno = codAluno;
        this.codLista = codLista;
        this.numAula = numAula;
        this.dataAula = dataAula;
        this.chamada = chamada;
    }
    
    public String getTipoAluno() {
        return tipoAluno;
    }

    public void setTipoAluno(String tipoAluno) {
        this.tipoAluno = tipoAluno;
    }
    

    public String getNomeAluno() {
        return nomeAluno;
    }

    public void setNomeAluno(String nomeAluno) {
        this.nomeAluno = nomeAluno;
    }

    public int getCodAluno() {
        return codAluno;
    }

    public void setCodAluno(int codAluno) {
        this.codAluno = codAluno;
    }

    public int getCodLista() {
        return codLista;
    }

    public void setCodLista(int codLista) {
        this.codLista = codLista;
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

    public String getChamada() {
        return chamada;
    }

    public void setChamada(String chamada) {
        this.chamada = chamada;
    }
    

}
