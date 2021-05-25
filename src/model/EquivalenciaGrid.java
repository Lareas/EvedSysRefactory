package entities;

public class EquivalenciaGrid {

    private int codEquiDet;
    private int codEqui;
    private String nomeInstitu;
    private String nomeDisciplina;
    private Float carga;
    private String credito;
    private String ano;
    private String nota;

    public EquivalenciaGrid(int codEquiDet, int codEqui, String nomeInstitu, String nomeDisciplina, Float carga, String credito, String ano, String nota) {
        this.codEquiDet = codEquiDet;
        this.codEqui = codEqui;
        this.nomeInstitu = nomeInstitu;
        this.nomeDisciplina = nomeDisciplina;
        this.carga = carga;
        this.credito = credito;
        this.ano = ano;
        this.nota = nota;
    }

    public EquivalenciaGrid() {
    }

    public int getCodEqui() {
        return codEqui;
    }

    public int getCodEquiDet() {
        return codEquiDet;
    }

    public void setCodEquiDet(int codEquiDet) {
        this.codEquiDet = codEquiDet;
    }

    public void setCodEqui(int codEqui) {
        this.codEqui = codEqui;
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

    public String getCredito() {
        return credito;
    }

    public void setCredito(String credito) {
        this.credito = credito;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    
}
