package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "programa")
public class Programa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ProgramaId")
    private Short programaId;
    @Basic(optional = false)
    @Column(name = "Programa")
    private String programa;
//    @Column(name = "Autorizacao")
//    private String autorizacao;
//    @Column(name = "Reconhecimento")
//    private String reconhecimento;
//    @Column(name = "Coordenador")
    private String coordenador;
    @Column(name = "CoordenadorTitulacao")
    private String coordenadorTitulacao;
    @Column(name = "Email")
    private String email;
    
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

//    @OneToMany(mappedBy = "programaEspec")
//    private List<Especificidade> especificidade;

    public Programa() {
    }

    public Programa(Short programaId) {
        this.programaId = programaId;
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

    
    public Programa(Short programaId, String programa) {
        this.programaId = programaId;
        this.programa = programa;
    }

    public Short getProgramaId() {
        return programaId;
    }

    public void setProgramaId(Short programaId) {
        this.programaId = programaId;
    }

    public String getPrograma() {
        return programa;
    }

    public void setPrograma(String programa) {
        this.programa = programa;
    }

//    public String getAutorizacao() {
//        return autorizacao;
//    }
//
//    public void setAutorizacao(String autorizacao) {
//        this.autorizacao = autorizacao;
//    }
//
//    public String getReconhecimento() {
//        return reconhecimento;
//    }
//
//    public void setReconhecimento(String reconhecimento) {
//        this.reconhecimento = reconhecimento;
//    }
    public String getCoordenador() {
        return coordenador;
    }

    public void setCoordenador(String coordenador) {
        this.coordenador = coordenador;
    }

    public String getCoordenadorTitulacao() {
        return coordenadorTitulacao;
    }

    public void setCoordenadorTitulacao(String coordenadorTitulacao) {
        this.coordenadorTitulacao = coordenadorTitulacao;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    //////////////////////////////////////////////////////
//    public List<Especificidade> getEspecificidade() {
//        return especificidade;
//    }
//
//    public void setEspecificidade(List<Especificidade> especificidade) {
//        this.especificidade = especificidade;
//    }
    //////////////////////////////////////////////////////

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (programaId != null ? programaId.hashCode() : 0);
        return hash;
    }

    

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Programa)) {
            return false;
        }
        Programa other = (Programa) object;
        if ((this.programaId == null && other.programaId != null) || (this.programaId != null && !this.programaId.equals(other.programaId))) {
            return false;
        }
        return true;
    }

    public String toString() {
        return programa;

//        return "====================================================\n"
//                + "Prog Id:       " + programaId +
//                 "\n Programa:    " + programa +
//                 "\n Coordenador: " + coordenador +
//                 "\n Coord. Tit:  " + coordenadorTitulacao +
//                 "\n E-mail     : " + email +
//                 "\n====================================================\n";
    }
}
