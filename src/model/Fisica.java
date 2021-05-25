package entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


@Entity
@Table(name = "FISICA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Fisica.findAll", query = "SELECT f FROM Fisica f")
    , @NamedQuery(name = "Fisica.findByCodigo", query = "SELECT f FROM Fisica f WHERE f.codigo = :codigo")
    , @NamedQuery(name = "Fisica.findByCodext", query = "SELECT f FROM Fisica f WHERE f.codext = :codext")
    , @NamedQuery(name = "Fisica.findByNome", query = "SELECT f FROM Fisica f WHERE f.nome = :nome")
    , @NamedQuery(name = "Fisica.findBySexo", query = "SELECT f FROM Fisica f WHERE f.sexo = :sexo")
    , @NamedQuery(name = "Fisica.findByDatanasc", query = "SELECT f FROM Fisica f WHERE f.datanasc = :datanasc")
    , @NamedQuery(name = "Fisica.findByCpf", query = "SELECT f FROM Fisica f WHERE f.cpf = :cpf")
    , @NamedQuery(name = "Fisica.findByRg", query = "SELECT f FROM Fisica f WHERE f.rg = :rg")
    , @NamedQuery(name = "Fisica.findByOrgaoexp", query = "SELECT f FROM Fisica f WHERE f.orgaoexp = :orgaoexp")
    , @NamedQuery(name = "Fisica.findByDataexp", query = "SELECT f FROM Fisica f WHERE f.dataexp = :dataexp")
    , @NamedQuery(name = "Fisica.findByLogradouro", query = "SELECT f FROM Fisica f WHERE f.logradouro = :logradouro")
    , @NamedQuery(name = "Fisica.findByCep", query = "SELECT f FROM Fisica f WHERE f.cep = :cep")
    , @NamedQuery(name = "Fisica.findByContato1", query = "SELECT f FROM Fisica f WHERE f.contato1 = :contato1")
    , @NamedQuery(name = "Fisica.findByContato2", query = "SELECT f FROM Fisica f WHERE f.contato2 = :contato2")
    , @NamedQuery(name = "Fisica.findByContato3", query = "SELECT f FROM Fisica f WHERE f.contato3 = :contato3")
    , @NamedQuery(name = "Fisica.findByEmail", query = "SELECT f FROM Fisica f WHERE f.email = :email")
    , @NamedQuery(name = "Fisica.findByObservacoes", query = "SELECT f FROM Fisica f WHERE f.observacoes = :observacoes")
    , @NamedQuery(name = "Fisica.findByAtiva", query = "SELECT f FROM Fisica f WHERE f.ativa = :ativa")
    , @NamedQuery(name = "Fisica.findByFornecedor", query = "SELECT f FROM Fisica f WHERE f.fornecedor = :fornecedor")
    , @NamedQuery(name = "Fisica.findByCliente", query = "SELECT f FROM Fisica f WHERE f.cliente = :cliente")
    , @NamedQuery(name = "Fisica.findByVendafaturada", query = "SELECT f FROM Fisica f WHERE f.vendafaturada = :vendafaturada")
    , @NamedQuery(name = "Fisica.findBySenha", query = "SELECT f FROM Fisica f WHERE f.senha = :senha")
    , @NamedQuery(name = "Fisica.findByColaborador", query = "SELECT f FROM Fisica f WHERE f.colaborador = :colaborador")
    , @NamedQuery(name = "Fisica.findByNomeBusca", query = "SELECT f FROM Fisica f WHERE f.nomeBusca = :nomeBusca")
    , @NamedQuery(name = "Fisica.findByItaH8", query = "SELECT f FROM Fisica f WHERE f.itaH8 = :itaH8")
    , @NamedQuery(name = "Fisica.findByItaRc", query = "SELECT f FROM Fisica f WHERE f.itaRc = :itaRc")
    , @NamedQuery(name = "Fisica.findByItaCracha", query = "SELECT f FROM Fisica f WHERE f.itaCracha = :itaCracha")
    , @NamedQuery(name = "Fisica.findByItaPortaria", query = "SELECT f FROM Fisica f WHERE f.itaPortaria = :itaPortaria")
    , @NamedQuery(name = "Fisica.findByItaOutrosesp", query = "SELECT f FROM Fisica f WHERE f.itaOutrosesp = :itaOutrosesp")
    , @NamedQuery(name = "Fisica.findByItaInstorigem", query = "SELECT f FROM Fisica f WHERE f.itaInstorigem = :itaInstorigem")
    , @NamedQuery(name = "Fisica.findByItaBolsistas", query = "SELECT f FROM Fisica f WHERE f.itaBolsistas = :itaBolsistas")
    , @NamedQuery(name = "Fisica.findByItaBolinst", query = "SELECT f FROM Fisica f WHERE f.itaBolinst = :itaBolinst")
    , @NamedQuery(name = "Fisica.findByItaBolinic", query = "SELECT f FROM Fisica f WHERE f.itaBolinic = :itaBolinic")
    , @NamedQuery(name = "Fisica.findByItaBolterm", query = "SELECT f FROM Fisica f WHERE f.itaBolterm = :itaBolterm")
    , @NamedQuery(name = "Fisica.findByItaQualtit", query = "SELECT f FROM Fisica f WHERE f.itaQualtit = :itaQualtit")
    , @NamedQuery(name = "Fisica.findByItaQualins", query = "SELECT f FROM Fisica f WHERE f.itaQualins = :itaQualins")
    , @NamedQuery(name = "Fisica.findByItaQualano", query = "SELECT f FROM Fisica f WHERE f.itaQualano = :itaQualano")
    , @NamedQuery(name = "Fisica.findByItaQualare", query = "SELECT f FROM Fisica f WHERE f.itaQualare = :itaQualare")
    , @NamedQuery(name = "Fisica.findByItaIngle", query = "SELECT f FROM Fisica f WHERE f.itaIngle = :itaIngle")
    , @NamedQuery(name = "Fisica.findByItaEsple", query = "SELECT f FROM Fisica f WHERE f.itaEsple = :itaEsple")
    , @NamedQuery(name = "Fisica.findByItaFrnle", query = "SELECT f FROM Fisica f WHERE f.itaFrnle = :itaFrnle")
    , @NamedQuery(name = "Fisica.findByItaIngfala", query = "SELECT f FROM Fisica f WHERE f.itaIngfala = :itaIngfala")
    , @NamedQuery(name = "Fisica.findByItaEspfala", query = "SELECT f FROM Fisica f WHERE f.itaEspfala = :itaEspfala")
    , @NamedQuery(name = "Fisica.findByItaFrnfala", query = "SELECT f FROM Fisica f WHERE f.itaFrnfala = :itaFrnfala")
    , @NamedQuery(name = "Fisica.findByItaIngescr", query = "SELECT f FROM Fisica f WHERE f.itaIngescr = :itaIngescr")
    , @NamedQuery(name = "Fisica.findByItaEspescr", query = "SELECT f FROM Fisica f WHERE f.itaEspescr = :itaEspescr")
    , @NamedQuery(name = "Fisica.findByItaFrnescr", query = "SELECT f FROM Fisica f WHERE f.itaFrnescr = :itaFrnescr")
    , @NamedQuery(name = "Fisica.findByItaIngente", query = "SELECT f FROM Fisica f WHERE f.itaIngente = :itaIngente")
    , @NamedQuery(name = "Fisica.findByItaEspente", query = "SELECT f FROM Fisica f WHERE f.itaEspente = :itaEspente")
    , @NamedQuery(name = "Fisica.findByItaFrnente", query = "SELECT f FROM Fisica f WHERE f.itaFrnente = :itaFrnente")
    , @NamedQuery(name = "Fisica.findByItaAnofor", query = "SELECT f FROM Fisica f WHERE f.itaAnofor = :itaAnofor")
    , @NamedQuery(name = "Fisica.findByItaTipoescola", query = "SELECT f FROM Fisica f WHERE f.itaTipoescola = :itaTipoescola")
    , @NamedQuery(name = "Fisica.findByItaCpor", query = "SELECT f FROM Fisica f WHERE f.itaCpor = :itaCpor")
    , @NamedQuery(name = "Fisica.findByItaCx", query = "SELECT f FROM Fisica f WHERE f.itaCx = :itaCx")
    , @NamedQuery(name = "Fisica.findByItaArea", query = "SELECT f FROM Fisica f WHERE f.itaArea = :itaArea")
    , @NamedQuery(name = "Fisica.findByCnpj", query = "SELECT f FROM Fisica f WHERE f.cnpj = :cnpj")
    , @NamedQuery(name = "Fisica.findByCro", query = "SELECT f FROM Fisica f WHERE f.cro = :cro")
    , @NamedQuery(name = "Fisica.findByUfcro", query = "SELECT f FROM Fisica f WHERE f.ufcro = :ufcro")
    , @NamedQuery(name = "Fisica.findByLogradouro2", query = "SELECT f FROM Fisica f WHERE f.logradouro2 = :logradouro2")
    , @NamedQuery(name = "Fisica.findByCep2", query = "SELECT f FROM Fisica f WHERE f.cep2 = :cep2")
    , @NamedQuery(name = "Fisica.findByCidade2", query = "SELECT f FROM Fisica f WHERE f.cidade2 = :cidade2")
    , @NamedQuery(name = "Fisica.findByEmail2", query = "SELECT f FROM Fisica f WHERE f.email2 = :email2")
    , @NamedQuery(name = "Fisica.findByEndereco1principal", query = "SELECT f FROM Fisica f WHERE f.endereco1principal = :endereco1principal")
    , @NamedQuery(name = "Fisica.findByCodContr", query = "SELECT f FROM Fisica f WHERE f.codContr = :codContr")
    , @NamedQuery(name = "Fisica.findByCodContrDv", query = "SELECT f FROM Fisica f WHERE f.codContrDv = :codContrDv")
    , @NamedQuery(name = "Fisica.findByApelido", query = "SELECT f FROM Fisica f WHERE f.apelido = :apelido")
    , @NamedQuery(name = "Fisica.findByLoginPS", query = "SELECT f FROM Fisica f WHERE f.loginPS = :loginPS")
    , @NamedQuery(name = "Fisica.findBySenhaPS", query = "SELECT f FROM Fisica f WHERE f.senhaPS = :senhaPS")
    , @NamedQuery(name = "Fisica.findByNumero", query = "SELECT f FROM Fisica f WHERE f.numero = :numero")
    , @NamedQuery(name = "Fisica.findByComplemento", query = "SELECT f FROM Fisica f WHERE f.complemento = :complemento")
    , @NamedQuery(name = "Fisica.findByNumero2", query = "SELECT f FROM Fisica f WHERE f.numero2 = :numero2")
    , @NamedQuery(name = "Fisica.findByComplemento2", query = "SELECT f FROM Fisica f WHERE f.complemento2 = :complemento2")
    , @NamedQuery(name = "Fisica.findByVctoPadraoDia", query = "SELECT f FROM Fisica f WHERE f.vctoPadraoDia = :vctoPadraoDia")
    , @NamedQuery(name = "Fisica.findByVctoPadraoUtil", query = "SELECT f FROM Fisica f WHERE f.vctoPadraoUtil = :vctoPadraoUtil")
    , @NamedQuery(name = "Fisica.findByContaOffice365", query = "SELECT f FROM Fisica f WHERE f.contaOffice365 = :contaOffice365")
    , @NamedQuery(name = "Fisica.findBySenhaOffice365", query = "SELECT f FROM Fisica f WHERE f.senhaOffice365 = :senhaOffice365")
    , @NamedQuery(name = "Fisica.findByMaeAutorizadaRetirarAluno", query = "SELECT f FROM Fisica f WHERE f.maeAutorizadaRetirarAluno = :maeAutorizadaRetirarAluno")
    , @NamedQuery(name = "Fisica.findByPaiAutorizadoRetirarAluno", query = "SELECT f FROM Fisica f WHERE f.paiAutorizadoRetirarAluno = :paiAutorizadoRetirarAluno")
    , @NamedQuery(name = "Fisica.findByPessoaBoleto", query = "SELECT f FROM Fisica f WHERE f.pessoaBoleto = :pessoaBoleto")
    , @NamedQuery(name = "Fisica.findByFalecido", query = "SELECT f FROM Fisica f WHERE f.falecido = :falecido")
    , @NamedQuery(name = "Fisica.findByIdfkOrgaoRegistro", query = "SELECT f FROM Fisica f WHERE f.idfkOrgaoRegistro = :idfkOrgaoRegistro")
    , @NamedQuery(name = "Fisica.findByResponsavel", query = "SELECT f FROM Fisica f WHERE f.responsavel = :responsavel")
    , @NamedQuery(name = "Fisica.findByPagarBanco", query = "SELECT f FROM Fisica f WHERE f.pagarBanco = :pagarBanco")
    , @NamedQuery(name = "Fisica.findByPagarAgencia", query = "SELECT f FROM Fisica f WHERE f.pagarAgencia = :pagarAgencia")
    , @NamedQuery(name = "Fisica.findByPagarConta", query = "SELECT f FROM Fisica f WHERE f.pagarConta = :pagarConta")
    , @NamedQuery(name = "Fisica.findByPagarDac", query = "SELECT f FROM Fisica f WHERE f.pagarDac = :pagarDac")
    , @NamedQuery(name = "Fisica.findByQuantidadeFilho", query = "SELECT f FROM Fisica f WHERE f.quantidadeFilho = :quantidadeFilho")
    , @NamedQuery(name = "Fisica.findByOmitirCpfNfse", query = "SELECT f FROM Fisica f WHERE f.omitirCpfNfse = :omitirCpfNfse")
    , @NamedQuery(name = "Fisica.findByPassaporteNumero", query = "SELECT f FROM Fisica f WHERE f.passaporteNumero = :passaporteNumero")
    , @NamedQuery(name = "Fisica.findByPassaporteExpedicao", query = "SELECT f FROM Fisica f WHERE f.passaporteExpedicao = :passaporteExpedicao")
    , @NamedQuery(name = "Fisica.findByPassaporteValidade", query = "SELECT f FROM Fisica f WHERE f.passaporteValidade = :passaporteValidade")
    , @NamedQuery(name = "Fisica.findByIdInep", query = "SELECT f FROM Fisica f WHERE f.idInep = :idInep")
    , @NamedQuery(name = "Fisica.findByUtilizaNomeSocial", query = "SELECT f FROM Fisica f WHERE f.utilizaNomeSocial = :utilizaNomeSocial")
    , @NamedQuery(name = "Fisica.findByNomeCivilRegistrado", query = "SELECT f FROM Fisica f WHERE f.nomeCivilRegistrado = :nomeCivilRegistrado")
    , @NamedQuery(name = "Fisica.findByCodextTmp", query = "SELECT f FROM Fisica f WHERE f.codextTmp = :codextTmp")
    , @NamedQuery(name = "Fisica.findBySolicitacaoMatriculaSenha", query = "SELECT f FROM Fisica f WHERE f.solicitacaoMatriculaSenha = :solicitacaoMatriculaSenha")
    , @NamedQuery(name = "Fisica.findByEmailCorporativo", query = "SELECT f FROM Fisica f WHERE f.emailCorporativo = :emailCorporativo")})
public class Fisica implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CODIGO")
    private Integer codigo;
    
    @Column(name = "CODEXT")
    private String codext;
    
    @Basic(optional = false)
    @Column(name = "NOME")
    private String nome;
    @Column(name = "SEXO")
    private String sexo;
    
    @Column(name = "DATANASC")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datanasc;
    
    @Column(name = "CPF")
    private String cpf;
    @Column(name = "RG")
    private String rg;
    @Column(name = "ORGAOEXP")
    private String orgaoexp;
    @Column(name = "DATAEXP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataexp;
    @Column(name = "LOGRADOURO")
    private String logradouro;
    @Column(name = "CEP")
    private String cep;
    @Column(name = "CONTATO1")
    private String contato1;
    @Column(name = "CONTATO2")
    private String contato2;
    @Column(name = "CONTATO3")
    private String contato3;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "OBSERVACOES")
    private String observacoes;
    @Basic(optional = false)
    @Column(name = "ATIVA")
    private short ativa;
    @Basic(optional = false)
    @Column(name = "FORNECEDOR")
    private short fornecedor;
    @Basic(optional = false)
    @Column(name = "CLIENTE")
    private short cliente;
    @Basic(optional = false)
    @Column(name = "VENDAFATURADA")
    private short vendafaturada;
    @Column(name = "SENHA")
    private String senha;
    @Basic(optional = false)
    @Column(name = "COLABORADOR")
    private short colaborador;
    @Basic(optional = false)
    @Column(name = "NOME_BUSCA")
    private String nomeBusca;
    @Column(name = "ITA_H8")
    private String itaH8;
    @Column(name = "ITA_RC")
    private String itaRc;
    @Column(name = "ITA_CRACHA")
    private String itaCracha;
    @Column(name = "ITA_PORTARIA")
    private String itaPortaria;
    @Column(name = "ITA_OUTROSESP")
    private String itaOutrosesp;
    @Column(name = "ITA_INSTORIGEM")
    private String itaInstorigem;
    @Column(name = "ITA_BOLSISTAS")
    private Short itaBolsistas;
    @Column(name = "ITA_BOLINST")
    private String itaBolinst;
    @Column(name = "ITA_BOLINIC")
    @Temporal(TemporalType.TIMESTAMP)
    private Date itaBolinic;
    @Column(name = "ITA_BOLTERM")
    @Temporal(TemporalType.TIMESTAMP)
    private Date itaBolterm;
    @Column(name = "ITA_QUALTIT")
    private String itaQualtit;
    @Column(name = "ITA_QUALINS")
    private String itaQualins;
    @Column(name = "ITA_QUALANO")
    private String itaQualano;
    @Column(name = "ITA_QUALARE")
    private String itaQualare;
    @Column(name = "ITA_INGLE")
    private Short itaIngle;
    @Column(name = "ITA_ESPLE")
    private Short itaEsple;
    @Column(name = "ITA_FRNLE")
    private Short itaFrnle;
    @Column(name = "ITA_INGFALA")
    private Short itaIngfala;
    @Column(name = "ITA_ESPFALA")
    private Short itaEspfala;
    @Column(name = "ITA_FRNFALA")
    private Short itaFrnfala;
    @Column(name = "ITA_INGESCR")
    private Short itaIngescr;
    @Column(name = "ITA_ESPESCR")
    private Short itaEspescr;
    @Column(name = "ITA_FRNESCR")
    private Short itaFrnescr;
    @Column(name = "ITA_INGENTE")
    private Short itaIngente;
    @Column(name = "ITA_ESPENTE")
    private Short itaEspente;
    @Column(name = "ITA_FRNENTE")
    private Short itaFrnente;
    @Column(name = "ITA_ANOFOR")
    private Integer itaAnofor;
    @Column(name = "ITA_TIPOESCOLA")
    private Short itaTipoescola;
    @Column(name = "ITA_CPOR")
    private Short itaCpor;
    @Column(name = "ITA_CX")
    private Integer itaCx;
    @Column(name = "ITA_AREA")
    private Short itaArea;
    @Column(name = "CNPJ")
    private String cnpj;
    @Column(name = "CRO")
    private String cro;
    @Column(name = "UFCRO")
    private String ufcro;
    @Column(name = "LOGRADOURO2")
    private String logradouro2;
    @Column(name = "CEP2")
    private String cep2;
    @Column(name = "CIDADE2")
    private Integer cidade2;
    @Column(name = "EMAIL2")
    private String email2;
    @Column(name = "ENDERECO1PRINCIPAL")
    private Short endereco1principal;
    @Column(name = "COD_CONTR")
    private Integer codContr;
    @Column(name = "COD_CONTR_DV")
    private Short codContrDv;
    @Column(name = "APELIDO")
    private String apelido;
    @Column(name = "LoginPS")
    private String loginPS;
    @Column(name = "SenhaPS")
    private String senhaPS;
    @Column(name = "NUMERO")
    private String numero;
    @Column(name = "COMPLEMENTO")
    private String complemento;
    @Column(name = "NUMERO2")
    private String numero2;
    @Column(name = "COMPLEMENTO2")
    private String complemento2;
    @Column(name = "VCTO_PADRAO_DIA")
    private Integer vctoPadraoDia;
    @Column(name = "VCTO_PADRAO_UTIL")
    private Short vctoPadraoUtil;
    @Column(name = "CONTA_OFFICE365")
    private String contaOffice365;
    @Column(name = "SENHA_OFFICE365")
    private String senhaOffice365;
    @Basic(optional = false)
    @Column(name = "MAE_AUTORIZADA_RETIRAR_ALUNO")
    private int maeAutorizadaRetirarAluno;
    @Basic(optional = false)
    @Column(name = "PAI_AUTORIZADO_RETIRAR_ALUNO")
    private int paiAutorizadoRetirarAluno;
    @Column(name = "PESSOA_BOLETO")
    private Short pessoaBoleto;
    @Column(name = "FALECIDO")
    private Short falecido;
    @Column(name = "IDFK_ORGAO_REGISTRO")
    private Integer idfkOrgaoRegistro;
    @Column(name = "RESPONSAVEL")
    private Short responsavel;
    @Column(name = "PAGAR_BANCO")
    private Integer pagarBanco;
    @Column(name = "PAGAR_AGENCIA")
    private String pagarAgencia;
    @Column(name = "PAGAR_CONTA")
    private String pagarConta;
    @Column(name = "PAGAR_DAC")
    private String pagarDac;
    @Column(name = "QUANTIDADE_FILHO")
    private Short quantidadeFilho;
    @Column(name = "OMITIR_CPF_NFSE")
    private Short omitirCpfNfse;
    @Column(name = "PASSAPORTE_NUMERO")
    private String passaporteNumero;
    @Column(name = "PASSAPORTE_EXPEDICAO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date passaporteExpedicao;
    @Column(name = "PASSAPORTE_VALIDADE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date passaporteValidade;
    @Column(name = "ID_INEP")
    private String idInep;
    @Column(name = "UTILIZA_NOME_SOCIAL")
    private Short utilizaNomeSocial;
    @Column(name = "NOME_CIVIL_REGISTRADO")
    private String nomeCivilRegistrado;
    @Column(name = "CODEXT_TMP")
    private String codextTmp;
    @Column(name = "SOLICITACAO_MATRICULA_SENHA")
    private String solicitacaoMatriculaSenha;
    @Column(name = "EMAIL_CORPORATIVO")
    private String emailCorporativo;
    @OneToMany(mappedBy = "idfkFisicaEnderecoIgual")
    private Collection<Fisica> fisicaCollection;
    @JoinColumn(name = "IDFK_FISICA_ENDERECO_IGUAL", referencedColumnName = "CODIGO")
    @ManyToOne
    private Fisica idfkFisicaEnderecoIgual;

    public Fisica() {
    }

    public Fisica(Integer codigo) {
        this.codigo = codigo;
    }

    public Fisica(Integer codigo, String nome, short ativa, short fornecedor, short cliente, short vendafaturada, short colaborador, String nomeBusca, int maeAutorizadaRetirarAluno, int paiAutorizadoRetirarAluno) {
        this.codigo = codigo;
        this.nome = nome;
        this.ativa = ativa;
        this.fornecedor = fornecedor;
        this.cliente = cliente;
        this.vendafaturada = vendafaturada;
        this.colaborador = colaborador;
        this.nomeBusca = nomeBusca;
        this.maeAutorizadaRetirarAluno = maeAutorizadaRetirarAluno;
        this.paiAutorizadoRetirarAluno = paiAutorizadoRetirarAluno;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getCodext() {
        return codext;
    }

    public void setCodext(String codext) {
        this.codext = codext;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Date getDatanasc() {
        return datanasc;
    }

    public void setDatanasc(Date datanasc) {
        this.datanasc = datanasc;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getOrgaoexp() {
        return orgaoexp;
    }

    public void setOrgaoexp(String orgaoexp) {
        this.orgaoexp = orgaoexp;
    }

    public Date getDataexp() {
        return dataexp;
    }

    public void setDataexp(Date dataexp) {
        this.dataexp = dataexp;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getContato1() {
        return contato1;
    }

    public void setContato1(String contato1) {
        this.contato1 = contato1;
    }

    public String getContato2() {
        return contato2;
    }

    public void setContato2(String contato2) {
        this.contato2 = contato2;
    }

    public String getContato3() {
        return contato3;
    }

    public void setContato3(String contato3) {
        this.contato3 = contato3;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public short getAtiva() {
        return ativa;
    }

    public void setAtiva(short ativa) {
        this.ativa = ativa;
    }

    public short getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(short fornecedor) {
        this.fornecedor = fornecedor;
    }

    public short getCliente() {
        return cliente;
    }

    public void setCliente(short cliente) {
        this.cliente = cliente;
    }

    public short getVendafaturada() {
        return vendafaturada;
    }

    public void setVendafaturada(short vendafaturada) {
        this.vendafaturada = vendafaturada;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public short getColaborador() {
        return colaborador;
    }

    public void setColaborador(short colaborador) {
        this.colaborador = colaborador;
    }

    public String getNomeBusca() {
        return nomeBusca;
    }

    public void setNomeBusca(String nomeBusca) {
        this.nomeBusca = nomeBusca;
    }

    public String getItaH8() {
        return itaH8;
    }

    public void setItaH8(String itaH8) {
        this.itaH8 = itaH8;
    }

    public String getItaRc() {
        return itaRc;
    }

    public void setItaRc(String itaRc) {
        this.itaRc = itaRc;
    }

    public String getItaCracha() {
        return itaCracha;
    }

    public void setItaCracha(String itaCracha) {
        this.itaCracha = itaCracha;
    }

    public String getItaPortaria() {
        return itaPortaria;
    }

    public void setItaPortaria(String itaPortaria) {
        this.itaPortaria = itaPortaria;
    }

    public String getItaOutrosesp() {
        return itaOutrosesp;
    }

    public void setItaOutrosesp(String itaOutrosesp) {
        this.itaOutrosesp = itaOutrosesp;
    }

    public String getItaInstorigem() {
        return itaInstorigem;
    }

    public void setItaInstorigem(String itaInstorigem) {
        this.itaInstorigem = itaInstorigem;
    }

    public Short getItaBolsistas() {
        return itaBolsistas;
    }

    public void setItaBolsistas(Short itaBolsistas) {
        this.itaBolsistas = itaBolsistas;
    }

    public String getItaBolinst() {
        return itaBolinst;
    }

    public void setItaBolinst(String itaBolinst) {
        this.itaBolinst = itaBolinst;
    }

    public Date getItaBolinic() {
        return itaBolinic;
    }

    public void setItaBolinic(Date itaBolinic) {
        this.itaBolinic = itaBolinic;
    }

    public Date getItaBolterm() {
        return itaBolterm;
    }

    public void setItaBolterm(Date itaBolterm) {
        this.itaBolterm = itaBolterm;
    }

    public String getItaQualtit() {
        return itaQualtit;
    }

    public void setItaQualtit(String itaQualtit) {
        this.itaQualtit = itaQualtit;
    }

    public String getItaQualins() {
        return itaQualins;
    }

    public void setItaQualins(String itaQualins) {
        this.itaQualins = itaQualins;
    }

    public String getItaQualano() {
        return itaQualano;
    }

    public void setItaQualano(String itaQualano) {
        this.itaQualano = itaQualano;
    }

    public String getItaQualare() {
        return itaQualare;
    }

    public void setItaQualare(String itaQualare) {
        this.itaQualare = itaQualare;
    }

    public Short getItaIngle() {
        return itaIngle;
    }

    public void setItaIngle(Short itaIngle) {
        this.itaIngle = itaIngle;
    }

    public Short getItaEsple() {
        return itaEsple;
    }

    public void setItaEsple(Short itaEsple) {
        this.itaEsple = itaEsple;
    }

    public Short getItaFrnle() {
        return itaFrnle;
    }

    public void setItaFrnle(Short itaFrnle) {
        this.itaFrnle = itaFrnle;
    }

    public Short getItaIngfala() {
        return itaIngfala;
    }

    public void setItaIngfala(Short itaIngfala) {
        this.itaIngfala = itaIngfala;
    }

    public Short getItaEspfala() {
        return itaEspfala;
    }

    public void setItaEspfala(Short itaEspfala) {
        this.itaEspfala = itaEspfala;
    }

    public Short getItaFrnfala() {
        return itaFrnfala;
    }

    public void setItaFrnfala(Short itaFrnfala) {
        this.itaFrnfala = itaFrnfala;
    }

    public Short getItaIngescr() {
        return itaIngescr;
    }

    public void setItaIngescr(Short itaIngescr) {
        this.itaIngescr = itaIngescr;
    }

    public Short getItaEspescr() {
        return itaEspescr;
    }

    public void setItaEspescr(Short itaEspescr) {
        this.itaEspescr = itaEspescr;
    }

    public Short getItaFrnescr() {
        return itaFrnescr;
    }

    public void setItaFrnescr(Short itaFrnescr) {
        this.itaFrnescr = itaFrnescr;
    }

    public Short getItaIngente() {
        return itaIngente;
    }

    public void setItaIngente(Short itaIngente) {
        this.itaIngente = itaIngente;
    }

    public Short getItaEspente() {
        return itaEspente;
    }

    public void setItaEspente(Short itaEspente) {
        this.itaEspente = itaEspente;
    }

    public Short getItaFrnente() {
        return itaFrnente;
    }

    public void setItaFrnente(Short itaFrnente) {
        this.itaFrnente = itaFrnente;
    }

    public Integer getItaAnofor() {
        return itaAnofor;
    }

    public void setItaAnofor(Integer itaAnofor) {
        this.itaAnofor = itaAnofor;
    }

    public Short getItaTipoescola() {
        return itaTipoescola;
    }

    public void setItaTipoescola(Short itaTipoescola) {
        this.itaTipoescola = itaTipoescola;
    }

    public Short getItaCpor() {
        return itaCpor;
    }

    public void setItaCpor(Short itaCpor) {
        this.itaCpor = itaCpor;
    }

    public Integer getItaCx() {
        return itaCx;
    }

    public void setItaCx(Integer itaCx) {
        this.itaCx = itaCx;
    }

    public Short getItaArea() {
        return itaArea;
    }

    public void setItaArea(Short itaArea) {
        this.itaArea = itaArea;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getCro() {
        return cro;
    }

    public void setCro(String cro) {
        this.cro = cro;
    }

    public String getUfcro() {
        return ufcro;
    }

    public void setUfcro(String ufcro) {
        this.ufcro = ufcro;
    }

    public String getLogradouro2() {
        return logradouro2;
    }

    public void setLogradouro2(String logradouro2) {
        this.logradouro2 = logradouro2;
    }

    public String getCep2() {
        return cep2;
    }

    public void setCep2(String cep2) {
        this.cep2 = cep2;
    }

    public Integer getCidade2() {
        return cidade2;
    }

    public void setCidade2(Integer cidade2) {
        this.cidade2 = cidade2;
    }

    public String getEmail2() {
        return email2;
    }

    public void setEmail2(String email2) {
        this.email2 = email2;
    }

    public Short getEndereco1principal() {
        return endereco1principal;
    }

    public void setEndereco1principal(Short endereco1principal) {
        this.endereco1principal = endereco1principal;
    }

    public Integer getCodContr() {
        return codContr;
    }

    public void setCodContr(Integer codContr) {
        this.codContr = codContr;
    }

    public Short getCodContrDv() {
        return codContrDv;
    }

    public void setCodContrDv(Short codContrDv) {
        this.codContrDv = codContrDv;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public String getLoginPS() {
        return loginPS;
    }

    public void setLoginPS(String loginPS) {
        this.loginPS = loginPS;
    }

    public String getSenhaPS() {
        return senhaPS;
    }

    public void setSenhaPS(String senhaPS) {
        this.senhaPS = senhaPS;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getNumero2() {
        return numero2;
    }

    public void setNumero2(String numero2) {
        this.numero2 = numero2;
    }

    public String getComplemento2() {
        return complemento2;
    }

    public void setComplemento2(String complemento2) {
        this.complemento2 = complemento2;
    }

    public Integer getVctoPadraoDia() {
        return vctoPadraoDia;
    }

    public void setVctoPadraoDia(Integer vctoPadraoDia) {
        this.vctoPadraoDia = vctoPadraoDia;
    }

    public Short getVctoPadraoUtil() {
        return vctoPadraoUtil;
    }

    public void setVctoPadraoUtil(Short vctoPadraoUtil) {
        this.vctoPadraoUtil = vctoPadraoUtil;
    }

    public String getContaOffice365() {
        return contaOffice365;
    }

    public void setContaOffice365(String contaOffice365) {
        this.contaOffice365 = contaOffice365;
    }

    public String getSenhaOffice365() {
        return senhaOffice365;
    }

    public void setSenhaOffice365(String senhaOffice365) {
        this.senhaOffice365 = senhaOffice365;
    }

    public int getMaeAutorizadaRetirarAluno() {
        return maeAutorizadaRetirarAluno;
    }

    public void setMaeAutorizadaRetirarAluno(int maeAutorizadaRetirarAluno) {
        this.maeAutorizadaRetirarAluno = maeAutorizadaRetirarAluno;
    }

    public int getPaiAutorizadoRetirarAluno() {
        return paiAutorizadoRetirarAluno;
    }

    public void setPaiAutorizadoRetirarAluno(int paiAutorizadoRetirarAluno) {
        this.paiAutorizadoRetirarAluno = paiAutorizadoRetirarAluno;
    }

    public Short getPessoaBoleto() {
        return pessoaBoleto;
    }

    public void setPessoaBoleto(Short pessoaBoleto) {
        this.pessoaBoleto = pessoaBoleto;
    }

    public Short getFalecido() {
        return falecido;
    }

    public void setFalecido(Short falecido) {
        this.falecido = falecido;
    }

    public Integer getIdfkOrgaoRegistro() {
        return idfkOrgaoRegistro;
    }

    public void setIdfkOrgaoRegistro(Integer idfkOrgaoRegistro) {
        this.idfkOrgaoRegistro = idfkOrgaoRegistro;
    }

    public Short getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(Short responsavel) {
        this.responsavel = responsavel;
    }

    public Integer getPagarBanco() {
        return pagarBanco;
    }

    public void setPagarBanco(Integer pagarBanco) {
        this.pagarBanco = pagarBanco;
    }

    public String getPagarAgencia() {
        return pagarAgencia;
    }

    public void setPagarAgencia(String pagarAgencia) {
        this.pagarAgencia = pagarAgencia;
    }

    public String getPagarConta() {
        return pagarConta;
    }

    public void setPagarConta(String pagarConta) {
        this.pagarConta = pagarConta;
    }

    public String getPagarDac() {
        return pagarDac;
    }

    public void setPagarDac(String pagarDac) {
        this.pagarDac = pagarDac;
    }

    public Short getQuantidadeFilho() {
        return quantidadeFilho;
    }

    public void setQuantidadeFilho(Short quantidadeFilho) {
        this.quantidadeFilho = quantidadeFilho;
    }

    public Short getOmitirCpfNfse() {
        return omitirCpfNfse;
    }

    public void setOmitirCpfNfse(Short omitirCpfNfse) {
        this.omitirCpfNfse = omitirCpfNfse;
    }

    public String getPassaporteNumero() {
        return passaporteNumero;
    }

    public void setPassaporteNumero(String passaporteNumero) {
        this.passaporteNumero = passaporteNumero;
    }

    public Date getPassaporteExpedicao() {
        return passaporteExpedicao;
    }

    public void setPassaporteExpedicao(Date passaporteExpedicao) {
        this.passaporteExpedicao = passaporteExpedicao;
    }

    public Date getPassaporteValidade() {
        return passaporteValidade;
    }

    public void setPassaporteValidade(Date passaporteValidade) {
        this.passaporteValidade = passaporteValidade;
    }

    public String getIdInep() {
        return idInep;
    }

    public void setIdInep(String idInep) {
        this.idInep = idInep;
    }

    public Short getUtilizaNomeSocial() {
        return utilizaNomeSocial;
    }

    public void setUtilizaNomeSocial(Short utilizaNomeSocial) {
        this.utilizaNomeSocial = utilizaNomeSocial;
    }

    public String getNomeCivilRegistrado() {
        return nomeCivilRegistrado;
    }

    public void setNomeCivilRegistrado(String nomeCivilRegistrado) {
        this.nomeCivilRegistrado = nomeCivilRegistrado;
    }

    public String getCodextTmp() {
        return codextTmp;
    }

    public void setCodextTmp(String codextTmp) {
        this.codextTmp = codextTmp;
    }

    public String getSolicitacaoMatriculaSenha() {
        return solicitacaoMatriculaSenha;
    }

    public void setSolicitacaoMatriculaSenha(String solicitacaoMatriculaSenha) {
        this.solicitacaoMatriculaSenha = solicitacaoMatriculaSenha;
    }

    public String getEmailCorporativo() {
        return emailCorporativo;
    }

    public void setEmailCorporativo(String emailCorporativo) {
        this.emailCorporativo = emailCorporativo;
    }

    @XmlTransient
    public Collection<Fisica> getFisicaCollection() {
        return fisicaCollection;
    }

    public void setFisicaCollection(Collection<Fisica> fisicaCollection) {
        this.fisicaCollection = fisicaCollection;
    }

    public Fisica getIdfkFisicaEnderecoIgual() {
        return idfkFisicaEnderecoIgual;
    }

    public void setIdfkFisicaEnderecoIgual(Fisica idfkFisicaEnderecoIgual) {
        this.idfkFisicaEnderecoIgual = idfkFisicaEnderecoIgual;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigo != null ? codigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Fisica)) {
            return false;
        }
        Fisica other = (Fisica) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Fisica[ codigo=" + codigo + " ]";
    }

}
