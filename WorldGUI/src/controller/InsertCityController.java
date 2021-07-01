package controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Cidade;
import model.MySQLConnection;
import model.Pais;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class InsertCityController {

    @FXML
    private TextField tfCidade;

    @FXML
    private TextField tfDistrito;

    @FXML
    private TextField tfPopulacao;

    @FXML
    private Button btnFechar;

    @FXML
    private ComboBox<String> cbPais;

    @FXML
    private Button btnInserir;

    private MySQLConnection connection;

    private ArrayList<Pais> listaPaises = new ArrayList<Pais>();

    public void initialize(){
        try{
            connection = new MySQLConnection();
            ResultSet result = connection.getPaises();
            while(result.next()){
                String codigo = result.getString(1);
                String nome = result.getString(2);
                Pais p = new Pais(codigo,nome);
                this.listaPaises.add(p);
            }
            for(Pais p : listaPaises){
                this.cbPais.getItems().add(p.getName());
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @FXML
    void fechar(ActionEvent event) {
        Stage stage = (Stage) this.btnFechar.getScene().getWindow();
        stage.close();
    }

    @FXML
    void inserir(ActionEvent event) {
        System.out.println("Entrou no inserir");
        //se todos os campos estiverem preenchidos entra...
        if(this.tfCidade.getText().isEmpty()==false &&
           this.tfDistrito.getText().isEmpty()==false &&
           this.cbPais.getValue().isEmpty()==false &&
           this.tfPopulacao.getText().isEmpty()==false) {
                //recolhe os valores dos campos
               String cidade = this.tfCidade.getText();
               String distrito = this.tfDistrito.getText();
               String pais = this.cbPais.getValue();
               System.out.print(pais);
               String codigoPais=null;
               for(Pais p: listaPaises){
                   if(p.getName().equals(pais)){
                       codigoPais=p.getCode();
                   }
               }
               int pop = Integer.parseInt(this.tfPopulacao.getText());
               //cria objeto com os valores
               Cidade c = new Cidade(cidade,codigoPais,distrito,pop);
               //passa o objeto ao método que vai inserir na bd
                if(connection.inserirCidade(c) == true){
                    alerta(Alert.AlertType.INFORMATION,"Cidade inserida com sucesso!","SUCESSO");
                }
                else{
                    alerta(Alert.AlertType.WARNING,"Não foi possível realizar a operação...!", "ERRO");
                }
        } else {
            alerta(Alert.AlertType.WARNING,"Todos os campos devem estar preenchidos!", "ERRO");
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
