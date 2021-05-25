/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author ticoa
 */
public class GeraEsMatriSem {
    private Integer dcp;
    private Integer dcg;
    private Integer ano;
    private Integer sem;
    private Integer cre;
    private Integer dis;

    public GeraEsMatriSem() {
    }

    public GeraEsMatriSem(Integer dcp, Integer dcg, Integer ano, Integer sem, Integer cre, Integer dis) {
        this.dcp = dcp;
        this.dcg = dcg;
        this.ano = ano;
        this.sem = sem;
        this.cre = cre;
        this.dis = dis;
    }

    public Integer getDcp() {
        return dcp;
    }

    public void setDcp(Integer dcp) {
        this.dcp = dcp;
    }

    public Integer getDcg() {
        return dcg;
    }

    public void setDcg(Integer dcg) {
        this.dcg = dcg;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public Integer getSem() {
        return sem;
    }

    public void setSem(Integer sem) {
        this.sem = sem;
    }

    public Integer getCre() {
        return cre;
    }

    public void setCre(Integer cre) {
        this.cre = cre;
    }

    public Integer getDis() {
        return dis;
    }

    public void setDis(Integer dis) {
        this.dis = dis;
    }

    
    
}
