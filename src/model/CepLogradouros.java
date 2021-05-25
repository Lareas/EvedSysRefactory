package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cep_logradouros")
public class CepLogradouros implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CODIGO")
    private Integer codigo;
    
    @Basic(optional = false)
    @Column(name = "NOME")
    private String nome;
    
    @Column(name = "COMPLEMENTO")
    private String complemento;

//    @Column(name = "BAIRRO")
//    private Integer bairro;
    @ManyToOne(optional = true)
    @JoinColumn(name = "bairro", referencedColumnName = "codigo", nullable = true)
    private CepBairros nome_do_bairro;
    
    @Column(name = "UF")
    private String uf;

////    @Column(name = "CIDADE")
////    private Integer cidade;
    @ManyToOne
    @JoinColumn(name = "cidade", referencedColumnName = "codigo", nullable = true)
    private CepCidades nomeCidade;

    @Column(name = "CEP")
    private String cep;

    //    @Column(name = "TIPO_LOG")
//    private Integer tipoLog;
    @ManyToOne
    @JoinColumn(name = "TIPO_LOG", referencedColumnName = "codigo", nullable = true)
    private CepTipoLog nomeTipoLog;

    // É ESSA CHAMADA QUE FAZ TUDO FUNCIONAR
    public String getNomeDoBairro() {
        return getNome_do_bairro().getNome();
    }
    
    // É ESSA CHAMADA QUE FAZ TUDO FUNCIONAR
    public String getNomeDaCidade() {
        return getNomeCidade().getNome();
    }
    
    // É ESSA CHAMADA QUE FAZ TUDO FUNCIONAR
    public String getNomeDoTipoLog() {
        return getNomeTipoLog().getDescricao();
    }

    public CepCidades getNomeCidade() {
        return nomeCidade;
    }

    public void setNomeCidade(CepCidades nomeCidade) {
        this.nomeCidade = nomeCidade;
    }

    public CepTipoLog getNomeTipoLog() {
        return nomeTipoLog;
    }

    public void setNomeTipoLog(CepTipoLog nomeTipoLog) {
        this.nomeTipoLog = nomeTipoLog;
    }

    
    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public CepBairros getNome_do_bairro() {
        return nome_do_bairro;
    }

    public void setNome_do_bairro(CepBairros nome_do_bairro) {
        this.nome_do_bairro = nome_do_bairro;
    }
    

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }


    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
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
        if (!(object instanceof CepLogradouros)) {
            return false;
        }
        CepLogradouros other = (CepLogradouros) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.CepLogradouros[ codigo=" + codigo + " ]";
    }

}
