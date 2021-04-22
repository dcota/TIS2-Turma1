package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;


public class Controller {

    @FXML TextField tfEsquerda;

    @FXML TextArea taDireita;

    @FXML Button btnTeste;

    @FXML ComboBox<String> cbLinguagens;

    @FXML Label lblLinguagemPreferida;

    String textoEsquerda="";

    public void mudaTexto(ActionEvent event){
        textoEsquerda += this.tfEsquerda.getText() + "\n";
        this.taDireita.setText(textoEsquerda);
        this.tfEsquerda.setText("");
    }

    public void selecionaLinguagem (ActionEvent event){
        String linguagem = this.cbLinguagens.getValue();
        this.lblLinguagemPreferida.setText(linguagem);
    }

    public void initialize(){
        this.cbLinguagens.getItems().add("Java");
        this.cbLinguagens.getItems().add("C");
        this.cbLinguagens.getItems().add("C++");
        this.cbLinguagens.getItems().add("Python");
        this.cbLinguagens.getItems().add("C#");
    }

}
