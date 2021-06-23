package model;

import java.util.Objects;

public class Cidade {
    //atributos
    private String cidade;
    private String pais;

    public Cidade(String cidade, String pais) {
        this.cidade = cidade;
        this.pais = pais;
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
        return Objects.equals(cidade, cidade1.cidade) && Objects.equals(pais, cidade1.pais);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cidade, pais);
    }
}
