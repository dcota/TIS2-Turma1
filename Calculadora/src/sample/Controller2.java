package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Controller2 {
    @FXML Label lbResultado;
    @FXML Button btnFechar;

    private Dados dados2controller;

    public void fechar(ActionEvent event){
        Stage stage = (Stage) this.btnFechar.getScene().getWindow();
        stage.close();
    }

    public void getDados(Dados dados){
        this.dados2controller = dados;
        String lbTexto = this.lbResultado.getText();
        if(dados2controller.getOpera().equals("soma")){
            double soma = this.dados2controller.soma(this.dados2controller.n1, this.dados2controller.n2);
            this.lbResultado.setText(lbTexto + String.valueOf(soma));
        }


    }
}
