package main;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {

        MysqlConnection connection = new MysqlConnection();

        //inserir uma linha em city
        //connection.insertCity("Praia da Vitória", "PRT", "Açores", 3500);

        //eliminar uma linha de city a partir do ID
        connection.deleteFromCity(4090);

        //selecionar as colunas da tabela city
        ResultSet resultado = connection.querySELECT("SELECT * FROM city");
        while(true){
            try {
                if (!resultado.next()) {
                    break;
                }
                System.out.print(resultado.getInt("ID") + " ; ");
                System.out.print(resultado.getString("Name") + " ; ");
                System.out.print(resultado.getString("CountryCode") + " ; ");
                System.out.print(resultado.getString("District")  + " ; ");
                System.out.println(resultado.getInt("Population"));
            } catch (SQLException throwables) {
                System.out.println("Consulta com erros...");
                break;
            }

        }

    }

}
