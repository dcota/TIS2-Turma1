package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Cidade;
import model.MySQLConnection;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MainController {

    @FXML
    private TableView<Cidade> tblCidades;

    @FXML
    private TableColumn<Cidade, String> colCidade;

    @FXML
    private TableColumn<Cidade, String> colPais;

    private ObservableList<Cidade> listaCidades;

    private MySQLConnection connection;

    public void initialize(){
        //preparar a tabela para receber os objetos da classe Pessoa
        listaCidades = FXCollections.observableArrayList();
        this.tblCidades.setItems(listaCidades);
        this.colCidade.setCellValueFactory(new PropertyValueFactory<Cidade,String>("cidade"));
        this.colPais.setCellValueFactory(new PropertyValueFactory<Cidade,String>("pais"));

        connection = new MySQLConnection();

        fillTable();

    }

    public void fillTable() {
        //obter o resultado do query para preencher a tabela
        ResultSet result = connection.getCidades();
        //limpar a lista de cidades
        listaCidades.clear();
        try{
            while(result.next()){
                String cidade = result.getString(1);
                String pais = result.getString(2);
                Cidade c = new Cidade(cidade,pais);
                listaCidades.add(c);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        this.tblCidades.setItems(listaCidades);
        this.tblCidades.refresh();
    }

}
