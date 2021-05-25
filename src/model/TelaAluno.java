package entities;

import java.util.Date;

public class TelaAluno {

    private String matricula;
    private Integer codAluno;
    private String nomeAluno;
    private String titulacao;
    private String programa;
    private Short numprogs;
    private String situacaoNome;
    private String formacaoNome;
    private String profissaoNome;
    private String ministerioNome;
    private String denominacaoNome;
    private Short anoLetivo;
    private Short semestre;
    private Date dataCadastro;
    private String Localidade;
    private String telefoneCelular;
    private String emailPessoal;

    public TelaAluno() {
    }

    public TelaAluno(String matricula, Integer codAluno, String nomeAluno, String titulacao, String programa, Short numprogs, String situacaoNome, String formacaoNome, String profissaoNome, String ministerioNome, String denominacaoNome, Short anoLetivo, Short semestre, Date dataCadastro, String Localidade, String telefoneCelular, String emailPessoal) {
        this.matricula = matricula;
        this.codAluno = codAluno;
        this.nomeAluno = nomeAluno;
        this.titulacao = titulacao;
        this.programa = programa;
        this.numprogs = numprogs;
        this.situacaoNome = situacaoNome;
        this.formacaoNome = formacaoNome;
        this.profissaoNome = profissaoNome;
        this.ministerioNome = ministerioNome;
        this.denominacaoNome = denominacaoNome;
        this.anoLetivo = anoLetivo;
        this.semestre = semestre;
        this.dataCadastro = dataCadastro;
        this.Localidade = Localidade;
        this.telefoneCelular = telefoneCelular;
        this.emailPessoal = emailPessoal;
    }

    public TelaAluno(String matricula, Integer codAluno, String nomeAluno, String titulacao, String programa, Short numprogs, String situacaoNome, Short anoLetivo, Short semestre, Date dataCadastro, String Localidade, String telefoneCelular, String emailPessoal) {
        this.matricula = matricula;
        this.codAluno = codAluno;
        this.nomeAluno = nomeAluno;
        this.titulacao = titulacao;
        this.programa = programa;
        this.numprogs = numprogs;
        this.situacaoNome = situacaoNome;
        this.anoLetivo = anoLetivo;
        this.semestre = semestre;
        this.dataCadastro = dataCadastro;
        this.Localidade = Localidade;
        this.telefoneCelular = telefoneCelular;
        this.emailPessoal = emailPessoal;
    }

    
    public Integer getCodAluno() {
        return codAluno;
    }

    public void setCodAluno(Integer codAluno) {
        this.codAluno = codAluno;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
    
    public String getNomeAluno() {
        return nomeAluno;
    }

    public void setNomeAluno(String nomeAluno) {
        this.nomeAluno = nomeAluno;
    }

    public String getTitulacao() {
        return titulacao;
    }

    public void setTitulacao(String titulacao) {
        this.titulacao = titulacao;
    }

    public String getPrograma() {
        return programa;
    }

    public void setPrograma(String programa) {
        this.programa = programa;
    }

    public Short getNumprogs() {
        return numprogs;
    }

    public void setNumprogs(Short numprogs) {
        this.numprogs = numprogs;
    }

    public String getSituacaoNome() {
        return situacaoNome;
    }

    public void setSituacaoNome(String situacaoNome) {
        this.situacaoNome = situacaoNome;
    }

    public String getFormacaoNome() {
        return formacaoNome;
    }

    public void setFormacaoNome(String formacaoNome) {
        this.formacaoNome = formacaoNome;
    }

    public String getProfissaoNome() {
        return profissaoNome;
    }

    public void setProfissaoNome(String profissaoNome) {
        this.profissaoNome = profissaoNome;
    }

    public String getMinisterioNome() {
        return ministerioNome;
    }

    public void setMinisterioNome(String ministerioNome) {
        this.ministerioNome = ministerioNome;
    }

    public String getDenominacaoNome() {
        return denominacaoNome;
    }

    public void setDenominacaoNome(String denominacaoNome) {
        this.denominacaoNome = denominacaoNome;
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

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public String getLocalidade() {
        return Localidade;
    }

    public void setLocalidade(String Localidade) {
        this.Localidade = Localidade;
    }

    public String getTelefoneCelular() {
        return telefoneCelular;
    }

    public void setTelefoneCelular(String telefoneCelular) {
        this.telefoneCelular = telefoneCelular;
    }

    public String getEmailPessoal() {
        return emailPessoal;
    }

    public void setEmailPessoal(String emailPessoal) {
        this.emailPessoal = emailPessoal;
    }

 

    
}
