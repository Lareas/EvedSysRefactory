package entities;

public class PlacarInfo {

   private Integer codGrade;
   private String semestre;
   private String titulo;
   private String tipo;
   private String corFundo;
   private String corFonte;

    public PlacarInfo(Integer codGrade, String semestre, String titulo, String tipo, String corFundo, String corFonte) {
        this.codGrade = codGrade;
        this.semestre = semestre;
        this.titulo = titulo;
        this.tipo = tipo;
        this.corFundo = corFundo;
        this.corFonte = corFonte;
    }

    public Integer getCodGrade() {
        return codGrade;
    }

    public void setCodGrade(Integer codGrade) {
        this.codGrade = codGrade;
    }

    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCorFundo() {
        return corFundo;
    }

    public void setCorFundo(String corFundo) {
        this.corFundo = corFundo;
    }

    public String getCorFonte() {
        return corFonte;
    }

    public void setCorFonte(String corFonte) {
        this.corFonte = corFonte;
    }

}
