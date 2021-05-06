package sample;

public class Dados {
    //atributos
    double n1;
    double n2;
    String opera;

    //contrutor

    public Dados(double n1, double n2, String opera) {
        this.n1 = n1;
        this.n2 = n2;
        this.opera = opera;
    }

    //getters e os setters

    public double getN1() {
        return n1;
    }

    public void setN1(double n1) {
        this.n1 = n1;
    }

    public double getN2() {
        return n2;
    }

    public void setN2(double n2) {
        this.n2 = n2;
    }

    public String getOpera() {
        return opera;
    }

    public void setOpera(String opera) {
        this.opera = opera;
    }

    //outros métodos
    public double soma (double n1, double n2){
        return this.n1 + this.n2;
    }

    public double sub (double n1, double n2){
        return this.n1 - this.n2;
    }

    public double mult (double d1, double d2){
        return this.n1 * this.n2;
    }

    public double div (double d1, double d2){
        return (double) this.n1 / this.n2;

    }
}
