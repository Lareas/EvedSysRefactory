package entities;

public class FichaEspecificidade {

    private Short especId;
    private String especNome;
    private String especDesc;
    private String especProg;

    public FichaEspecificidade(Short especId, String especNome, String especDesc, String especProg) {
        this.especId = especId;
        this.especNome = especNome;
        this.especDesc = especDesc;
        this.especProg = especProg;
    }

    public Short getEspecId() {
        return especId;
    }

    public void setEspecId(Short especId) {
        this.especId = especId;
    }

    public String getEspecNome() {
        return especNome;
    }

    public void setEspecNome(String especNome) {
        this.especNome = especNome;
    }

    public String getEspecDesc() {
        return especDesc;
    }

    public void setEspecDesc(String especDesc) {
        this.especDesc = especDesc;
    }

    public String getEspecProg() {
        return especProg;
    }

    public void setEspecProg(String especProg) {
        this.especProg = especProg;
    }
    
    
}
