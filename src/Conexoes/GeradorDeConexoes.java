package Conexoes;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;

public class GeradorDeConexoes {
    public static Connection gerarConexao() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            Connection conexao = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/DB_ATIVIDADE", "root", "root");

            return conexao;
        }
        
        catch (SQLException erro) {
            throw new RuntimeException(erro);
        }

        catch (ClassNotFoundException erro) {
            throw new RuntimeException(erro);
        }
    }
}
