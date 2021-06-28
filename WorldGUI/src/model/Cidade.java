package model;

import java.util.Objects;

public class Cidade {
    //atributos
    private String cidade;
    private String pais;
    private int ID;

    public Cidade(int ID, String cidade, String pais) {
        this.ID = ID;
        this.cidade = cidade;
        this.pais = pais;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cidade cidade1 = (Cidade) o;
        return ID == cidade1.ID && Objects.equals(cidade, cidade1.cidade) && Objects.equals(pais, cidade1.pais);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cidade, pais, ID);
    }
}
