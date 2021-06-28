package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Cidade;
import model.MySQLConnection;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MainController {

    @FXML
    private TableView<Cidade> tblCidades;

    @FXML
    private TableColumn<Cidade, String> colCidade;

    @FXML
    private TableColumn<Cidade, String> colPais;

    @FXML
    private Button btnDetalhe;

    private ObservableList<Cidade> listaCidades;

    private Cidade linhaCidade;

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
    public void alerta(Alert.AlertType tipo, String txt, String titulo){
        Alert alerta = new Alert(tipo);
        alerta.setHeaderText(null);
        alerta.setTitle(titulo);
        alerta.setContentText(txt);
        alerta.showAndWait();
    }

}
