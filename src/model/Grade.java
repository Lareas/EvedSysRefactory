package entities;

public class Grade {

    private int cadastroDisciplinaId;
    private int codGrade;
    private String turno;
    private String tipoDis;
    private String nomeDisc;
    private String professor1;
    private String professor2;
    private String labelData;
    private String labelHora;
//    private Float cargaHoraria;
//    private int 
    private int credito;
    private int totAlu;
    private int aluReg;
    private int aluOuv;

    public Grade() {
    }

    public Grade( int codGrade, String turno, String tipoDis, String nomeDisc, String professor1, String professor2, String labelData, String labelHora, int credito) {
        this.codGrade = codGrade;
        this.turno = turno;
        this.tipoDis = tipoDis;
        this.nomeDisc = nomeDisc;
        this.professor1 = professor1;
        this.professor2 = professor2;
        this.labelData = labelData;
        this.labelHora = labelHora;
        this.credito = credito;
    }
    
    public Grade( int codGrade, String turno, String tipoDis, String nomeDisc, String professor1, String professor2, String labelData, String labelHora, int credito, int totAlu) {
        this.codGrade = codGrade;
        this.turno = turno;
        this.tipoDis = tipoDis;
        this.nomeDisc = nomeDisc;
        this.professor1 = professor1;
        this.professor2 = professor2;
        this.labelData = labelData;
        this.labelHora = labelHora;
        this.credito = credito;
        this.totAlu = totAlu;
    }
    
    public Grade(int cadastroDisciplinaId,  int codGrade, String turno, String tipoDis, String nomeDisc, String professor1, String professor2, String labelData, String labelHora, int credito, int totAlu) {
        this.cadastroDisciplinaId = cadastroDisciplinaId;
        this.codGrade = codGrade;
        this.turno = turno;
        this.tipoDis = tipoDis;
        this.nomeDisc = nomeDisc;
        this.professor1 = professor1;
        this.professor2 = professor2;
        this.labelData = labelData;
        this.labelHora = labelHora;
        this.credito = credito;
        this.totAlu = totAlu;
    }
    
    public Grade( int codGrade, String turno, String tipoDis, String nomeDisc, String professor1, String professor2, String labelData, String labelHora, int credito, int totAlu, int aluReg, int aluOuv) {
        this.codGrade = codGrade;
        this.turno = turno;
        this.tipoDis = tipoDis;
        this.nomeDisc = nomeDisc;
        this.professor1 = professor1;
        this.professor2 = professor2;
        this.labelData = labelData;
        this.labelHora = labelHora;
        this.credito = credito;
        this.totAlu = totAlu;
        this.aluReg = aluReg;
        this.aluOuv = aluOuv;
    }


    public int getCadastroDisciplinaId() {
        return cadastroDisciplinaId;
    }

    public void setCadastroDisciplinaId(int cadastroDisciplinaId) {
        this.cadastroDisciplinaId = cadastroDisciplinaId;
    }

    public int getCodGrade() {
        return codGrade;
    }

    public void setCodGrade(int codGrade) {
        this.codGrade = codGrade;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public String getTipoDis() {
        return tipoDis;
    }

    public void setTipoDis(String tipoDis) {
        this.tipoDis = tipoDis;
    }

    public String getNomeDisc() {
        return nomeDisc;
    }

    public void setNomeDisc(String nomeDisc) {
        this.nomeDisc = nomeDisc;
    }

    public String getProfessor1() {
        return professor1;
    }

    public void setProfessor1(String professor1) {
        this.professor1 = professor1;
    }

    public String getProfessor2() {
        return professor2;
    }

    public void setProfessor2(String professor2) {
        this.professor2 = professor2;
    }

    public String getLabelData() {
        return labelData;
    }

    public void setLabelData(String labelData) {
        this.labelData = labelData;
    }

    public String getLabelHora() {
        return labelHora;
    }

    public void setLabelHora(String labelHora) {
        this.labelHora = labelHora;
    }

    public int getCredito() {
        return credito;
    }

    public void setCredito(int credito) {
        this.credito = credito;
    }

    public int getTotAlu() {
        return totAlu;
    }

    public void setTotAlu(int totAlu) {
        this.totAlu = totAlu;
    }

    public int getAluReg() {
        return aluReg;
    }

    public void setAluReg(int aluReg) {
        this.aluReg = aluReg;
    }

    public int getAluOuv() {
        return aluOuv;
    }

    public void setAluOuv(int aluOuv) {
        this.aluOuv = aluOuv;
    }

    
}
