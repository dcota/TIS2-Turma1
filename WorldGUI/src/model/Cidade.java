package model;

import java.util.Objects;

public class Cidade {
    //atributos
    private String cidade;
    private String pais;
    private int ID;
    private String distrito;
    private int pop;
    private String codPais;

    public Cidade(int ID, String cidade, String pais) {
        this.ID = ID;
        this.cidade = cidade;
        this.pais = pais;
    }

    public Cidade(String cidade, String codPais, String distrito, int pop) {
        this.cidade = cidade;
        this.codPais = codPais;
        this.distrito = distrito;
        this.pop = pop;
    }

    public String getCodPais() {
        return codPais;
    }

    public void setCodPais(String codPais) {
        this.codPais = codPais;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public int getPop() {
        return pop;
    }

    public void setPop(int pop) {
        this.pop = pop;
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
