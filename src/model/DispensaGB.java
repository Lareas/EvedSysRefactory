package entities;

public class DispensaGB {

    private int codDispensa;
    private int codAluno;
    private int codPrograma;
    private String nomeALuno;
    private String nomePrograma;
    private int modoDis; // 1 = ins, 2 = edi

    
    public DispensaGB() {
        this.codDispensa = 0;
    }

    public int getCodDispensa() {
        return codDispensa;
    }

    public void setCodDispensa(int codDispensa) {
        this.codDispensa = codDispensa;
    }

    public int getCodAluno() {
        return codAluno;
    }

    public void setCodAluno(int codAluno) {
        this.codAluno = codAluno;
    }

    public int getCodPrograma() {
        return codPrograma;
    }

    public void setCodPrograma(int codPrograma) {
        this.codPrograma = codPrograma;
    }

    public String getNomeALuno() {
        return nomeALuno;
    }

    public void setNomeALuno(String nomeALuno) {
        this.nomeALuno = nomeALuno;
    }

    public String getNomePrograma() {
        return nomePrograma;
    }

    public void setNomePrograma(String nomePrograma) {
        this.nomePrograma = nomePrograma;
    }

    public int getModoDis() {
        return modoDis;
    }

    public void setModoDis(int modoDis) {
        this.modoDis = modoDis;
    }

}
