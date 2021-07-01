package controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import model.MySQLConnection;
import model.Pais;

import java.sql.ResultSet;
import java.sql.SQLException;
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

    private ArrayList<Pais> listaPaises = new ArrayList<Pais>();

    public void initialize(){
        try{
            connection = new MySQLConnection();
            ResultSet result = connection.getPaises();
            while(result.next()){
                String codigo = result.getString(1);
                String nome = result.getString(2);
                Pais p = new Pais(codigo,nome);
                this.listaPaises.add(p);
            }
            for(Pais p : listaPaises){
                this.cbPais.getItems().add(p.getName());
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @FXML
    void fechar(ActionEvent event) {

    }

    @FXML
    void inserir(ActionEvent event) {

    }

}
