package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Cidade;
import model.MySQLConnection;
import model.Pais;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Locale;

public class MainController {

    @FXML
    private TableView<Cidade> tblCidades;

    @FXML
    private TableColumn<Cidade, String> colCidade;

    @FXML
    private TableColumn<Cidade, String> colPais;

    @FXML
    private Button btnDetalhe;

    @FXML
    private TextField tfPesquisa;

    @FXML
    private Button btnInserir;

    @FXML
    private Button btnSair;

    private ObservableList<Cidade> listaCidades;
    private ObservableList<Cidade> listaAux;

    private Cidade linhaCidade;

    private MySQLConnection connection;

    public void initialize(){
        //preparar a tabela para receber os objetos da classe Pessoa
        listaCidades = FXCollections.observableArrayList();
        listaAux = FXCollections.observableArrayList();
        this.tblCidades.setItems(listaCidades);
        this.colCidade.setCellValueFactory(new PropertyValueFactory<Cidade,String>("cidade"));
        this.colPais.setCellValueFactory(new PropertyValueFactory<Cidade,String>("pais"));

        connection = new MySQLConnection();

        fillTable();

    }

    public void pesquisar(){
        String texto = this.tfPesquisa.getText();
        if(texto.isEmpty()){
            this.tblCidades.setItems(listaCidades);
        } else {
            this.listaAux.clear();
            for(Cidade c : listaCidades){
                if(c.getPais().toLowerCase().contains(texto.toLowerCase())){
                    this.listaAux.add(c);
                }
            }
            this.tblCidades.setItems(listaAux);
        }
    }

    public void fillTable() {
        //obter o resultado do query para preencher a tabela
        ResultSet result = connection.getCidades();
        //limpar a lista de cidades
        listaCidades.clear();
        try{
            while(result.next()){
                int ID = result.getInt(1);
                String cidade = result.getString(2);
                String pais = result.getString(3);
                Cidade c = new Cidade(ID,cidade,pais);
                listaCidades.add(c);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        this.tblCidades.setItems(listaCidades);
        this.tblCidades.refresh();
    }

    @FXML
    public void detalhe(ActionEvent event){
        //selecionar o objeto Cidade da linha escolhida
        linhaCidade = this.tblCidades.getSelectionModel().getSelectedItem();
        if(linhaCidade==null){
            alerta(Alert.AlertType.ERROR, "Não selecionou uma cidade...", "ERRO");
        } else {
            //preparar o lançamento da segunda vista
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/cityView.fxml"));
                Parent root = loader.load();

                //para poder usar os métodos do segundo controller
                CityController controller = loader.getController();

                //chamar método do segundo controller e passar o ID da cidade
                controller.getID(linhaCidade.getID());

                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.initModality(Modality.WINDOW_MODAL);
                stage.setScene(scene);
                stage.showAndWait();


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void inserir(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/newCityView.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.WINDOW_MODAL);
            stage.setScene(scene);
            stage.showAndWait();
            fillTable();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @FXML
    void sair(ActionEvent event) {

    }

    @FXML
    public void alerta(Alert.AlertType tipo, String txt, String titulo){
        Alert alerta = new Alert(tipo);
        alerta.setHeaderText(null);
        alerta.setTitle(titulo);
        alerta.setContentText(txt);
        alerta.showAndWait();
    }

}
