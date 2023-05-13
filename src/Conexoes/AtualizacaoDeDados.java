package Conexoes;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.Date;

public class AtualizacaoDeDados {
    public static void atualizar(int id, String tabela, String coluna, String s, int i, Date d) {
        try {
            Connection conexao = GeradorDeConexoes.gerarConexao();

            PreparedStatement statement = conexao.prepareStatement("""
                UPDATE ?
                SET ? = ?
                WHERE ID = ?
            """);

            statement.setString(1, tabela);
            statement.setString(2, coluna);
            
            if (s != null) {
                statement.setString(3, s);
            }

            else if (i != 0) {
                statement.setInt(3, i);
            }

            else if (d != null) {
                statement.setDate(3, new java.sql.Date(d.getTime()));
            }

            statement.setInt(4, id);

            statement.execute();
        }

        catch (SQLException erro) {
            throw new RuntimeException(erro);
        }
    }
}
