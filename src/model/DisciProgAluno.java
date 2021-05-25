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
public class DisciProgAluno {

    private int cadastroAlunoDisciplinaId;
    private String programa;
    private String disciplina;
    private String professor;
    private float media;
    private Short faltas;
    private String situaacao;
    private String frequencia;
    private Short credito;
    private Short cargah;
    private Short anoLetivo;
    private Short semestre;
    private int codEqui;
    private int codCadDis;
    private String tipoRegOuv;

    public DisciProgAluno() {
    }
    
    public DisciProgAluno(String disciplina, String professor, float media, Short faltas, String situaacao, String frequencia, Short credito, Short cargah, Short anoLetivo, Short semestre, String tipoRegOuv) {
        this.disciplina = disciplina;
        this.professor = professor;
        this.media = media;
        this.faltas = faltas;
        this.situaacao = situaacao;
        this.frequencia = frequencia;
        this.credito = credito;
        this.cargah = cargah;
        this.anoLetivo = anoLetivo;
        this.semestre = semestre;
        this.tipoRegOuv = tipoRegOuv;
    }

    public DisciProgAluno(int cadastroAlunoDisciplinaId, String disciplina, String professor, float media, Short faltas, String situaacao, String frequencia, Short credito, Short cargah, Short anoLetivo, Short semestre, int codEqui, Integer codCadDis, String tipoRegOuv) {
        this.cadastroAlunoDisciplinaId = cadastroAlunoDisciplinaId;
        this.disciplina = disciplina;
        this.professor = professor;
        this.media = media;
        this.faltas = faltas;
        this.situaacao = situaacao;
        this.frequencia = frequencia;
        this.credito = credito;
        this.cargah = cargah;
        this.anoLetivo = anoLetivo;
        this.semestre = semestre;
        this.codEqui = codEqui;
        this.codCadDis = codCadDis;
        this.tipoRegOuv = tipoRegOuv;  
    }

    public DisciProgAluno(int cadastroAlunoDisciplinaId, String programa, String disciplina, String professor, float media, Short faltas, String situaacao, String frequencia, Short credito, Short cargah, Short anoLetivo, Short semestre) {
        this.cadastroAlunoDisciplinaId = cadastroAlunoDisciplinaId;
        this.programa = programa;
        this.disciplina = disciplina;
        this.professor = professor;
        this.media = media;
        this.faltas = faltas;
        this.situaacao = situaacao;
        this.frequencia = frequencia;
        this.credito = credito;
        this.cargah = cargah;
        this.anoLetivo = anoLetivo;
        this.semestre = semestre;
        this.tipoRegOuv = tipoRegOuv;
    }

    public int getCodEqui() {
        return codEqui;
    }

    public void setCodEqui(int codEqui) {
        this.codEqui = codEqui;
    }

    public String getTipoRegOuv() {
        return tipoRegOuv;
    }

    public void setTipoRegOuv(String tipoRegOuv) {
        this.tipoRegOuv = tipoRegOuv;
    }

    public int getCadastroAlunoDisciplinaId() {
        return cadastroAlunoDisciplinaId;
    }

    public int getCodCadDis() {
        return codCadDis;
    }

    public void setCodCadDis(int codCadDis) {
        this.codCadDis = codCadDis;
    }

    public void setCadastroAlunoDisciplinaId(int cadastroAlunoDisciplinaId) {
        this.cadastroAlunoDisciplinaId = cadastroAlunoDisciplinaId;
    }

    public String getPrograma() {
        return programa;
    }

    public void setPrograma(String programa) {
        this.programa = programa;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public float getMedia() {
        return media;
    }

    public void setMedia(float media) {
        this.media = media;
    }

    public Short getFaltas() {
        return faltas;
    }

    public void setFaltas(Short faltas) {
        this.faltas = faltas;
    }

    public String getSituaacao() {
        return situaacao;
    }

    public void setSituaacao(String situaacao) {
        this.situaacao = situaacao;
    }

    public String getFrequencia() {
        return frequencia;
    }

    public void setFrequencia(String frequencia) {
        this.frequencia = frequencia;
    }

    public Short getCredito() {
        return credito;
    }

    public void setCredito(Short credito) {
        this.credito = credito;
    }

    public Short getCargah() {
        return cargah;
    }

    public void setCargah(Short cargah) {
        this.cargah = cargah;
    }

    public Short getAnoLetivo() {
        return anoLetivo;
    }

    public void setAnoLetivo(Short anoLetivo) {
        this.anoLetivo = anoLetivo;
    }

    public Short getSemestre() {
        return semestre;
    }

    public void setSemestre(Short semestre) {
        this.semestre = semestre;
    }

    
}
