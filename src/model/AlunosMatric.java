package entities;

public class AlunosMatric {

    private int codAM;
    private String nomeAM;
    private String tipoAluno;

    public AlunosMatric(int codAM, String nomeAM, String tipoAluno) {
        this.codAM = codAM;
        this.nomeAM = nomeAM;
        this.tipoAluno = tipoAluno;
    }

    public int getCodAM() {
        return codAM;
    }

    public void setCodAM(int codAM) {
        this.codAM = codAM;
    }

    public String getNomeAM() {
        return nomeAM;
    }

    public void setNomeAM(String nomeAM) {
        this.nomeAM = nomeAM;
    }

    public String getTipoAluno() {
        return tipoAluno;
    }

    public void setTipoAluno(String tipoAluno) {
        this.tipoAluno = tipoAluno;
    }
    
    

}
