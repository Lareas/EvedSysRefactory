/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Date;

/**
 *
 * @author ticoa
 */
public class MatriSemCabec {

    private EsMatrisem nomeMatriSem;
    private Boolean modoMatri; // true = INSERE; false = EDITA.; 
    private String nomeAluno;
    private String nomePrograma;
    private Date dataMatricula;
    private int anoLetivo;
    private int semestre;
    private Float taxaMatri;
    private Float valorCredito;
    private int numCredsRE;
    private int numCredsO;
    private Float percDescCred;
    private Float valorDesconto;
    private Float totalAPagar;
    private Float valorSistema;
    private int codGrade;

    private Date dataSecretaria1;
    private Date dataSecretaria2;
    private Date dataFinanceiro;
    private Date dataCoordenacao;
    private Short situacaoMatri;

    private int codAluno;
    private int codPrograma;
    private String obsMatri;

    public MatriSemCabec() {
    }

    public int getCodGrade() {
        return codGrade;
    }

    public void setCodGrade(int codGrade) {
        this.codGrade = codGrade;
    }

    public String getObsMatri() {
        return obsMatri;
    }

    public void setObsMatri(String obsMatri) {
        this.obsMatri = obsMatri;
    }

    public int getCodPrograma() {
        return codPrograma;
    }

    public void setCodPrograma(int codPrograma) {
        this.codPrograma = codPrograma;
    }

    public EsMatrisem getNomeMatriSem() {
        return nomeMatriSem;
    }

    public void setNomeMatriSem(EsMatrisem nomeMatriSem) {
        this.nomeMatriSem = nomeMatriSem;
    }

    public Boolean getModoMatri() {
        return modoMatri;
    }

    public void setModoMatri(Boolean modoMatri) {
        this.modoMatri = modoMatri;
    }

    public String getNomeAluno() {
        return nomeAluno;
    }

    public void setNomeAluno(String nomeAluno) {
        this.nomeAluno = nomeAluno;
    }

    public String getNomePrograma() {
        return nomePrograma;
    }

    public void setNomePrograma(String nomePrograma) {
        this.nomePrograma = nomePrograma;
    }

    public Date getDataMatricula() {
        return dataMatricula;
    }

    public void setDataMatricula(Date dataMatricula) {
        this.dataMatricula = dataMatricula;
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

    public Float getTaxaMatri() {
        return taxaMatri;
    }

    public void setTaxaMatri(Float taxaMatri) {
        this.taxaMatri = taxaMatri;
    }

    public Float getValorCredito() {
        return valorCredito;
    }

    public void setValorCredito(Float valorCredito) {
        this.valorCredito = valorCredito;
    }

    public int getNumCredsRE() {
        return numCredsRE;
    }

    public void setNumCredsRE(int numCredsRE) {
        this.numCredsRE = numCredsRE;
    }

    public int getNumCredsO() {
        return numCredsO;
    }

    public void setNumCredsO(int numCredsO) {
        this.numCredsO = numCredsO;
    }

    public Float getPercDescCred() {
        return percDescCred;
    }

    public void setPercDescCred(Float percDescCred) {
        this.percDescCred = percDescCred;
    }

    public Float getValorDesconto() {
        return valorDesconto;
    }

    public void setValorDesconto(Float valorDesconto) {
        this.valorDesconto = valorDesconto;
    }

    public Float getTotalAPagar() {
        return totalAPagar;
    }

    public void setTotalAPagar(Float totalAPagar) {
        this.totalAPagar = totalAPagar;
    }

    public Float getValorSistema() {
        return valorSistema;
    }

    public void setValorSistema(Float valorSistema) {
        this.valorSistema = valorSistema;
    }

    public Date getDataSecretaria1() {
        return dataSecretaria1;
    }

    public void setDataSecretaria1(Date dataSecretaria1) {
        this.dataSecretaria1 = dataSecretaria1;
    }

    public Date getDataSecretaria2() {
        return dataSecretaria2;
    }

    public void setDataSecretaria2(Date dataSecretaria2) {
        this.dataSecretaria2 = dataSecretaria2;
    }

    public Date getDataFinanceiro() {
        return dataFinanceiro;
    }

    public void setDataFinanceiro(Date dataFinanceiro) {
        this.dataFinanceiro = dataFinanceiro;
    }

    public Date getDataCoordenacao() {
        return dataCoordenacao;
    }

    public void setDataCoordenacao(Date dataCoordenacao) {
        this.dataCoordenacao = dataCoordenacao;
    }

    public Short getSituacaoMatri() {
        return situacaoMatri;
    }

    public void setSituacaoMatri(Short situacaoMatri) {
        this.situacaoMatri = situacaoMatri;
    }

    public int getCodAluno() {
        return codAluno;
    }

    public void setCodAluno(int codAluno) {
        this.codAluno = codAluno;
    }

}
