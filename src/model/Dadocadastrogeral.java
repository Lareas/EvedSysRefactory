package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "dadocadastrogeral")
public class Dadocadastrogeral implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DadoCadastroGeralId")
    private Integer dadoCadastroGeralId;
    @Basic(optional = false)
    @Column(name = "Nome")
    private String nome;
    @Column(name = "EnderecoResidencial")
    private String enderecoResidencial;
    @Column(name = "BairroResidencial")
    private String bairroResidencial;
    @Column(name = "CidadeResidencial")
    private String cidadeResidencial;

//    @Column(name = "UFResidencialId")
//    private Short uFResidencialId;
    @ManyToOne
    @JoinColumn(name = "uFResidencialId", referencedColumnName = "siglaEstadoId", nullable = true)
    private Siglaestado nomeUFResid;

    @Column(name = "CEPResidencial")
    private String cEPResidencial;
    @Column(name = "TelefoneResidencial")
    private String telefoneResidencial;
    @Column(name = "TelefoneCelular")
    private String telefoneCelular;
    @Column(name = "TelefoneComercial")
    private String telefoneComercial;
    @Column(name = "EmailPessoal")
    private String emailPessoal;
    @Column(name = "LocalNascimento")
    private String localNascimento;

//    @Column(name = "UFNascimentoId")
//    private Short uFNascimentoId;
    @ManyToOne
    @JoinColumn(name = "uFNascimentoId", referencedColumnName = "siglaEstadoId", nullable = true)
    private Siglaestado nomeUFNasc;
    
//    
    @Column(name = "DataNascimento")
    @Temporal(TemporalType.DATE)
    private Date dataNascimento;

//    @Column(name = "NacionalidadeId")
//    private Short nacionalidadeId;
    @ManyToOne
    @JoinColumn(name = "nacionalidadeId", referencedColumnName = "nacionalidadeId", nullable = true)
    private Nacionalidade nomeNacionalidade;
//    

//    @Column(name = "EstadoCivilId")
//    private Short estadoCivilId;
    @ManyToOne
    @JoinColumn(name = "estadoCivilId", referencedColumnName = "estadoCivilId", nullable = true)
    private Estadocivil nomeEstadoCivil;

    @Column(name = "Conjuge")
    private String conjuge;
    
    @Column(name = "RG")
    private String rg;
    @Column(name = "CPF")
    private String cpf;

//    @Column(name = "DenominacaoId")
//    private Short denominacaoId;
    @ManyToOne
    @JoinColumn(name = "denominacaoId", referencedColumnName = "denominacaoId", nullable = true)
    private Denominacao nomeDenominacao;

    @Column(name = "EscolaGrauMaxima")
    private String escolaGrauMaxima;
    @Column(name = "Escola")
    private String escola;
    @Column(name = "CidadeEscola")
    private String cidadeEscola;

//    @Column(name = "UFEscolaId")
//    private Short uFEscolaId;
    @ManyToOne
    @JoinColumn(name = "uFEscolaId", referencedColumnName = "siglaEstadoId", nullable = true)
    private Siglaestado nomeUFEscola;

    @Column(name = "CursoEscola")
    private String cursoEscola;
    
    @Column(name = "AnoConclusaoEscola")
    private Integer anoConclusaoEscola;
    
    @Column(name = "EscolaPosGrad")
    private String escolaPosGrad;
    @Column(name = "CidadePosGrad")
    private String cidadePosGrad;

//    @Column(name = "UFPosGradId")
//    private Short uFPosGradId;
    @ManyToOne
    @JoinColumn(name = "uFPosGradId", referencedColumnName = "siglaEstadoId", nullable = true)
    private Siglaestado nomeUFPos;

    @Column(name = "CursoPosGrad")
    private String cursoPosGrad;
    @Column(name = "AnoConclusaoPosGrad")
    private String anoConclusaoPosGrad;

    @Column(name = "NomeIgreja")
    private String nomeIgreja;
    @Column(name = "TelefoneIgreja")
    private String telefoneIgreja;
    @Column(name = "EmailIgreja")
    private String emailIgreja;
    @Column(name = "PastorIgreja")
    private String pastorIgreja;

//    @Lob
//    @Column(name = "FotoAluno")
//    private byte[] fotoAluno;
    @Column(name = "SexoId")
    private Short sexoId;

    @Column(name = "Matricula")
    private String matricula;

    @Column(name = "Atualizacao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date atualizacao;

//    @Column(name = "tipoDocOficial")
//    private Short tipoDocOficial;
    @ManyToOne
    @JoinColumn(name = "tipoDocOficial", referencedColumnName = "docOficialId", nullable = true)
    private Docsoficiais nomeDocOficial;

    @Column(name = "numDocOficial")
    private String numDocOficial;

//    @Column(name = "codFormacao")
//    private Short codFormacao;
    @ManyToOne
    @JoinColumn(name = "codFormacao", referencedColumnName = "codFormacao", nullable = true)
    private Formacao nomeFormacao;

    //    @Column(name = "codProfissao")
//    private Short codProfissao;
    @ManyToOne
    @JoinColumn(name = "codProfissao", referencedColumnName = "codProfissao", nullable = true)
    private Profissoes nomeProfissao;

    @Column(name = "compProfissao")
    private String compProfissao;
    
    @Column(name = "compProfissao2")
    private String compProfissao2;

    @ManyToOne
    @JoinColumn(name = "corPele", referencedColumnName = "codcordapele", nullable = true)
    private Cordapele nomeCorDaPele;

    @Column(name = "situacaodados")
    private String situacaodados;

    @Column(name = "consist")
    private String consist;

    @Column(name = "datainclusao")
    @Temporal(TemporalType.DATE)
    private Date dataInclusao;
    
    @ManyToOne
    @JoinColumn(name = "codProfissao2", referencedColumnName = "codProfissao", nullable = true)
    private Profissoes nomeProfissao2;
    
    @Column(name = "ObsEnsinoMed")
    private String obsEnsinoMed;
    
    @Column(name = "ObsEnsinoSup")
    private String obsEnsinoSup;
    
    @Column(name = "ObsGeral")
    private String obsGeral;
    
    @Column(name = "DataInc")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataInc;
    @Column(name = "DataAlt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAlt;
    @Column(name = "CodUserInc")
    private Integer codUserInc;
    @Column(name = "CodUserAlt")
    private Integer codUserAlt;
    
    @Column(name = "Especial")
    private Boolean especial;

    public String getCompProfissao2() {
        return compProfissao2;
    }

    public void setCompProfissao2(String compProfissao2) {
        this.compProfissao2 = compProfissao2;
    }
    
    @Column(name = "NDependentes")
    private Integer nDependentes;

    public Integer getnDependentes() {
        return nDependentes;
    }

    public void setnDependentes(Integer nDependentes) {
        this.nDependentes = nDependentes;
    }

    public Integer getAnoConclusaoEscola() {
        return anoConclusaoEscola;
    }

    public void setAnoConclusaoEscola(Integer anoConclusaoEscola) {
        this.anoConclusaoEscola = anoConclusaoEscola;
    }
    
    public Dadocadastrogeral() {
    }

    public Boolean getEspecial() {
        return especial;
    }

    public void setEspecial(Boolean especial) {
        this.especial = especial;
    }

    
    public Profissoes getNomeProfissao2() {
        return nomeProfissao2;
    }

    public void setNomeProfissao2(Profissoes nomeProfissao2) {
        this.nomeProfissao2 = nomeProfissao2;
    }

    public Date getDataInc() {
        return dataInc;
    }

    public void setDataInc(Date dataInc) {
        this.dataInc = dataInc;
    }

    public Date getDataAlt() {
        return dataAlt;
    }

    public void setDataAlt(Date dataAlt) {
        this.dataAlt = dataAlt;
    }
    

    public String getObsEnsinoMed() {
        return obsEnsinoMed;
    }

    public Integer getCodUserInc() {
        return codUserInc;
    }

    public void setCodUserInc(Integer codUserInc) {
        this.codUserInc = codUserInc;
    }

    public Integer getCodUserAlt() {
        return codUserAlt;
    }

    public void setCodUserAlt(Integer codUserAlt) {
        this.codUserAlt = codUserAlt;
    }
    

    public void setObsEnsinoMed(String obsEnsinoMed) {
        this.obsEnsinoMed = obsEnsinoMed;
    }

    public String getObsEnsinoSup() {
        return obsEnsinoSup;
    }

    public void setObsEnsinoSup(String obsEnsinoSup) {
        this.obsEnsinoSup = obsEnsinoSup;
    }

    public String getObsGeral() {
        return obsGeral;
    }

    public void setObsGeral(String obsGeral) {
        this.obsGeral = obsGeral;
    }

    
    public String getCompProfissao() {
        return compProfissao;
    }

    public void setCompProfissao(String compProfissao) {
        this.compProfissao = compProfissao;
    }

    public String getSituacaodados() {
        return situacaodados;
    }

    public void setSituacaodados(String situacaodados) {
        this.situacaodados = situacaodados;
    }

    public String getConsist() {
        return consist;
    }

    public void setConsist(String consist) {
        this.consist = consist;
    }

    public Date getDataInclusao() {
        return dataInclusao;
    }

    public void setDataInclusao(Date dataInclusao) {
        this.dataInclusao = dataInclusao;
    }

    public Cordapele getNomeCorDaPele() {
        return nomeCorDaPele;
    }

    public void setNomeCorDaPele(Cordapele nomeCorDaPele) {
        this.nomeCorDaPele = nomeCorDaPele;
    }

//    public Date getDataStatusDados() {
//        return dataStatusDados;
//    }
//
//    public void setDataStatusDados(Date dataStatusDados) {
//        this.dataStatusDados = dataStatusDados;
//    }    

    public Siglaestado getNomeUFResid() {
        return nomeUFResid;
    }

//    public Date getDataStatusDados() {
//        return dataStatusDados;
//    }
//
//    public void setDataStatusDados(Date dataStatusDados) {
//        this.dataStatusDados = dataStatusDados;
//    }
    public void setNomeUFResid(Siglaestado nomeUFResid) {
        this.nomeUFResid = nomeUFResid;
    }

    public Siglaestado getNomeUFNasc() {
        return nomeUFNasc;
    }

    public void setNomeUFNasc(Siglaestado nomeUFNasc) {
        this.nomeUFNasc = nomeUFNasc;
    }

    public Nacionalidade getNomeNacionalidade() {
        return nomeNacionalidade;
    }

    public void setNomeNacionalidade(Nacionalidade nomeNacionalidade) {
        this.nomeNacionalidade = nomeNacionalidade;
    }

    public Estadocivil getNomeEstadoCivil() {
        return nomeEstadoCivil;
    }

    public void setNomeEstadoCivil(Estadocivil nomeEstadoCivil) {
        this.nomeEstadoCivil = nomeEstadoCivil;
    }

    public Denominacao getNomeDenominacao() {
        return nomeDenominacao;
    }

    public void setNomeDenominacao(Denominacao nomeDenominacao) {
        this.nomeDenominacao = nomeDenominacao;
    }

    public Siglaestado getNomeUFEscola() {
        return nomeUFEscola;
    }

    public void setNomeUFEscola(Siglaestado nomeUFEscola) {
        this.nomeUFEscola = nomeUFEscola;
    }

    public Siglaestado getNomeUFPos() {
        return nomeUFPos;
    }

    public void setNomeUFPos(Siglaestado nomeUFPos) {
        this.nomeUFPos = nomeUFPos;
    }

    public Dadocadastrogeral(Integer dadoCadastroGeralId) {
        this.dadoCadastroGeralId = dadoCadastroGeralId;
    }

    public Dadocadastrogeral(Integer dadoCadastroGeralId, String nome) {
        this.dadoCadastroGeralId = dadoCadastroGeralId;
        this.nome = nome;
    }

    public Integer getDadoCadastroGeralId() {
        return dadoCadastroGeralId;
    }

    public void setDadoCadastroGeralId(Integer dadoCadastroGeralId) {
        this.dadoCadastroGeralId = dadoCadastroGeralId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEnderecoResidencial() {
        return enderecoResidencial;
    }

    public void setEnderecoResidencial(String enderecoResidencial) {
        this.enderecoResidencial = enderecoResidencial;
    }

    public String getBairroResidencial() {
        return bairroResidencial;
    }

    public void setBairroResidencial(String bairroResidencial) {
        this.bairroResidencial = bairroResidencial;
    }

    public String getCidadeResidencial() {
        return cidadeResidencial;
    }

    public void setCidadeResidencial(String cidadeResidencial) {
        this.cidadeResidencial = cidadeResidencial;
    }

    public String getCEPResidencial() {
        return cEPResidencial;
    }

    public void setCEPResidencial(String cEPResidencial) {
        this.cEPResidencial = cEPResidencial;
    }

    public String getTelefoneResidencial() {
        return telefoneResidencial;
    }

    public void setTelefoneResidencial(String telefoneResidencial) {
        this.telefoneResidencial = telefoneResidencial;
    }

    public String getTelefoneCelular() {
        return telefoneCelular;
    }

    public void setTelefoneCelular(String telefoneCelular) {
        this.telefoneCelular = telefoneCelular;
    }

    public String getTelefoneComercial() {
        return telefoneComercial;
    }

    public void setTelefoneComercial(String telefoneComercial) {
        this.telefoneComercial = telefoneComercial;
    }

    public String getEmailPessoal() {
        return emailPessoal;
    }

    public void setEmailPessoal(String emailPessoal) {
        this.emailPessoal = emailPessoal;
    }

    public String getLocalNascimento() {
        return localNascimento;
    }

    public void setLocalNascimento(String localNascimento) {
        this.localNascimento = localNascimento;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getConjuge() {
        return conjuge;
    }

    public void setConjuge(String conjuge) {
        this.conjuge = conjuge;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEscolaGrauMaxima() {
        return escolaGrauMaxima;
    }

    public void setEscolaGrauMaxima(String escolaGrauMaxima) {
        this.escolaGrauMaxima = escolaGrauMaxima;
    }

    public String getEscola() {
        return escola;
    }

    public void setEscola(String escola) {
        this.escola = escola;
    }

    public String getCidadeEscola() {
        return cidadeEscola;
    }

    public void setCidadeEscola(String cidadeEscola) {
        this.cidadeEscola = cidadeEscola;
    }

    public String getCursoEscola() {
        return cursoEscola;
    }

    public void setCursoEscola(String cursoEscola) {
        this.cursoEscola = cursoEscola;
    }

    public String getEscolaPosGrad() {
        return escolaPosGrad;
    }

    public void setEscolaPosGrad(String escolaPosGrad) {
        this.escolaPosGrad = escolaPosGrad;
    }

    public String getCidadePosGrad() {
        return cidadePosGrad;
    }

    public void setCidadePosGrad(String cidadePosGrad) {
        this.cidadePosGrad = cidadePosGrad;
    }

    public String getCursoPosGrad() {
        return cursoPosGrad;
    }

    public void setCursoPosGrad(String cursoPosGrad) {
        this.cursoPosGrad = cursoPosGrad;
    }

    public String getAnoConclusaoPosGrad() {
        return anoConclusaoPosGrad;
    }

    public void setAnoConclusaoPosGrad(String anoConclusaoPosGrad) {
        this.anoConclusaoPosGrad = anoConclusaoPosGrad;
    }

    public String getNomeIgreja() {
        return nomeIgreja;
    }

    public void setNomeIgreja(String nomeIgreja) {
        this.nomeIgreja = nomeIgreja;
    }

    public String getTelefoneIgreja() {
        return telefoneIgreja;
    }

    public void setTelefoneIgreja(String telefoneIgreja) {
        this.telefoneIgreja = telefoneIgreja;
    }

    public String getEmailIgreja() {
        return emailIgreja;
    }

    public void setEmailIgreja(String emailIgreja) {
        this.emailIgreja = emailIgreja;
    }

    public String getPastorIgreja() {
        return pastorIgreja;
    }

    public void setPastorIgreja(String pastorIgreja) {
        this.pastorIgreja = pastorIgreja;
    }

    public Short getSexoId() {
        return sexoId;
    }

    public void setSexoId(Short sexoId) {
        this.sexoId = sexoId;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Date getAtualizacao() {
        return atualizacao;
    }

    public void setAtualizacao(Date atualizacao) {
        this.atualizacao = atualizacao;
    }

    public String getcEPResidencial() {
        return cEPResidencial;
    }

    public void setcEPResidencial(String cEPResidencial) {
        this.cEPResidencial = cEPResidencial;
    }

    public Docsoficiais getNomeDocOficial() {
        return nomeDocOficial;
    }

    public void setNomeDocOficial(Docsoficiais nomeDocOficial) {
        this.nomeDocOficial = nomeDocOficial;
    }

    public String getNumDocOficial() {
        return numDocOficial;
    }

    public void setNumDocOficial(String numDocOficial) {
        this.numDocOficial = numDocOficial;
    }

    public Formacao getNomeFormacao() {
        return nomeFormacao;
    }

    public void setNomeFormacao(Formacao nomeFormacao) {
        this.nomeFormacao = nomeFormacao;
    }

    public Profissoes getNomeProfissao() {
        return nomeProfissao;
    }

    public void setNomeProfissao(Profissoes nomeProfissao) {
        this.nomeProfissao = nomeProfissao;
    }

//    public String getCompProfissao() {
//        return compProfissao;
//    }
//
//    public void setCompProfissao(String compProfissao) {
//        this.compProfissao = compProfissao;
//    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dadoCadastroGeralId != null ? dadoCadastroGeralId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Dadocadastrogeral)) {
            return false;
        }
        Dadocadastrogeral other = (Dadocadastrogeral) object;
        if ((this.dadoCadastroGeralId == null && other.dadoCadastroGeralId != null) || (this.dadoCadastroGeralId != null && !this.dadoCadastroGeralId.equals(other.dadoCadastroGeralId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Dadocadastrogeral[ dadoCadastroGeralId=" + dadoCadastroGeralId + " ]";
    }

    public void setNomeUFResid(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
