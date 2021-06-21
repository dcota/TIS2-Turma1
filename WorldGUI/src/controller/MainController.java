package controller;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.Cidade;

public class MainController {

    @FXML
    private TableView<Cidade> tblCidades;

    @FXML
    private TableColumn<Cidade, String> colCidade;

    @FXML
    private TableColumn<Cidade, String> colPais;

    public void initialize(){

    }

}
