/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author ticoa
 */
public class DisMatriSemestre {

    private int cadastroAlunoDisciplinaId;
    private int dadoCadastroProgramaId;
    private int codmatrisem;
    private int codGrade;
    private String tipoRegOuv;
    private int anoLetivo;
    private int semestre;
    private String labelData;
    private String labelHorario;
    private String descricao;
    private String professor1;
    private Short credito;
    private int totAlu;
    private int disRegEsp;
    private int dispOuv;

    public DisMatriSemestre(int cadastroAlunoDisciplinaId, int codGrade, String tipoRegOuv, int anoLetivo, int semestre, String labelData, String labelHorario, String descricao, String professor1, Short credito, int totAlu, int disRegEsp, int dispOuv) {
        this.cadastroAlunoDisciplinaId = cadastroAlunoDisciplinaId;
        this.codGrade = codGrade;
        this.tipoRegOuv = tipoRegOuv;
        this.anoLetivo = anoLetivo;
        this.semestre = semestre;
        this.labelData = labelData;
        this.labelHorario = labelHorario;
        this.descricao = descricao;
        this.professor1 = professor1;
        this.credito = credito;
        this.totAlu = totAlu;
        this.disRegEsp = disRegEsp;
        this.dispOuv = dispOuv;
    }

    public DisMatriSemestre(int cadastroAlunoDisciplinaId, int dadoCadastroProgramaId, int codmatrisem, int codGrade, String tipoRegOuv, String labelData, String labelHorario, String descricao, String professor1, Short credito) {
        this.cadastroAlunoDisciplinaId = cadastroAlunoDisciplinaId;
        this.dadoCadastroProgramaId = dadoCadastroProgramaId;
        this.codmatrisem = codmatrisem;
        this.codGrade = codGrade;
        this.tipoRegOuv = tipoRegOuv;
        this.labelData = labelData;
        this.labelHorario = labelHorario;
        this.descricao = descricao;
        this.professor1 = professor1;
        this.credito = credito;
    }


    public int getCodmatrisem() {
        return codmatrisem;
    }

    public void setCodmatrisem(int codmatrisem) {
        this.codmatrisem = codmatrisem;
    }

    public int getDadoCadastroProgramaId() {
        return dadoCadastroProgramaId;
    }

    public void setDadoCadastroProgramaId(int dadoCadastroProgramaId) {
        this.dadoCadastroProgramaId = dadoCadastroProgramaId;
    }
    
    
    

    public DisMatriSemestre() {
    }

    public int getTotAlu() {
        return totAlu;
    }

    public void setTotAlu(int totAlu) {
        this.totAlu = totAlu;
    }

    public int getDisRegEsp() {
        return disRegEsp;
    }

    public void setDisRegEsp(int disRegEsp) {
        this.disRegEsp = disRegEsp;
    }

    public int getDispOuv() {
        return dispOuv;
    }

    public void setDispOuv(int dispOuv) {
        this.dispOuv = dispOuv;
    }

    public int getCadastroAlunoDisciplinaId() {
        return cadastroAlunoDisciplinaId;
    }

    public void setCadastroAlunoDisciplinaId(int cadastroAlunoDisciplinaId) {
        this.cadastroAlunoDisciplinaId = cadastroAlunoDisciplinaId;
    }

    public int getCodGrade() {
        return codGrade;
    }

    public void setCodGrade(int codGrade) {
        this.codGrade = codGrade;
    }

    public String getTipoRegOuv() {
        return tipoRegOuv;
    }

    public void setTipoRegOuv(String tipoRegOuv) {
        this.tipoRegOuv = tipoRegOuv;
    }

    public int getAnoLetivo() {
        return anoLetivo;
    }

    public void setAnoLetivo(int anoLetivo) {
        this.anoLetivo = anoLetivo;
    }

    public int getSemestre() {
        return semestre;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }

    public String getLabelData() {
        return labelData;
    }

    public void setLabelData(String labelData) {
        this.labelData = labelData;
    }

    public String getLabelHorario() {
        return labelHorario;
    }

    public void setLabelHorario(String labelHorario) {
        this.labelHorario = labelHorario;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String disciplina) {
        this.descricao = descricao;
    }

    public String getProfessor1() {
        return professor1;
    }

    public void setProfessor1(String professor1) {
        this.professor1 = professor1;
    }

    public Short getCredito() {
        return credito;
    }

    public void setCredito(Short credito) {
        this.credito = credito;
    }

    public String toString() {
        return String.format("%s", tipoRegOuv);
    }

}
