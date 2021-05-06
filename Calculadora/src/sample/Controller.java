package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;


public class Controller {
    
    @FXML TextField tfNum1;
    @FXML TextField tfNum2;
    @FXML RadioButton rbSoma;
    @FXML RadioButton rbSub;
    @FXML RadioButton rbMult;
    @FXML RadioButton rbDiv;
    @FXML TextField tfRes;
    @FXML Button bntCalcular;

    private ToggleGroup opera;
    private Object ArithmeticException;


    public void initialize() {
        //criar o grupo de radiobuttons
        opera = new ToggleGroup();
        this.rbDiv.setToggleGroup(opera);
        this.rbMult.setToggleGroup(opera);
        this.rbSoma.setToggleGroup(opera);
        this.rbSub.setToggleGroup(opera);
    }

    @FXML
    public void calcular (ActionEvent event) {
        try {
            //recolher os valores introduzidos
            String s1 = this.tfNum1.getText();
            String s2 = this.tfNum2.getText();
            //converter os valores para double
            double d1 = Double.parseDouble(s1);
            double d2 = Double.parseDouble(s2);
            String op = null;
            if(this.rbSoma.isSelected()){
                op="soma";
            }
            else if(this.rbSub.isSelected()){
                op= "sub";
            }
            else if(this.rbMult.isSelected()){
                op = "mult";
            }
            else if(this.rbDiv.isSelected()){
                if(d2 != 0) {
                    op = "div";
                }
                else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setTitle("Erro");
                    alert.setContentText("Denominador não pode ser zero...");
                    alert.showAndWait();
                }
            }
            else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Erro");
                alert.setContentText("Nenhuma operação selecionada...");
                alert.showAndWait();
            }

            //criar objeto da classe Dados com os dados necessários
            Dados dados = new Dados(d1,d2,op);

            //criar um loader para carregar a segunda vista
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/resultado.fxml"));
            Parent root = loader.load();

            //poder aceder aos métodos do segundo controller
            Controller2 controller2 = loader.getController();
            controller2.getDados(dados);

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.WINDOW_MODAL);
            stage.setScene(scene);
            stage.showAndWait();

            limpar();

        }
        catch (NumberFormatException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Erro");
            alert.setContentText("Formato dos números errado...");
            alert.showAndWait();
        }
        catch (IOException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Erro");
            alert.setContentText("Ocorreu um problema com um ficheiro...");
            alert.showAndWait();
        }


    }

    public void sucesso(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("SUCESSO");
        alert.setContentText("Operação realizada!");
        alert.showAndWait();
    }

    public void limpar(){
        this.tfNum1.clear();
        this.tfNum2.clear();
        this.tfRes.clear();
    }

}
