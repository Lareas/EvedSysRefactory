/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author ticoa
 */
@Entity
@Table(name = "lista_chamada")
public class ListaChamada implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "CodLista_Chamada")
    private Integer codListaChamada;

    @Column(name = "CodLista_Aula")
    private Integer codListaAula;
//    
    @Column(name = "CodAluno")
    private Integer codAluno;

//    @ManyToOne
//    @JoinColumn(name = "CodAluno", referencedColumnName = "dadoCadastroGeralId", nullable = true)
//    private Dadocadastrogeral nomeALuno;

    @Column(name = "Chamada")
    private String chamada;
    
    @Column(name = "ObsAlunoAula")
    private String obsAlunoAula;

    public ListaChamada() {
    }

    public Integer getCodListaAula() {
        return codListaAula;
    }

    public void setCodListaAula(Integer codListaAula) {
        this.codListaAula = codListaAula;
    }

    public Integer getCodAluno() {
        return codAluno;
    }

    public void setCodAluno(Integer codAluno) {
        this.codAluno = codAluno;
    }

    public ListaChamada(Integer codListaChamada) {
        this.codListaChamada = codListaChamada;
    }

    public Integer getCodListaChamada() {
        return codListaChamada;
    }

    public void setCodListaChamada(Integer codListaChamada) {
        this.codListaChamada = codListaChamada;
    }

    public String getObsAlunoAula() {
        return obsAlunoAula;
    }

    public void setObsAlunoAula(String obsAlunoAula) {
        this.obsAlunoAula = obsAlunoAula;
    }

//    public Integer getCodListaAula() {
//        return codListaAula;
//    }
//
//    public void setCodListaAula(Integer codListaAula) {
//        this.codListaAula = codListaAula;
//    }
//
//    public Integer getCodAluno() {
//        return codAluno;
//    }
//
//    public void setCodAluno(Integer codAluno) {
//        this.codAluno = codAluno;
//    }

    public String getChamada() {
        return chamada;
    }

    public void setChamada(String chamada) {
        this.chamada = chamada;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codListaChamada != null ? codListaChamada.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ListaChamada)) {
            return false;
        }
        ListaChamada other = (ListaChamada) object;
        if ((this.codListaChamada == null && other.codListaChamada != null) || (this.codListaChamada != null && !this.codListaChamada.equals(other.codListaChamada))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.ListaChamada[ codListaChamada=" + codListaChamada + " ]";
    }

}
