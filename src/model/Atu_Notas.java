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
public class Atu_Notas {

    private int cadastroAlunoDisciplinaId;
    private int dadoCadastroGeralId;
    private String nomeAluno;
    private String programa;
    private String disciplina;
    private Short anoLetivo;
    private Short semestre;
    private Short credito;
    private Short cargah;
    private String turno;
    private String horario;
    private String turma;
    private String professor;
    private String localidade;
    private float media;
    private Short faltas;
    private String situacao;
    private String frequencia;
    private String tipoRegOuv;

    public Atu_Notas() {
    }

    public Atu_Notas(int cadastroAlunoDisciplinaId, int dadoCadastroGeralId, String nomeAluno, String programa, String disciplina, Short anoLetivo, Short semestre, Short credito, Short cargah, String turno, String horario, String turma, String professor, String localidade, float media, Short faltas, String situacao, String frequencia, String tipoRegOuv) {
        this.cadastroAlunoDisciplinaId = cadastroAlunoDisciplinaId;
        this.dadoCadastroGeralId = dadoCadastroGeralId;
        this.nomeAluno = nomeAluno;
        this.programa = programa;
        this.disciplina = disciplina;
        this.anoLetivo = anoLetivo;
        this.semestre = semestre;
        this.credito = credito;
        this.cargah = cargah;
        this.turno = turno;
        this.horario = horario;
        this.turma = turma;
        this.professor = professor;
        this.localidade = localidade;
        this.media = media;
        this.faltas = faltas;
        this.situacao = situacao;
        this.frequencia = frequencia;
        this.tipoRegOuv = tipoRegOuv;
    }
    

    public int getDadoCadastroGeralId() {
        return dadoCadastroGeralId;
    }

    public void setDadoCadastroGeralId(int dadoCadastroGeralId) {
        this.dadoCadastroGeralId = dadoCadastroGeralId;
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

    public void setCadastroAlunoDisciplinaId(int cadastroAlunoDisciplinaId) {
        this.cadastroAlunoDisciplinaId = cadastroAlunoDisciplinaId;
    }

    public String getNomeAluno() {
        return nomeAluno;
    }

    public void setNomeAluno(String nomeAluno) {
        this.nomeAluno = nomeAluno;
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

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getTurma() {
        return turma;
    }

    public void setTurma(String turma) {
        this.turma = turma;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
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

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public String getFrequencia() {
        return frequencia;
    }

    public void setFrequencia(String frequencia) {
        this.frequencia = frequencia;
    }

}
