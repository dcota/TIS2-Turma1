package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import model.Pessoa;

public class Controller2 {

    @FXML
    private TextField tfPrimNome;

    @FXML
    private TextField tfUltNome;

    @FXML
    private ComboBox<String> cbGenero;

    @FXML
    private Button btnAlterar;

    @FXML
    private Button btnCancelar;

    private Pessoa p;

    public void initialize(){
        this.cbGenero.getItems().addAll("Masculino", "Feminino", "Outro");
    }

    public void getDados(Pessoa p){
        this.p=p;
    }

    @FXML
    void alterar(ActionEvent event) {

    }

    @FXML
    void cancelar(ActionEvent event) {

    }

}

