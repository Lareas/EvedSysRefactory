/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author ticoa
 */
public class DisProgCateg {

    private int codgradedisprg;
    private int cadastroDisciplinaId;
    private int codprograma;
    private int categDis;
    private final SimpleStringProperty categ = new SimpleStringProperty();
    private final SimpleStringProperty nompro = new SimpleStringProperty();

    public DisProgCateg() {
    }

    public DisProgCateg(String nompro, String categ) {
        setNompro(nompro);
        setCateg(categ);
    }

    public DisProgCateg(int codgradedisprg, int cadastroDisciplinaId, int codprograma, int categDis, String categ, String nompro) {
        this.codgradedisprg = codgradedisprg;
        this.cadastroDisciplinaId = cadastroDisciplinaId;
        this.codprograma = codprograma;
        this.categDis = categDis;
        setCateg(categ);
        setNompro(nompro);
    }

    public final StringProperty categProperty() {
        return this.categ;
    }

    public final String getCateg() {
        return this.categProperty().get();
    }

    public final void setCateg(final String categ) {
        this.categProperty().set(categ);
    }

    public final StringProperty nomproProperty() {
        return this.nompro;
    }

    public final String getNmpro() {
        return this.nomproProperty().get();
    }

    public final void setNompro(final String nompro) {
        this.nomproProperty().set(nompro);
    }

    public int getCodgradedisprg() {
        return codgradedisprg;
    }

    public void setCodgradedisprg(int codgradedisprg) {
        this.codgradedisprg = codgradedisprg;
    }

    public int getCadastroDisciplinaId() {
        return cadastroDisciplinaId;
    }

    public void setCadastroDisciplinaId(int cadastroDisciplinaId) {
        this.cadastroDisciplinaId = cadastroDisciplinaId;
    }

    public int getCodprograma() {
        return codprograma;
    }

    public void setCodprograma(int codprograma) {
        this.codprograma = codprograma;
    }

    public int getCategDis() {
        return categDis;
    }

    public void setCategDis(int categDis) {
        this.categDis = categDis;
    }

}
