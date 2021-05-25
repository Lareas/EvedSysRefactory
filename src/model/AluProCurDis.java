package entities;

public class AluProCurDis {

    private String nomeAluno;
    private String nomeDisci;
    private String nomeProf;
    private float media;
    private int falta;
    private String nomeSituacao;
    private String nomeFrequencia;
    private int credito;
    private Float cargaHoraria;
    private int anoLetivo;
    private int semestre;
    private Short supervisionada;

    private int codAluno;
    private int codPrograma;
    private int codDisciplina;
    private int codFuncionario;
    private int codSituacao;
    private int codFrequencia;

    public AluProCurDis() {
    }

    public AluProCurDis(String nomeAluno, String nomeDisci, String nomeProf, float media, int falta, String nomeSituacao, String nomeFrequencia, int credito, Float cargaHoraria, int anoLetivo, int semestre, Short supervisionada, int codAluno, int codPrograma, int codDisciplina, int codFuncionario, int codSituacao, int codFrequencia) {
        this.nomeAluno = nomeAluno;
        this.nomeDisci = nomeDisci;
        this.nomeProf = nomeProf;
        this.media = media;
        this.falta = falta;
        this.nomeSituacao = nomeSituacao;
        this.nomeFrequencia = nomeFrequencia;
        this.credito = credito;
        this.cargaHoraria = cargaHoraria;
        this.anoLetivo = anoLetivo;
        this.semestre = semestre;
        this.supervisionada = supervisionada;
        this.codAluno = codAluno;
        this.codPrograma = codPrograma;
        this.codDisciplina = codDisciplina;
        this.codFuncionario = codFuncionario;
        this.codSituacao = codSituacao;
        this.codFrequencia = codFrequencia;
    }

    public String getNomeAluno() {
        return nomeAluno;
    }

    public void setNomeAluno(String nomeAluno) {
        this.nomeAluno = nomeAluno;
    }

    public String getNomeDisci() {
        return nomeDisci;
    }

    public void setNomeDisci(String nomeDisci) {
        this.nomeDisci = nomeDisci;
    }

    public String getNomeProf() {
        return nomeProf;
    }

    public void setNomeProf(String nomeProf) {
        this.nomeProf = nomeProf;
    }

    public float getMedia() {
        return media;
    }

    public void setMedia(float media) {
        this.media = media;
    }

    public int getFalta() {
        return falta;
    }

    public void setFalta(int falta) {
        this.falta = falta;
    }

    public String getNomeSituacao() {
        return nomeSituacao;
    }

    public void setNomeSituacao(String nomeSituacao) {
        this.nomeSituacao = nomeSituacao;
    }

    public String getNomeFrequencia() {
        return nomeFrequencia;
    }

    public void setNomeFrequencia(String nomeFrequencia) {
        this.nomeFrequencia = nomeFrequencia;
    }

    public int getCredito() {
        return credito;
    }

    public void setCredito(int credito) {
        this.credito = credito;
    }

    public Float getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(Float cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
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

    public Short getSupervisionada() {
        return supervisionada;
    }

    public void setSupervisionada(Short supervisionada) {
        this.supervisionada = supervisionada;
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

    public int getCodDisciplina() {
        return codDisciplina;
    }

    public void setCodDisciplina(int codDisciplina) {
        this.codDisciplina = codDisciplina;
    }

    public int getCodFuncionario() {
        return codFuncionario;
    }

    public void setCodFuncionario(int codFuncionario) {
        this.codFuncionario = codFuncionario;
    }

    public int getCodSituacao() {
        return codSituacao;
    }

    public void setCodSituacao(int codSituacao) {
        this.codSituacao = codSituacao;
    }

    public int getCodFrequencia() {
        return codFrequencia;
    }

    public void setCodFrequencia(int codFrequencia) {
        this.codFrequencia = codFrequencia;
    }

}
