package entities;

public class GridCurso {

    private Integer cursoId;
    private String curso;
    private String descricao;
    private String programa;
    private String titutacao;
    private String grau;

    public GridCurso(Integer cursoId, String curso, String descricao, String programa, String titutacao, String grau) {
        this.cursoId = cursoId;
        this.curso = curso;
        this.descricao = descricao;
        this.programa = programa;
        this.titutacao = titutacao;
        this.grau = grau;
    }

    public Integer getCursoId() {
        return cursoId;
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getPrograma() {
        return programa;
    }

    public void setPrograma(String programa) {
        this.programa = programa;
    }

    public String getTitutacao() {
        return titutacao;
    }

    public void setTitutacao(String titutacao) {
        this.titutacao = titutacao;
    }

    public String getGrau() {
        return grau;
    }

    public void setGrau(String grau) {
        this.grau = grau;
    }
    
    
}
