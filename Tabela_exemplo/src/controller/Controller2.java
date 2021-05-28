package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
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

    private Pessoa pAlterada;

    public void initialize(){

        this.cbGenero.getItems().addAll("Masculino", "Feminino", "Outro");
    }

    public void getDados(Pessoa p){
        this.tfPrimNome.setText(p.getPrimNome());
        this.tfUltNome.setText(p.getUltNome());
        this.cbGenero.setValue(p.getGenero());
    }

    @FXML
    void alterar(ActionEvent event) {
        if ( this.tfPrimNome.getText().isEmpty() == false
                && this.tfUltNome.getText().isEmpty() == false
                && this.cbGenero.getValue() != null) {
            //recolher os dados alterados
            String primNome = this.tfPrimNome.getText();
            String ultNome = this.tfUltNome.getText();
            String genero = this.cbGenero.getValue();
            //instanciar objeto pAlterada
            pAlterada = new Pessoa(primNome, ultNome, genero);
            //fechar a vista
            Stage stage = (Stage) this.btnAlterar.getScene().getWindow();
            stage.close();
        }
        else {
            alertaERRO("Todos os campos devem estar preenchidos...");
        }
    }

    @FXML
    void cancelar(ActionEvent event) {
        //fechar a vista
        Stage stage = (Stage) this.btnCancelar.getScene().getWindow();
        stage.close();
    }

    public void alertaERRO (String texto){
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setHeaderText(null);
        alerta.setTitle("ERRO");
        alerta.setContentText(texto);
        alerta.showAndWait();
    }

    //método para retornar o objeto pAlterada
    public Pessoa getpAlterada() {
        return this.pAlterada;
    }

}

