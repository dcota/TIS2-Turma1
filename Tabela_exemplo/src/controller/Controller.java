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
    @FXML
    Button btnEliminar;
    @FXML
    Button btnAlterar;

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
        if ( this.tfPrimNome.getText().isEmpty() == false
                && this.tfUltNome.getText().isEmpty() == false
                && this.cbGenero.getValue() != null){
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

                //lançar alerta de sucesso
                alertaSUCESSO("Pessoa adicionada com sucesso!");

                //limpar o formulário
                limparForm();
            }
            else {
                alertaERRO("Pessoa existente...");
                limparForm();
            }
        }
        else {
            alertaERRO("Todos os campos devem estar preenchidos!");
        }


    }

    @FXML
    public void eliminar(ActionEvent event){
        //Obter a linha selecionada
        Pessoa p = this.tblPessoas.getSelectionModel().getSelectedItem();
        if(p==null){
            alertaERRO("Deve selecionar uma linha para eliminar!");
        }
        else{
            this.listaPessoas.remove(p);
            this.tblPessoas.refresh();
            alertaSUCESSO("Pessoa removida com sucesso");
        }
    }

    @FXML
    public void alterar(ActionEvent event){
        try {
            //Obter a linha selecionada
            Pessoa p = this.tblPessoas.getSelectionModel().getSelectedItem();
            if (p == null) {
                alertaERRO("Deve selecionar uma linha para alterar!");
            } else {
                //Passo1 - criar um loader para carregar a segunda vista
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/vista_alterar.fxml"));
                Parent root = loader.load();

                //Passo2
                //poder aceder aos métodos do segundo controller
                Controller2 controller2 = loader.getController();
                //chamar o método getDados() e passar o objeto
                controller2.getDados(p);

                //Passo3 - mostrar a segunda vista
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.initModality(Modality.WINDOW_MODAL);
                stage.setScene(scene);
                stage.showAndWait();

                //vai buscar o objeto pAlterada ao controller2
                Pessoa pAlterada = controller2.getpAlterada();

                //alterar os valores do objeto inicial para os valores do objeto pAlterada
                p.setPrimNome(pAlterada.getPrimNome());
                p.setUltNome(pAlterada.getUltNome());
                p.setGenero(pAlterada.getGenero());

                //atualiza a tabela
                this.tblPessoas.refresh();

                alertaSUCESSO("Registo alterado com sucesso!");

            }
        }
        catch (Exception e) {

        }
    }

    public void limparForm(){
        this.tfPrimNome.setText("");
        this.tfUltNome.setText("");
        this.cbGenero.setValue(null);
    }

    public void alertaERRO (String texto){
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setHeaderText(null);
        alerta.setTitle("ERRO");
        alerta.setContentText(texto);
        alerta.showAndWait();
    }

    public void alertaSUCESSO (String texto){
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setHeaderText(null);
        alerta.setTitle("OK");
        alerta.setContentText(texto);
        alerta.showAndWait();
    }


}


