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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "dadocadastroprograma")
public class Dadocadastroprograma implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DadoCadastroProgramaId")
    private Integer dadoCadastroProgramaId;
    @Basic(optional = false)
    @Column(name = "DadoCadastroGeralId")
    private Integer dadoCadastroGeralId;
    @Column(name = "Localidade")
    private String localidade;
    @Column(name = "AnoLetivo")
    private Integer anoLetivo;
    @Column(name = "SemestreId")
    private Integer semestreId;
    @Column(name = "ModalidadeId")
    private Short modalidadeId;

//    @Column(name = "CursoId")
//    private Short cursoId;
    @ManyToOne
    @JoinColumn(name = "cursoId", referencedColumnName = "cursoId", nullable = false)
    private Curso nomeCurso;

    @ManyToOne
    @JoinColumn(name = "turnoId", referencedColumnName = "turnoId", nullable = false)
    private Turno nomeTurno;

    @ManyToOne
    @JoinColumn(name = "dadoCadastroProgramaSituacaoId", referencedColumnName = "dadoCadastroProgramaSituacaoId", nullable = false)
    private Dadocadastroprogramasituacao nomeSituacao;

//    @Column(name = "DadoCadastroProgramaSituacaoId")
//    private Short dadoCadastroProgramaSituacaoId;
    @Lob
    @Column(name = "AtualSituacaoObservacoes")
    private String atualSituacaoObservacoes;
    @Column(name = "DataCadastro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCadastro;
    @Column(name = "MatriculaTrancada")
    @Temporal(TemporalType.TIMESTAMP)
    private Date matriculaTrancada;
    @Column(name = "Desistencia")
    @Temporal(TemporalType.TIMESTAMP)
    private Date desistencia;
    @Column(name = "Cursando")
    @Temporal(TemporalType.TIMESTAMP)
    private Date cursando;
    @Column(name = "Abandono")
    @Temporal(TemporalType.TIMESTAMP)
    private Date abandono;
    @Column(name = "Reativado")
    @Temporal(TemporalType.TIMESTAMP)
    private Date reativado;
    @Column(name = "ColacaoGrau")
    @Temporal(TemporalType.TIMESTAMP)
    private Date colacaoGrau;
    @Column(name = "Desligado")
    @Temporal(TemporalType.TIMESTAMP)
    private Date desligado;

    @Column(name = "Foto3X4")
    private Boolean foto3X4;
    @Column(name = "Foto5X7")
    private Boolean foto5X7;
    
    @Column(name = "Historico2grau")
    private Boolean historico2grau;
    
    @Column(name = "Historico3grau")
    private Boolean historico3grau;

    @Column(name = "FotocopiaRG")
    private Boolean fotocopiaRG;
    @Column(name = "FotocopiaCPFoCIC")
    private Boolean fotocopiaCPFoCIC;
//    @Column(name = "AtestadoSaude")
//    private Boolean atestadoSaude;
    @Column(name = "CartaRecomendacao")
    private Boolean cartaRecomendacao;
    @Column(name = "RequerimentoMatricula")
    private Boolean requerimentoMatricula;
    @Column(name = "Questionario")
    private Boolean questionario;
    @Column(name = "BiografiaReligiosa")
    private Boolean biografiaReligiosa;

    @Column(name = "DataVestibularInicio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataVestibularInicio;

    @Lob
    @Column(name = "Biografia")
    private String biografia;
    @Column(name = "Especificidade")
    private String especificidade;
    @Column(name = "CategoriaAlunoId")
    private Short categoriaAlunoId;

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

    public Boolean getHistorico2grau() {
        return historico2grau;
    }

    public void setHistorico2grau(Boolean historico2grau) {
        this.historico2grau = historico2grau;
    }

    public Dadocadastroprograma() {
    }

    public Dadocadastroprograma(Integer dadoCadastroProgramaId) {
        this.dadoCadastroProgramaId = dadoCadastroProgramaId;
    }

    public Dadocadastroprograma(Integer dadoCadastroProgramaId, Integer dadoCadastroGeralId) {
        this.dadoCadastroProgramaId = dadoCadastroProgramaId;
        this.dadoCadastroGeralId = dadoCadastroGeralId;
    }

    public Date getDataInc() {
        return dataInc;
    }

    public void setDataInc(Date dataInc) {
        this.dataInc = dataInc;
    }


    public Boolean getHistorico3grau() {
        return historico3grau;
    }

    public void setHistorico3grau(Boolean historico3grau) {
        this.historico3grau = historico3grau;
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


    public Curso getNomeCurso() {
        return nomeCurso;
    }

    public void setNomeCurso(Curso nomeCurso) {
        this.nomeCurso = nomeCurso;
    }

    public Turno getNomeTurno() {
        return nomeTurno;
    }

    public void setNomeTurno(Turno nomeTurno) {
        this.nomeTurno = nomeTurno;
    }

    public Integer getDadoCadastroProgramaId() {
        return dadoCadastroProgramaId;
    }

    public void setDadoCadastroProgramaId(Integer dadoCadastroProgramaId) {
        this.dadoCadastroProgramaId = dadoCadastroProgramaId;
    }

    public Integer getDadoCadastroGeralId() {
        return dadoCadastroGeralId;
    }

    public void setDadoCadastroGeralId(Integer dadoCadastroGeralId) {
        this.dadoCadastroGeralId = dadoCadastroGeralId;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }


    public Dadocadastroprogramasituacao getNomeSituacao() {
        return nomeSituacao;
    }

    public void setNomeSituacao(Dadocadastroprogramasituacao nomeSituacao) {
        this.nomeSituacao = nomeSituacao;
    }

    public Integer getAnoLetivo() {
        return anoLetivo;
    }

    public void setAnoLetivo(Integer anoLetivo) {
        this.anoLetivo = anoLetivo;
    }

    public Integer getSemestreId() {
        return semestreId;
    }

    public void setSemestreId(Integer semestreId) {
        this.semestreId = semestreId;
    }

    public Short getModalidadeId() {
        return modalidadeId;
    }

    public void setModalidadeId(Short modalidadeId) {
        this.modalidadeId = modalidadeId;
    }

    public String getAtualSituacaoObservacoes() {
        return atualSituacaoObservacoes;
    }

    public void setAtualSituacaoObservacoes(String atualSituacaoObservacoes) {
        this.atualSituacaoObservacoes = atualSituacaoObservacoes;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Date getMatriculaTrancada() {
        return matriculaTrancada;
    }

    public void setMatriculaTrancada(Date matriculaTrancada) {
        this.matriculaTrancada = matriculaTrancada;
    }

    public Date getDesistencia() {
        return desistencia;
    }

    public void setDesistencia(Date desistencia) {
        this.desistencia = desistencia;
    }

    public Date getCursando() {
        return cursando;
    }

    public void setCursando(Date cursando) {
        this.cursando = cursando;
    }

    public Date getAbandono() {
        return abandono;
    }

    public void setAbandono(Date abandono) {
        this.abandono = abandono;
    }

    public Date getReativado() {
        return reativado;
    }

    public void setReativado(Date reativado) {
        this.reativado = reativado;
    }

    public Date getColacaoGrau() {
        return colacaoGrau;
    }

    public void setColacaoGrau(Date colacaoGrau) {
        this.colacaoGrau = colacaoGrau;
    }

    public Date getDesligado() {
        return desligado;
    }

    public void setDesligado(Date desligado) {
        this.desligado = desligado;
    }

    public Boolean getFoto3X4() {
        return foto3X4;
    }

    public void setFoto3X4(Boolean foto3X4) {
        this.foto3X4 = foto3X4;
    }

    public Boolean getFoto5X7() {
        return foto5X7;
    }

    public void setFoto5X7(Boolean foto5X7) {
        this.foto5X7 = foto5X7;
    }

    public Boolean getFotocopiaRG() {
        return fotocopiaRG;
    }

    public void setFotocopiaRG(Boolean fotocopiaRG) {
        this.fotocopiaRG = fotocopiaRG;
    }

    public Boolean getFotocopiaCPFoCIC() {
        return fotocopiaCPFoCIC;
    }

    public void setFotocopiaCPFoCIC(Boolean fotocopiaCPFoCIC) {
        this.fotocopiaCPFoCIC = fotocopiaCPFoCIC;
    }

//    public Boolean getAtestadoSaude() {
//        return atestadoSaude;
//    }
//
//    public void setAtestadoSaude(Boolean atestadoSaude) {
//        this.atestadoSaude = atestadoSaude;
//    }
    public Boolean getCartaRecomendacao() {
        return cartaRecomendacao;
    }

    public void setCartaRecomendacao(Boolean cartaRecomendacao) {
        this.cartaRecomendacao = cartaRecomendacao;
    }

    public Boolean getRequerimentoMatricula() {
        return requerimentoMatricula;
    }

    public void setRequerimentoMatricula(Boolean requerimentoMatricula) {
        this.requerimentoMatricula = requerimentoMatricula;
    }

    public Boolean getQuestionario() {
        return questionario;
    }

    public void setQuestionario(Boolean questionario) {
        this.questionario = questionario;
    }

    public Boolean getBiografiaReligiosa() {
        return biografiaReligiosa;
    }

    public void setBiografiaReligiosa(Boolean biografiaReligiosa) {
        this.biografiaReligiosa = biografiaReligiosa;
    }

    public Date getDataVestibularInicio() {
        return dataVestibularInicio;
    }

    public void setDataVestibularInicio(Date dataVestibularInicio) {
        this.dataVestibularInicio = dataVestibularInicio;
    }

    public String getBiografia() {
        return biografia;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }

    public String getEspecificidade() {
        return especificidade;
    }

    public void setEspecificidade(String especificidade) {
        this.especificidade = especificidade;
    }

    public Short getCategoriaAlunoId() {
        return categoriaAlunoId;
    }

    public void setCategoriaAlunoId(Short categoriaAlunoId) {
        this.categoriaAlunoId = categoriaAlunoId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dadoCadastroProgramaId != null ? dadoCadastroProgramaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Dadocadastroprograma)) {
            return false;
        }
        Dadocadastroprograma other = (Dadocadastroprograma) object;
        if ((this.dadoCadastroProgramaId == null && other.dadoCadastroProgramaId != null) || (this.dadoCadastroProgramaId != null && !this.dadoCadastroProgramaId.equals(other.dadoCadastroProgramaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Dadocadastroprograma[ dadoCadastroProgramaId=" + dadoCadastroProgramaId + " ]";
    }

}
