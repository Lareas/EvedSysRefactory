package entities;

public class ChecaErros {

   private int ctw;        // contador de warnings
   private int cte;        // contador de erros (graves)
   private String warn;  // texto dos warnings

    public ChecaErros() {
        this.ctw = 0;
        this.cte = 0;
        this.warn = "";
    }

    public int getCtw() {
        return ctw;
    }

    public void setCtw(int ctw) {
        this.ctw = ctw;
    }

    public int getCte() {
        return cte;
    }

    public void setCte(int cte) {
        this.cte = cte;
    }

    public String getWarn() {
        return warn;
    }

    public void setWarn(String warn) {
        this.warn = warn;
    }

    
   
}
