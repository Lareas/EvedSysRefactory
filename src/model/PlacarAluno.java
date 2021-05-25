package entities;

public class PlacarAluno {

    private int codALuno;
    private int matricula;
    private String nome;
    private String programa;
    private String disciplina;
    private String tipo;

    public PlacarAluno(int codALuno, int matricula, String nome, String programa, String disciplina, String tipo) {
        this.codALuno = codALuno;
        this.matricula = matricula;
        this.nome = nome;
        this.programa = programa;
        this.disciplina = disciplina;
        this.tipo = tipo;
    }

    public PlacarAluno(int codALuno, int matricula, String nome) {
        this.codALuno = codALuno;
        this.matricula = matricula;
        this.nome = nome;
    }
    
    public int getCodALuno() {
        return codALuno;
    }

    public void setCodALuno(int codALuno) {
        this.codALuno = codALuno;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPrograma() {
        return programa;
    }

    public void setPrograma(String programa) {
        this.programa = programa;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

}
