package entities;

import java.util.Date;

public class ProgramasAluno {

    private int codCadProId;
    private int codAluno;
    private String programa;
    private Short cursoId;
    private String curso;
    private String situacao;
    private int anoLetivo;
    private int semestre;
    private String turno;
    private Date dataCadastro;
    private String titulacao;

    public ProgramasAluno() {
    }

    public ProgramasAluno(int codCadProId, int codAluno, String programa, String curso, String situacao, int anoLetivo, int semestre, String turno, Date dataCadastro) {
        this.codCadProId = codCadProId;
        this.codAluno = codAluno;
        this.programa = programa;
        this.curso = curso;
        this.situacao = situacao;
        this.anoLetivo = anoLetivo;
        this.semestre = semestre;
        this.turno = turno;
        this.dataCadastro = dataCadastro;
    }

//    public ProgramasAluno(int codCadProId, String programa, String titulacao) {
//        this.codCadProId = codCadProId;
//        this.programa = programa;
//        this.titulacao = titulacao;
//    }
    
    public ProgramasAluno(int codCadProId, int codAluno, String programa, Short cursoId, String curso, String situacao, int anoLetivo, int semestre, String turno, Date dataCadastro) {
        this.codCadProId = codCadProId;
        this.codAluno = codAluno;
        this.programa = programa;
        this.cursoId = cursoId;
        this.curso = curso;
        this.situacao = situacao;
        this.anoLetivo = anoLetivo;
        this.semestre = semestre;
        this.turno = turno;
        this.dataCadastro = dataCadastro;
    }
    
    public ProgramasAluno(int codCadProId, int codAluno, String programa, Short cursoId, String curso, String situacao, int anoLetivo, int semestre, String turno, Date dataCadastro, String titulacao) {
        this.codCadProId = codCadProId;
        this.codAluno = codAluno;
        this.programa = programa;
        this.cursoId = cursoId;
        this.curso = curso;
        this.situacao = situacao;
        this.anoLetivo = anoLetivo;
        this.semestre = semestre;
        this.turno = turno;
        this.dataCadastro = dataCadastro;
        this.titulacao = titulacao;
    }

    public Short getCursoId() {
        return cursoId;
    }

    public String getTitulacao() {
        return titulacao;
    }

    public void setTitulacao(String titulacao) {
        this.titulacao = titulacao;
    }
    

    public void setCursoId(Short cursoId) {
        this.cursoId = cursoId;
    }

    public int getCodCadProId() {
        return codCadProId;
    }

    public void setCodCadProId(int codCadProId) {
        this.codCadProId = codCadProId;
    }

    public int getCodAluno() {
        return codAluno;
    }

    public void setCodAluno(int codAluno) {
        this.codAluno = codAluno;
    }

    public String getPrograma() {
        return programa;
    }

    public void setPrograma(String programa) {
        this.programa = programa;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
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

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

}
