package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;


public class Controller {

    @FXML TextField tfEsquerda;

    @FXML TextArea taDireita;

    @FXML Button btnTeste;

    @FXML ComboBox<String> cbLinguagens;

    @FXML Label lblLinguagemPreferida;

    @FXML RadioButton rb1;

    @FXML RadioButton rb2;

    @FXML RadioButton rb3;

    @FXML RadioButton rb4;

    @FXML Label lbLinguagemEscolhida;

    @FXML Button btnLinguagemRB;

    @FXML CheckBox chkbSim;

    @FXML CheckBox chkbNao;

    @FXML Label aviso;

    private ToggleGroup tg;

    private String textoEsquerda="";

    public void mudaTexto(ActionEvent event){
        if(this.chkbSim.isSelected() && !this.chkbNao.isSelected() && !this.tfEsquerda.getText().equals("")){
            textoEsquerda += this.tfEsquerda.getText() + "\n";
            this.taDireita.setText(textoEsquerda);
            this.tfEsquerda.setText("");
            Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
            alerta.setHeaderText(null);
            alerta.setTitle("SUCESSO");
            alerta.setContentText("Linha adicionada!");
            alerta.showAndWait();

        } else {
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setHeaderText(null);
            alerta.setTitle("AVISO");
            alerta.setContentText("Linha rejeitada...");
            alerta.showAndWait();
        }

    }

    public void selecionaLinguagem (ActionEvent event){
        String linguagem = this.cbLinguagens.getValue();
        this.lblLinguagemPreferida.setText(linguagem);
    }

    public void selecionaLinguagemRB (ActionEvent event) {
        String texto;
        if (this.rb1.isSelected()) {
            texto = this.rb1.getText();
            this.lbLinguagemEscolhida.setText(texto);
        } else if (this.rb2.isSelected()) {
            texto = this.rb2.getText();
            this.lbLinguagemEscolhida.setText(texto);
        } else if (this.rb3.isSelected()) {
            texto = this.rb3.getText();
            this.lbLinguagemEscolhida.setText(texto);
        } else if (this.rb4.isSelected()) {
            texto = this.rb4.getText();
            this.lbLinguagemEscolhida.setText(texto);
        }
    }
    public void initialize(){

        //adiciona opções à combobox
        this.cbLinguagens.getItems().add("Java");
        this.cbLinguagens.getItems().add("C");
        this.cbLinguagens.getItems().add("C++");
        this.cbLinguagens.getItems().add("Python");
        this.cbLinguagens.getItems().add("C#");

        //criar um grupo com os radiobuttons
        tg = new ToggleGroup();
        this.rb1.setToggleGroup(tg);
        this.rb1.setSelected(true);
        this.rb2.setToggleGroup(tg);
        this.rb3.setToggleGroup(tg);
        this.rb4.setToggleGroup(tg);

    }

}
