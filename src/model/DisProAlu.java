package entities;

public class DisProAlu {

    // CLASSE USADA PARA EXIBIR DISCIPLINAS DO PROGRAMA DO ALUNO EM CADPROG2
    private int cadastroAlunoDisciplinaId;
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
    private int numSituacao;
    private int numFrequencia;

    public DisProAlu(int cadastroAlunoDisciplinaId, String nomeAluno, String nomeDisci, String nomeProf, float media, int falta, String nomeSituacao, String nomeFrequencia, int credito, Float cargaHoraria, int anoLetivo, int semestre, Short supervisionada, int numSituacao, int numFrequencia) {
        this.cadastroAlunoDisciplinaId = cadastroAlunoDisciplinaId;
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
        this.numSituacao = numSituacao;
        this.numFrequencia = numFrequencia;
    }


    public DisProAlu() {
    }

    public String getNomeAluno() {
        return nomeAluno;
    }

    public void setNomeAluno(String nomeAluno) {
        this.nomeAluno = nomeAluno;
    }

    
    public int getNumSituacao() {
        return numSituacao;
    }

    public void setNumSituacao(int numSituacao) {
        this.numSituacao = numSituacao;
    }

    public int getNumFrequencia() {
        return numFrequencia;
    }

    public void setNumFrequencia(int numFrequencia) {
        this.numFrequencia = numFrequencia;
    }

    public int getCadastroAlunoDisciplinaId() {
        return cadastroAlunoDisciplinaId;
    }

    public void setCadastroAlunoDisciplinaId(int cadastroAlunoDisciplinaId) {
        this.cadastroAlunoDisciplinaId = cadastroAlunoDisciplinaId;
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
    
    
}
