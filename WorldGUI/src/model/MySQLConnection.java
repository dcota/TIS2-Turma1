package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class MySQLConnection {
    private Properties p;
    private Connection connection;

    public MySQLConnection(){
        p = new Properties();
        try {
            InputStream input = new FileInputStream("dbconnect.properties");
            p.load(input);
            connection = DriverManager.getConnection(p.getProperty("url"), p.getProperty("username"), p.getProperty("password"));
            System.out.println("Ligação efetuada com sucesso");

        } catch (FileNotFoundException e) {
            System.out.println("Ficheiro não encontrado...");

        } catch (IOException e) {
            System.out.println("Erro IO");

        } catch (SQLException e) {
            System.out.println("Erro de ligação à base de dados...");
        }
    }

    public ResultSet getCidades(){
        ResultSet result = null;
        String sql="SELECT city.Name, country.Name\n" +
                "FROM city, country\n" +
                "WHERE city.CountryCode = country.Code\n" +
                "ORDER BY country.Name ASC";
        try{
            Statement stm = connection.createStatement();
            result = stm.executeQuery(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

}
