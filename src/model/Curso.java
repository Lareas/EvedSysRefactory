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
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "curso")
@XmlRootElement
public class Curso implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CursoId")
    private Integer cursoId;

    @Basic(optional = false)
    @Column(name = "Curso")
    private String curso;

    @Lob
    @Column(name = "Descricao")
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "ProgramaId", referencedColumnName = "ProgramaId", nullable = false)
    private Programa programaEspec;
    
    @ManyToOne
    @JoinColumn(name = "CodEspecificidade", referencedColumnName = "especificidadeId", nullable = false)
    private Especificidade nomeEspecificidade;

    @Column(name = "Titulacao")
    private String titulacao;
    @Column(name = "Grau")
    private String grau;

    @Column(name = "docFichaInsc")
    private String docFichaInsc;
    
    @Column(name = "docFormMatri")
    private String formMatri;
    
    @Column(name = "docDiploma")
    private String docDiploma;
    
    @Column(name = "docHistorico")
    private String historico;
    
    @Column(name = "docCarta1")
    private String docCarta1;
    
    @Column(name = "docCarta2")
    private String docCarta2;
    
    @Column(name = "docRG")
    private String docRG;
    
    @Column(name = "docCPF")
    private String docCPF;
    
    @Column(name = "docFoto")
    private String docFoto;
    
    @Column(name = "docTimbrada")
    private String docTimbrada;
    
    @Column(name = "docTestemunho")
    private String docTestemunho;
    
    @Column(name = "docCurricMin")
    private String curricMin;
    
    @Column(name = "docDissertacaoTCC")
    private String docDissertacaoTCC;
    
    @Column(name = "docEsbocoProjMin")
    private String docEsbocoProjMin;
    
    @Column(name = "docExameDmin")
    private String docExameDmin;

    @Column(name = "docDeclarConhecIgreja")
    private String docDeclarConhecIgreja;
    
    @Column(name = "nomeArq")
    private String nomeArq;
    
    @Column(name = "caminhoArq")
    private String caminhoArq;
    
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
    
    @Column(name = "sigla")
    private String sigla;
    
    @Column(name = "ativo")
    private Short ativo;
    
    
    @Column(name = "Duracao")
    private int duracao;

    public Curso() {
    }

    public Curso(Integer cursoId) {
        this.cursoId = cursoId;
    }

    public Curso(Integer cursoId, String curso) {
        this.cursoId = cursoId;
        this.curso = curso;
    }

    public Especificidade getNomeEspecificidade() {
        return nomeEspecificidade;
    }

    public void setNomeEspecificidade(Especificidade nomeEspecificidade) {
        this.nomeEspecificidade = nomeEspecificidade;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }
    
    

    public Short getAtivo() {
        return ativo;
    }

    public void setAtivo(Short ativo) {
        this.ativo = ativo;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }
    
    

    public String getNomeArq() {
        return nomeArq;
    }

    public void setNomeArq(String nomeArq) {
        this.nomeArq = nomeArq;
    }

    public String getCaminhoArq() {
        return caminhoArq;
    }

    public void setCaminhoArq(String caminhoArq) {
        this.caminhoArq = caminhoArq;
    }

    
    public Integer getCursoId() {
        return cursoId;
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
    
    

    public void setCursoId(Integer cursoId) {
        this.cursoId = cursoId;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getDocExameDmin() {
        return docExameDmin;
    }

    public void setDocExameDmin(String docExameDmin) {
        this.docExameDmin = docExameDmin;
    }
    

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTitulacao() {
        return titulacao;
    }

    public void setTitulacao(String titulacao) {
        this.titulacao = titulacao;
    }

    public String getGrau() {
        return grau;
    }

    public void setGrau(String grau) {
        this.grau = grau;
    }

    public Programa getProgramaEspec() {
        return programaEspec;
    }

    public void setProgramaEspec(Programa programaEspec) {
        this.programaEspec = programaEspec;
    }

    public String getDocFichaInsc() {
        return docFichaInsc;
    }

    public void setDocFichaInsc(String docFichaInsc) {
        this.docFichaInsc = docFichaInsc;
    }

    public String getFormMatri() {
        return formMatri;
    }

    public void setFormMatri(String formMatri) {
        this.formMatri = formMatri;
    }

    public String getDocDiploma() {
        return docDiploma;
    }

    public void setDocDiploma(String docDiploma) {
        this.docDiploma = docDiploma;
    }

    public String getHistorico() {
        return historico;
    }

    public void setHistorico(String historico) {
        this.historico = historico;
    }

    public String getDocCarta1() {
        return docCarta1;
    }

    public void setDocCarta1(String docCarta1) {
        this.docCarta1 = docCarta1;
    }

    public String getDocCarta2() {
        return docCarta2;
    }

    public void setDocCarta2(String docCarta2) {
        this.docCarta2 = docCarta2;
    }

    public String getDocRG() {
        return docRG;
    }

    public void setDocRG(String docRG) {
        this.docRG = docRG;
    }

    public String getDocCPF() {
        return docCPF;
    }

    public void setDocCPF(String docCPF) {
        this.docCPF = docCPF;
    }

    public String getDocFoto() {
        return docFoto;
    }

    public void setDocFoto(String docFoto) {
        this.docFoto = docFoto;
    }

    public String getDocTimbrada() {
        return docTimbrada;
    }

    public void setDocTimbrada(String docTimbrada) {
        this.docTimbrada = docTimbrada;
    }

    public String getDocTestemunho() {
        return docTestemunho;
    }

    public void setDocTestemunho(String docTestemunho) {
        this.docTestemunho = docTestemunho;
    }

    public String getCurricMin() {
        return curricMin;
    }

    public void setCurricMin(String curricMin) {
        this.curricMin = curricMin;
    }

    public String getDocDissertacaoTCC() {
        return docDissertacaoTCC;
    }

    public void setDocDissertacaoTCC(String docDissertacaoTCC) {
        this.docDissertacaoTCC = docDissertacaoTCC;
    }

    public String getDocEsbocoProjMin() {
        return docEsbocoProjMin;
    }

    public void setDocEsbocoProjMin(String docEsbocoProjMin) {
        this.docEsbocoProjMin = docEsbocoProjMin;
    }

    public String getDocDeclarConhecIgreja() {
        return docDeclarConhecIgreja;
    }

    public void setDocDeclarConhecIgreja(String docDeclarConhecIgreja) {
        this.docDeclarConhecIgreja = docDeclarConhecIgreja;
    }

    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cursoId != null ? cursoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Curso)) {
            return false;
        }
        Curso other = (Curso) object;
        if ((this.cursoId == null && other.cursoId != null) || (this.cursoId != null && !this.cursoId.equals(other.cursoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Curso[ cursoId=" + cursoId + " ]";
    }

}
