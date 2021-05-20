package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Pessoa;

public class Controller {

    @FXML
    TextField tfPrimNome;
    @FXML
    TextField tfUltNome;
    @FXML
    ComboBox<String> cbGenero;
    @FXML
    Button btnAdicionar;
    @FXML
    TableView <Pessoa> tblPessoas;
    @FXML
    TableColumn <Pessoa, String> colPrimNome;
    @FXML
    TableColumn <Pessoa, String> colUltNome;
    @FXML
    TableColumn <Pessoa, String> colGenero;

    private ObservableList<Pessoa> listaPessoas;

    public void initialize(){
        //preencher a combobox de géneros
        this.cbGenero.getItems().addAll("Masculino", "Feminino", "Outro");

        //preparar a tabela para receber os objetos da classe Pessoa
        listaPessoas = FXCollections.observableArrayList();
        this.tblPessoas.setItems(listaPessoas);
        this.colPrimNome.setCellValueFactory(new PropertyValueFactory<Pessoa,String>("primNome"));
        this.colUltNome.setCellValueFactory(new PropertyValueFactory<Pessoa,String>("ultNome"));
        this.colGenero.setCellValueFactory(new PropertyValueFactory<Pessoa,String>("genero"));

    }

    @FXML
    public void adicionar(ActionEvent event) {

            //recolher a informação do formulário
            String primNome = this.tfPrimNome.getText();
            String ultNome = this.tfUltNome.getText();
            String genero = this.cbGenero.getValue();

            //instanciar objeto da classe Pessoa
            Pessoa p = new Pessoa(primNome,ultNome,genero);

            if (this.listaPessoas.contains(p) == false){
                //adicvionar o novo objeto à observablelist
                this.listaPessoas.add(p);

                //enviar a observablelista para a tabela
                this.tblPessoas.setItems(listaPessoas);

                //limpar o focmulário
                limparForm();
            }
            else {
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setHeaderText(null);
                alerta.setTitle("ERRO");
                alerta.setContentText("Pessoa existente");
                alerta.showAndWait();
            }





        }

        public void limparForm(){
            this.tfPrimNome.setText("");
            this.tfUltNome.setText("");
            this.cbGenero.setValue(null);
        }


}


