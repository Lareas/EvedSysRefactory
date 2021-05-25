package entities;


public class AlunosNestaDisciplina {

    private String nomeAluno;
    private float media;
    private Short faltas;
    private String situacao;
    private String frequencia;

    public AlunosNestaDisciplina() {
    }

    public AlunosNestaDisciplina(String nomeAluno, float media, Short faltas, String situacao, String frequencia) {
        this.nomeAluno = nomeAluno;
        this.media = media;
        this.faltas = faltas;
        this.situacao = situacao;
        this.frequencia = frequencia;
    }
    
    public String getNomeAluno() {
        return nomeAluno;
    }

    public void setNomeAluno(String nomeAluno) {
        this.nomeAluno = nomeAluno;
    }

    public float getMedia() {
        return media;
    }

    public void setMedia(float media) {
        this.media = media;
    }

    public Short getFaltas() {
        return faltas;
    }

    public void setFaltas(Short faltas) {
        this.faltas = faltas;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public String getFrequencia() {
        return frequencia;
    }

    public void setFrequencia(String frequencia) {
        this.frequencia = frequencia;
    }
      
    
    
    
}
