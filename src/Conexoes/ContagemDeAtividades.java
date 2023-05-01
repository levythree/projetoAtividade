package Conexoes;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ContagemDeAtividades {
    public static int getQuantidadeDeAtividades() {
        Connection conexao = GeradorDeConexoes.gerarConexao();
        
        try {
            PreparedStatement statement = conexao.prepareStatement("""
                SELECT COUNT(*)
                FROM ATIVIDADE
            """);

            ResultSet resultSet = statement.executeQuery();

            resultSet.next();

            return resultSet.getInt("COUNT(*)");
        }
        
        catch (SQLException erro) {
            throw new RuntimeException(erro);
        }
    }
}
