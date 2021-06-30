package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import model.MySQLConnection;
import model.Pais;

import java.util.ArrayList;

public class InsertCityController {

    @FXML
    private TextField tfCidade;

    @FXML
    private TextField tfDistrito;

    @FXML
    private TextField tfPopulacao;

    @FXML
    private Button btnFechar;

    @FXML
    private ComboBox<String> cbPais;

    @FXML
    private Button btnInserir;

    private MySQLConnection connection;

    private ArrayList<Pais> listaPaises;

    public void initialize(){
        connection = new MySQLConnection();

    }

    @FXML
    void fechar(ActionEvent event) {

    }

    @FXML
    void inserir(ActionEvent event) {

    }

}
