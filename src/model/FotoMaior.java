package entities;

import javafx.scene.image.Image;

public class FotoMaior {

    String nomeAluno;
    private Image fotoMaior;

    public FotoMaior() {
    }

    public FotoMaior(String nomeAluno, Image fotoMaior) {
        this.nomeAluno = nomeAluno;
        this.fotoMaior = fotoMaior;
    }

    public String getNomeAluno() {
        return nomeAluno;
    }

    public void setNomeAluno(String nomeAluno) {
        this.nomeAluno = nomeAluno;
    }

    public Image getFotoMaior() {
        return fotoMaior;
    }

    public void setFotoMaior(Image fotoMaior) {
        this.fotoMaior = fotoMaior;
    }

    
    
    
}
