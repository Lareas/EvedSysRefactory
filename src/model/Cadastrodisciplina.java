/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author ticoa
 */
@Entity
@Table(name = "cadastrodisciplina")
public class Cadastrodisciplina implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "CadastroDisciplinaId")
    private Integer cadastroDisciplinaId;

//    @Column(name = "DisciplinaId")
//    private Integer disciplinaId;
//    
//    @Column(name = "CodGrade")
//    private Integer codGrade;
    @ManyToOne
    @JoinColumn(name = "DisciplinaId", referencedColumnName = "DisciplinaId", nullable = true)
    private Disciplina nomeDisciplina;

    @ManyToOne
    @JoinColumn(name = "CodGrade", referencedColumnName = "codGrade", nullable = true)
    private EsGrade nomeGrade;

    @Column(name = "OrdemLista")
    private Short ordemLista;
    @Column(name = "Disciplina")
    private String disciplina;
    @Column(name = "Descricao")
    private String descricao;
    @Column(name = "Especificidade")
    private String especificidade;
    @Column(name = "Credito")
    private Short credito;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "CargaHoraria")
    private BigDecimal cargaHoraria;
    @Column(name = "AnoLetivo")
    private Short anoLetivo;
    @Column(name = "SemestreId")
    private Short semestreId;

    @ManyToOne
    @JoinColumn(name = "TurnoId", referencedColumnName = "turnoId", nullable = true)
    private Turno nomeTurno;

    @ManyToOne
    @JoinColumn(name = "TurmaId", referencedColumnName = "turmaId", nullable = true)
    private Turma nomeTurma;

//    @Column(name = "FuncionarioId")
//    private Integer funcionarioId;
    @ManyToOne
    @JoinColumn(name = "FuncionarioId", referencedColumnName = "funcionarioId", nullable = true)
    private Funcionario nomeProfessor1;

    @Column(name = "Localidade")
    private String localidade;
    @Column(name = "Domingo")
    private Boolean domingo;
    @Column(name = "Segunda")
    private Boolean segunda;
    @Column(name = "Terca")
    private Boolean terca;
    @Column(name = "Quarta")
    private Boolean quarta;
    @Column(name = "Quinta")
    private Boolean quinta;
    @Column(name = "Sexta")
    private Boolean sexta;
    @Column(name = "Sabado")
    private Boolean sabado;
    @Column(name = "Horario")
    private String horario;
    @Column(name = "Complementar")
    private Boolean complementar;
    @Column(name = "Supervisionada")
    private Boolean supervisionada;
    @Column(name = "Dispensa")
    private Boolean dispensa;
    @Column(name = "DispensaInstituicao")
    private String dispensaInstituicao;
    @Column(name = "DispensaProfessor")
    private String dispensaProfessor;
    @Column(name = "DataInicio")
    @Temporal(TemporalType.DATE)
    private Date dataInicio;
    @Column(name = "NumAulas")
    private Short numAulas;

//    @Column(name = "Professor2")
//    private Integer professor2;
    @ManyToOne
    @JoinColumn(name = "professor2", referencedColumnName = "funcionarioId", nullable = true)
    private Funcionario nomeProfessor2;

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

//    @Column(name = "TIpoDis")
//    private Integer tIpoDis;
    @ManyToOne
    @JoinColumn(name = "TipoDis", referencedColumnName = "codtipodis", nullable = true)
    private EsTipodis nomeTipoDis;

    @Column(name = "dataini")
    @Temporal(TemporalType.DATE)
    private Date dataini;
    @Column(name = "datafim")
    @Temporal(TemporalType.DATE)
    private Date datafim;

    @Column(name = "horaini")
    @Temporal(TemporalType.TIMESTAMP)
    private Date horaini;
    @Column(name = "horafim")
    @Temporal(TemporalType.TIMESTAMP)
    private Date horafim;

    @Column(name = "labelData")
    private String labelData;
    @Column(name = "labelHora")
    private String labelHora;
    @Column(name = "limitetotal")
    private Short limitetotal;
    @Column(name = "limiteregular")
    private Short limiteregular;
    @Column(name = "limiteouvinte")
    private Short limiteouvinte;
    @Column(name = "AvisarCom")
    private Short avisarCom;

//    @Column(name = "codsala")
//    private Integer codsala;
    @ManyToOne
    @JoinColumn(name = "codsala", referencedColumnName = "codessala", nullable = true)
    private EsSalas nomeSala;

    @Lob
    @Column(name = "Obs")
    private byte[] obs;
//    @Column(name = "CodLocalidade")
//    private Integer codLocalidade;
//    @Column(name = "CodEspecificidade")
//    private String codEspecificidade;

    @ManyToOne
    @JoinColumn(name = "CodLocalidade", referencedColumnName = "localidadeId", nullable = true)
    private Localidade nomeLocalidade;

    @ManyToOne
    @JoinColumn(name = "CodEspecificidade", referencedColumnName = "especificidadeId", nullable = true)
    private Especificidade nomeEspecificidade;

    @Column(name = "EvedSys")
    private Boolean evedSys;
    @Lob
    @Column(name = "ProgramaCurso")
    private byte[] programaCurso;
    @Column(name = "StatusMsg")
    private String statusMsg;
    @Column(name = "NomeArquivo")
    private String nomeArquivo;
    @Column(name = "PathArquivo")
    private String pathArquivo;

    public Cadastrodisciplina() {
    }

    public Cadastrodisciplina(Integer cadastroDisciplinaId) {
        this.cadastroDisciplinaId = cadastroDisciplinaId;
    }

    public Integer getCadastroDisciplinaId() {
        return cadastroDisciplinaId;
    }

    public void setCadastroDisciplinaId(Integer cadastroDisciplinaId) {
        this.cadastroDisciplinaId = cadastroDisciplinaId;
    }

    public Disciplina getNomeDisciplina() {
        return nomeDisciplina;
    }

    public void setNomeDisciplina(Disciplina nomeDisciplina) {
        this.nomeDisciplina = nomeDisciplina;
    }

    public EsGrade getNomeGrade() {
        return nomeGrade;
    }

    public void setNomeGrade(EsGrade nomeGrade) {
        this.nomeGrade = nomeGrade;
    }

    public Short getOrdemLista() {
        return ordemLista;
    }

    public void setOrdemLista(Short ordemLista) {
        this.ordemLista = ordemLista;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getEspecificidade() {
        return especificidade;
    }

    public void setEspecificidade(String especificidade) {
        this.especificidade = especificidade;
    }

    public Short getCredito() {
        return credito;
    }

    public void setCredito(Short credito) {
        this.credito = credito;
    }

    public BigDecimal getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(BigDecimal cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public Short getAnoLetivo() {
        return anoLetivo;
    }

    public void setAnoLetivo(Short anoLetivo) {
        this.anoLetivo = anoLetivo;
    }

    public Short getSemestreId() {
        return semestreId;
    }

    public void setSemestreId(Short semestreId) {
        this.semestreId = semestreId;
    }

    public Turno getNomeTurno() {
        return nomeTurno;
    }

    public void setNomeTurno(Turno nomeTurno) {
        this.nomeTurno = nomeTurno;
    }

    public Turma getNomeTurma() {
        return nomeTurma;
    }

    public void setNomeTurma(Turma nomeTurma) {
        this.nomeTurma = nomeTurma;
    }

    public Funcionario getNomeProfessor1() {
        return nomeProfessor1;
    }

    public void setNomeProfessor1(Funcionario nomeProfessor1) {
        this.nomeProfessor1 = nomeProfessor1;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public Boolean getDomingo() {
        return domingo;
    }

    public void setDomingo(Boolean domingo) {
        this.domingo = domingo;
    }

    public Boolean getSegunda() {
        return segunda;
    }

    public void setSegunda(Boolean segunda) {
        this.segunda = segunda;
    }

    public Boolean getTerca() {
        return terca;
    }

    public void setTerca(Boolean terca) {
        this.terca = terca;
    }

    public Boolean getQuarta() {
        return quarta;
    }

    public void setQuarta(Boolean quarta) {
        this.quarta = quarta;
    }

    public Boolean getQuinta() {
        return quinta;
    }

    public void setQuinta(Boolean quinta) {
        this.quinta = quinta;
    }

    public Boolean getSexta() {
        return sexta;
    }

    public void setSexta(Boolean sexta) {
        this.sexta = sexta;
    }

    public Boolean getSabado() {
        return sabado;
    }

    public void setSabado(Boolean sabado) {
        this.sabado = sabado;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public Boolean getComplementar() {
        return complementar;
    }

    public void setComplementar(Boolean complementar) {
        this.complementar = complementar;
    }

    public Boolean getSupervisionada() {
        return supervisionada;
    }

    public void setSupervisionada(Boolean supervisionada) {
        this.supervisionada = supervisionada;
    }

    public Boolean getDispensa() {
        return dispensa;
    }

    public void setDispensa(Boolean dispensa) {
        this.dispensa = dispensa;
    }

    public String getDispensaInstituicao() {
        return dispensaInstituicao;
    }

    public void setDispensaInstituicao(String dispensaInstituicao) {
        this.dispensaInstituicao = dispensaInstituicao;
    }

    public String getDispensaProfessor() {
        return dispensaProfessor;
    }

    public void setDispensaProfessor(String dispensaProfessor) {
        this.dispensaProfessor = dispensaProfessor;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Short getNumAulas() {
        return numAulas;
    }

    public void setNumAulas(Short numAulas) {
        this.numAulas = numAulas;
    }

    public Funcionario getNomeProfessor2() {
        return nomeProfessor2;
    }

    public void setNomeProfessor2(Funcionario nomeProfessor2) {
        this.nomeProfessor2 = nomeProfessor2;
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

    public EsTipodis getNomeTipoDis() {
        return nomeTipoDis;
    }

    public void setNomeTipoDis(EsTipodis nomeTipoDis) {
        this.nomeTipoDis = nomeTipoDis;
    }

    public Date getDataini() {
        return dataini;
    }

    public void setDataini(Date dataini) {
        this.dataini = dataini;
    }

    public Date getDatafim() {
        return datafim;
    }

    public void setDatafim(Date datafim) {
        this.datafim = datafim;
    }

   

    public String getLabelData() {
        return labelData;
    }

    public void setLabelData(String labelData) {
        this.labelData = labelData;
    }

    public String getLabelHora() {
        return labelHora;
    }

    public void setLabelHora(String labelHora) {
        this.labelHora = labelHora;
    }

    public Short getLimitetotal() {
        return limitetotal;
    }

    public void setLimitetotal(Short limitetotal) {
        this.limitetotal = limitetotal;
    }

    public Short getLimiteregular() {
        return limiteregular;
    }

    public void setLimiteregular(Short limiteregular) {
        this.limiteregular = limiteregular;
    }

    public Short getLimiteouvinte() {
        return limiteouvinte;
    }

    public void setLimiteouvinte(Short limiteouvinte) {
        this.limiteouvinte = limiteouvinte;
    }

    public Date getHoraini() {
        return horaini;
    }

    public void setHoraini(Date horaini) {
        this.horaini = horaini;
    }

    public Date getHorafim() {
        return horafim;
    }

    public void setHorafim(Date horafim) {
        this.horafim = horafim;
    }

    public Short getAvisarCom() {
        return avisarCom;
    }

    public void setAvisarCom(Short avisarCom) {
        this.avisarCom = avisarCom;
    }

    public EsSalas getNomeSala() {
        return nomeSala;
    }

    public void setNomeSala(EsSalas nomeSala) {
        this.nomeSala = nomeSala;
    }

    public byte[] getObs() {
        return obs;
    }

    public void setObs(byte[] obs) {
        this.obs = obs;
    }

    public Localidade getNomeLocalidade() {
        return nomeLocalidade;
    }

    public void setNomeLocalidade(Localidade nomeLocalidade) {
        this.nomeLocalidade = nomeLocalidade;
    }

    public Especificidade getNomeEspecificidade() {
        return nomeEspecificidade;
    }

    public void setNomeEspecificidade(Especificidade nomeEspecificidade) {
        this.nomeEspecificidade = nomeEspecificidade;
    }

    public Boolean getEvedSys() {
        return evedSys;
    }

    public void setEvedSys(Boolean evedSys) {
        this.evedSys = evedSys;
    }

    public byte[] getProgramaCurso() {
        return programaCurso;
    }

    public void setProgramaCurso(byte[] programaCurso) {
        this.programaCurso = programaCurso;
    }

    public String getStatusMsg() {
        return statusMsg;
    }

    public void setStatusMsg(String statusMsg) {
        this.statusMsg = statusMsg;
    }

    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public void setNomeArquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }

    public String getPathArquivo() {
        return pathArquivo;
    }

    public void setPathArquivo(String pathArquivo) {
        this.pathArquivo = pathArquivo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cadastroDisciplinaId != null ? cadastroDisciplinaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cadastrodisciplina)) {
            return false;
        }
        Cadastrodisciplina other = (Cadastrodisciplina) object;
        if ((this.cadastroDisciplinaId == null && other.cadastroDisciplinaId != null) || (this.cadastroDisciplinaId != null && !this.cadastroDisciplinaId.equals(other.cadastroDisciplinaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Cadastrodisciplina[ cadastroDisciplinaId=" + cadastroDisciplinaId + " ]";
    }

}
