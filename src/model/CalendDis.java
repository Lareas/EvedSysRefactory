package entities;

import java.util.Date;

public class CalendDis {

    private int codescalendas;
    private int aula;
    private Date data;
    private String obs;

    public CalendDis() {
    }

    public CalendDis(int codescalendas, int aula, Date data, String obs) {
        this.codescalendas = codescalendas;
        this.aula = aula;
        this.data = data;
        this.obs = obs;
    }

    public int getCodescalendas() {
        return codescalendas;
    }

    public void setCodescalendas(int codescalendas) {
        this.codescalendas = codescalendas;
    }

    public int getAula() {
        return aula;
    }

    public void setAula(int aula) {
        this.aula = aula;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

}
