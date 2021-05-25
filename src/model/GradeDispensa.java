package entities;

public class GradeDispensa {

    private int codgrade;
    private String concatGrade;

    public GradeDispensa(int codgrade, String concatGrade) {
        this.codgrade = codgrade;
        this.concatGrade = concatGrade;
    }

    public int getCodgrade() {
        return codgrade;
    }

    public void setCodgrade(int codgrade) {
        this.codgrade = codgrade;
    }

    public String getConcatGrade() {
        return concatGrade;
    }

    public void setConcatGrade(String concatGrade) {
        this.concatGrade = concatGrade;
    }
    
}
