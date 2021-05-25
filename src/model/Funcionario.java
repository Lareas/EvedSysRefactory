/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

/**
 *
 * @author luiza
 */
@Entity
@Table(name = "funcionario")
public class Funcionario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "FuncionarioId")
    private Integer funcionarioId;
    @Basic(optional = false)
    @Column(name = "Nome")
    private String nome;
    @Basic(optional = false)
    @Column(name = "Professor")
    private boolean professor;
    @Basic(optional = false)
    @Column(name = "Coordenador")
    private boolean coordenador;
    @Column(name = "Senha")
    private String senha;
    @Column(name = "Especificidade")
    private String especificidade;
    @Column(name = "Funcao")
    private String funcao;
    @Column(name = "Academico")
    private Boolean academico;
    @Column(name = "Endereco")
    private String endereco;
    @Column(name = "Telefone")
    private String telefone;
    @Column(name = "Celular")
    private String celular;
    @Column(name = "Ramal")
    private String ramal;
    @Column(name = "Observacoes")
    private String observacoes;
    @Column(name = "Foto")
    private String foto;
    @Column(name = "email")
    private String email;
    @Column(name = "RG")
    private String rg;
    @Column(name = "sabado")
    private String sabado;
    @Column(name = "setor")
    private String setor;
    @Column(name = "DataAdmissao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAdmissao;
    @Column(name = "agenda")
    private Boolean agenda;
    @Column(name = "PA")
    private Boolean pa;
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
    @Column(name = "BairroResidencial")
    private String bairroResidencial;
    @Column(name = "CidadeResidencial")
    private String cidadeResidencial;

    @ManyToOne
    @JoinColumn(name = "UFResidencialId", referencedColumnName = "siglaEstadoId", nullable = true)
    private Siglaestado nomeUFResid;

//    @Column(name = "UFResidencialId")
//    private Short uFResidencialId;
    @Column(name = "CEPResidencial")
    private String cEPResidencial;
    @Column(name = "LocalNascimento")
    private String localNascimento;

//    @Column(name = "UFNascimentoId")
//    private Short uFNascimentoId;
    @ManyToOne
    @JoinColumn(name = "UFNascimentoId", referencedColumnName = "siglaEstadoId", nullable = true)
    private Siglaestado nomeUFNasc;

    @Column(name = "DataNascimento")
    @Temporal(TemporalType.DATE)
    private Date dataNascimento;

//    @Column(name = "NacionalidadeId")
//    private Short nacionalidadeId;
    @ManyToOne
    @JoinColumn(name = "NacionalidadeId", referencedColumnName = "nacionalidadeId", nullable = true)
    private Nacionalidade nomeNacionalidade;

//    @Column(name = "EstadoCivilId")
//    private Short estadoCivilId;
    @ManyToOne
    @JoinColumn(name = "EstadoCivilId", referencedColumnName = "estadoCivilId", nullable = true)
    private Estadocivil nomeEstadoCivil;

    @Column(name = "Conjuge")
    private String conjuge;
    @Column(name = "NDependentes")
    private Integer nDependentes;

//    @Column(name = "corPele")
//    private Short corPele;
    @ManyToOne
    @JoinColumn(name = "corPele", referencedColumnName = "codcordapele", nullable = true)
    private Cordapele nomeCorDaPele;
    
//    @Column(name = "codBanco")
//    private Short codBanco;
    @ManyToOne
    @JoinColumn(name = "codBanco", referencedColumnName = "chaveBanco", nullable = true)
    private Tabbancos nomeBanco;

    @Column(name = "Agencia")
    private String agencia;
    @Column(name = "ContaCorrente")
    private String contaCorrente;
    @Column(name = "facebook")
    private String facebook;
    @Column(name = "twitter")
    private String twitter;
    @Column(name = "instagram")
    private String instagram;

    @Column(name = "ativo")
    private Boolean ativo;

    @Column(name = "sexoId")
    private Short sexoId;
    
    @Column(name = "CPF")
    private String cpf;

    
    public Funcionario() {
    }

    public Funcionario(Integer funcionarioId) {
        this.funcionarioId = funcionarioId;
    }

    public Funcionario(int funcionarioId, String nome, String especificidade) {
        this.funcionarioId = funcionarioId;
        this.nome = nome;
        this.especificidade = especificidade;
    }

    public Tabbancos getNomeBanco() {
        return nomeBanco;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    
    public void setNomeBanco(Tabbancos nomeBanco) {
        this.nomeBanco = nomeBanco;
    }

    public Funcionario(Integer funcionarioId, String nome, boolean professor, boolean coordenador) {
        this.funcionarioId = funcionarioId;
        this.nome = nome;
        this.professor = professor;
        this.coordenador = coordenador;
    }

    public Integer getFuncionarioId() {
        return funcionarioId;
    }

    public void setFuncionarioId(Integer funcionarioId) {
        this.funcionarioId = funcionarioId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean getProfessor() {
        return professor;
    }

    public Short getSexoId() {
        return sexoId;
    }

    public void setSexoId(Short sexoId) {
        this.sexoId = sexoId;
    }

    public void setProfessor(boolean professor) {
        this.professor = professor;
    }

    public boolean getCoordenador() {
        return coordenador;
    }

    public String getcEPResidencial() {
        return cEPResidencial;
    }

    public void setcEPResidencial(String cEPResidencial) {
        this.cEPResidencial = cEPResidencial;
    }

    public Integer getnDependentes() {
        return nDependentes;
    }

    public void setnDependentes(Integer nDependentes) {
        this.nDependentes = nDependentes;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public void setCoordenador(boolean coordenador) {
        this.coordenador = coordenador;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEspecificidade() {
        return especificidade;
    }

    public void setEspecificidade(String especificidade) {
        this.especificidade = especificidade;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public Boolean getAcademico() {
        return academico;
    }

    public void setAcademico(Boolean academico) {
        this.academico = academico;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getRamal() {
        return ramal;
    }

    public void setRamal(String ramal) {
        this.ramal = ramal;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getSabado() {
        return sabado;
    }

    public void setSabado(String sabado) {
        this.sabado = sabado;
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }

    public Date getDataAdmissao() {
        return dataAdmissao;
    }

    public void setDataAdmissao(Date dataAdmissao) {
        this.dataAdmissao = dataAdmissao;
    }

    public Boolean getAgenda() {
        return agenda;
    }

    public void setAgenda(Boolean agenda) {
        this.agenda = agenda;
    }

    public Boolean getPa() {
        return pa;
    }

    public void setPa(Boolean pa) {
        this.pa = pa;
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

    public Integer getNDependentes() {
        return nDependentes;
    }

    public void setNDependentes(Integer nDependentes) {
        this.nDependentes = nDependentes;
    }

    public Siglaestado getNomeUFResid() {
        return nomeUFResid;
    }

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

    public Cordapele getNomeCorDaPele() {
        return nomeCorDaPele;
    }

    public void setNomeCorDaPele(Cordapele nomeCorDaPele) {
        this.nomeCorDaPele = nomeCorDaPele;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public String getContaCorrente() {
        return contaCorrente;
    }

    public void setContaCorrente(String contaCorrente) {
        this.contaCorrente = contaCorrente;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (funcionarioId != null ? funcionarioId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Funcionario)) {
            return false;
        }
        Funcionario other = (Funcionario) object;
        if ((this.funcionarioId == null && other.funcionarioId != null) || (this.funcionarioId != null && !this.funcionarioId.equals(other.funcionarioId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Funcionario[ funcionarioId=" + funcionarioId + " ]";
    }

}
