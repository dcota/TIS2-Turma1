package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.MySQLConnection;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CityController {

    @FXML
    private TextField tfCidade;

    @FXML
    private TextField tfPais;

    @FXML
    private TextField tfDistrito;

    @FXML
    private TextField tfPopulacao;

    @FXML
    private Button btnFechar;

    private MySQLConnection connection;

    @FXML
    void fechar(ActionEvent event) {
        Stage stage = (Stage) this.btnFechar.getScene().getWindow();
        stage.close();
    }

    public void getID(int ID) {
        connection = new MySQLConnection();
        ResultSet result = connection.getDetalhe(ID);
        try {
            while (result.next()) {
                String cidade = result.getString(1);
                String pais = result.getString(2);
                String distrito = result.getString(3);
                int pop = result.getInt(4);
                this.tfCidade.setText(cidade);
                this.tfPais.setText(pais);
                this.tfDistrito.setText(distrito);
                this.tfPopulacao.setText(String.valueOf(pop));

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }
}


