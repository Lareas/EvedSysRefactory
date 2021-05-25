package entities;

public class DispensaGrid {

    private int codDisp;
    private String nomeInstitu;
    private String nomeDisciplina;
    private Float carga;
    private int credito;
    private int ano;
    private String nota;

    public DispensaGrid(int codDisp, String nomeInstitu, String nomeDisciplina, Float carga, int credito, int ano, String nota) {
        this.codDisp = codDisp;
        this.nomeInstitu = nomeInstitu;
        this.nomeDisciplina = nomeDisciplina;
        this.carga = carga;
        this.credito = credito;
        this.ano = ano;
        this.nota = nota;
    }

    public DispensaGrid() {
    }

    public int getCodDisp() {
        return codDisp;
    }

    public void setCodDisp(int codDisp) {
        this.codDisp = codDisp;
    }

    public String getNomeInstitu() {
        return nomeInstitu;
    }

    public void setNomeInstitu(String nomeInstitu) {
        this.nomeInstitu = nomeInstitu;
    }

    public String getNomeDisciplina() {
        return nomeDisciplina;
    }

    public void setNomeDisciplina(String nomeDisciplina) {
        this.nomeDisciplina = nomeDisciplina;
    }

    public Float getCarga() {
        return carga;
    }

    public void setCarga(Float carga) {
        this.carga = carga;
    }

    public int getCredito() {
        return credito;
    }

    public void setCredito(int credito) {
        this.credito = credito;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }
    
    
}
