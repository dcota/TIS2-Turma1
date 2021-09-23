package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Resposta;

import java.io.IOException;

public class MainController {

    @FXML
    private Button btnValidar;

    @FXML
    private Button btnSair;

    @FXML
    private TextField tfNum;

    private String resposta;

    @FXML
    void validar(ActionEvent event) {
        try {
            //começar por verificar se a caixa de texto está vazia
            if (this.tfNum.getText().isEmpty()) {
                alertaAtencao("Tem de indicar um número!");
            }
            else {
                try {
                    //tenta converter o valor introduzido para inteiro
                    //se contém caracteres inválidos lança exceção apanhada na linha 77
                    int num = Integer.parseInt(this.tfNum.getText());
                    //se o valor da caixa tiver mais do que 6 caracteres informa
                    if (this.tfNum.getText().length() > 6) {
                        alertaAtencao("O número tem mais do que 6 digitos");
                    }
                    //se não verifica se é primo e preenche a variável resposta
                    else {
                        boolean primo = true;
                        Resposta res = new Resposta();
                        for (int i = 2; i < num; i++) {
                            if (num % i == 0) {
                                primo = false;
                                break;
                            }
                        }
                        if (primo){
                            res.setResposta("O número " + num + " é primo.");
                        }
                        else{
                            res.setResposta("O número " + num + " não é primo.");
                        }

                        //prepara e envia a resposta para a segunda vista
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/second.fxml"));
                        Parent root = loader.load();
                        SecondController controller = loader.getController();
                        //passar a resposta
                        controller.getResposta(res);
                        //lança a segunda vista depois de passar a resposta
                        Scene scene = new Scene(root);
                        Stage stage = new Stage();
                        stage.initModality(Modality.WINDOW_MODAL);
                        stage.setScene(scene);
                        stage.showAndWait();
                        //depois de voltar da segunda vista limpa a caixa de entrada de números
                        this.tfNum.setText("");
                    }
                } catch (NumberFormatException e) {
                    alertaAtencao("Valor com caracteres inválidos!");
                }
            }
        } catch (IOException e) {
                alertaAtencao("Ocorreu um erro, tente de novo.");
        }
    }

    //método para lançar alertas com mensagem variável
    @FXML
    void alertaAtencao(String texto){
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setHeaderText(null);
        alerta.setTitle("ATENÇÃO");
        alerta.setContentText(texto);
        alerta.showAndWait();
        this.tfNum.setText("");
    }

    //método para fechar a vista
    @FXML
    void sair(ActionEvent event) {
        Stage stage = (Stage) this.btnSair.getScene().getWindow();
        stage.close();
    }

}

