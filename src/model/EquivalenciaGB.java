package entities;

public class EquivalenciaGB {
    
    private Integer codEquivalencia;
    private Integer codAluno;
    private Integer codPrograma;
    private Integer codDisciplina;
    private String observacoes;
    private Boolean docsDigi;
    private Integer codGrade;
    private Integer codSituacao;
    private Integer codPup;

    private String nomeALuno;
    private String nomePrograma;
    private int modoDis; // 1 = ins, 2 = edi

    public EquivalenciaGB() {
    }

    public Integer getCodEquivalencia() {
        return codEquivalencia;
    }

    public void setCodEquivalencia(Integer codEquivalencia) {
        this.codEquivalencia = codEquivalencia;
    }

    public Integer getCodAluno() {
        return codAluno;
    }

    public void setCodAluno(Integer codAluno) {
        this.codAluno = codAluno;
    }

    public Integer getCodPrograma() {
        return codPrograma;
    }

    public void setCodPrograma(Integer codPrograma) {
        this.codPrograma = codPrograma;
    }

    public Integer getCodDisciplina() {
        return codDisciplina;
    }

    public void setCodDisciplina(Integer codDisciplina) {
        this.codDisciplina = codDisciplina;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public Boolean getDocsDigi() {
        return docsDigi;
    }

    public void setDocsDigi(Boolean docsDigi) {
        this.docsDigi = docsDigi;
    }

    public Integer getCodGrade() {
        return codGrade;
    }

    public void setCodGrade(Integer codGrade) {
        this.codGrade = codGrade;
    }

    public Integer getCodSituacao() {
        return codSituacao;
    }

    public void setCodSituacao(Integer codSituacao) {
        this.codSituacao = codSituacao;
    }

    public Integer getCodPup() {
        return codPup;
    }

    public void setCodPup(Integer codPup) {
        this.codPup = codPup;
    }

    public String getNomeALuno() {
        return nomeALuno;
    }

    public void setNomeALuno(String nomeALuno) {
        this.nomeALuno = nomeALuno;
    }

    public String getNomePrograma() {
        return nomePrograma;
    }

    public void setNomePrograma(String nomePrograma) {
        this.nomePrograma = nomePrograma;
    }

    public int getModoDis() {
        return modoDis;
    }

    public void setModoDis(int modoDis) {
        this.modoDis = modoDis;
    }

    

    

}
