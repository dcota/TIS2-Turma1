package main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class MysqlConnection {

    private Properties p;
    private Connection connection;

    //construtor
    public MysqlConnection () {
        setConnection();
    }

    public void setConnection(){
        p = new Properties();
        try {
            InputStream input = new FileInputStream("dadosBD.properties");
            p.load(input);
            connection = DriverManager.getConnection(p.getProperty("url"), p.getProperty("username"), p.getProperty("password"));
            //System.out.println("Ligação efetuada com sucesso");

        } catch (FileNotFoundException e) {
            System.out.println("Ficheiro não encontrado...");

        } catch (IOException e) {
            System.out.println("Erro IO");

        } catch (SQLException throwables) {
            System.out.println("Erro de ligação à base de dados...");
        }
    }

    //método para executar SELECT
    public ResultSet querySELECT(String consulta){
        try {
            Statement statement = connection.createStatement();
            ResultSet resultado = statement.executeQuery(consulta);
            return resultado;
        } catch (SQLException throwables) {
            System.out.println("Consulta inválida...");
            return null;
        }
    }

    public void insertCity(String cidade, String codigoPais, String distrito, int popul) {
        String sql = "INSERT INTO city (Name, CountryCode, District, Population) VALUES (?,?,?,?)";
        try{
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,cidade);
            statement.setString(2,codigoPais);
            statement.setString(3,distrito);
            statement.setInt(4,popul);
            int linhas = statement.executeUpdate();
            System.out.println(linhas);
            if(linhas == 1){
                System.out.println("Linha adicionada com sucesso!");
            }
        } catch (SQLException e){
            System.out.println("Não foi possível adicionar a linha!");
        }
    }
     public void deleteFromCity(int ID){
        String sql = "DELETE FROM city WHERE ID = " + ID;
        try {
            Statement statement = connection.createStatement();
            int linhas = statement.executeUpdate(sql);
            if(linhas == 1){
                System.out.println("Linha eliminada com sucesso!");
            } else if (linhas ==0) {
                System.out.println("ID não encontrado");
            }
        } catch (SQLException e){
            System.out.println("Não foi possível eliminar a linha!");
        }
     }
}
