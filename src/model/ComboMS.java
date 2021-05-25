package entities;

public class ComboMS {

    private int ano;
    private int sem;
    private String gradeMS;

    public ComboMS(int ano, int sem) {
        this.ano = ano;
        this.sem = sem;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public int getSem() {
        return sem;
    }

    public void setSem(int sem) {
        this.sem = sem;
    }

    public String getGradeMS() {
        return ano + "." + sem;
    }

    public void setGradeMS(String gradeMS) {
        this.gradeMS = gradeMS;
    }

    
}
