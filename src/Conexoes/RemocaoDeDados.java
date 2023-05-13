package Conexoes;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public class RemocaoDeDados {
    public static void remover(int id) {
        try {
            Connection conexao = GeradorDeConexoes.gerarConexao();

            PreparedStatement statement = conexao.prepareStatement("""
                DELETE FROM
                ATIVIDADE
                WHERE ID = ?
            """);

            statement.setInt(1, id);

            statement.execute();

            atualizarIdsBanco(id);
        }

        catch (SQLException erro) {
            throw new RuntimeException(erro);
        }
    }

    public static void atualizarIdsBanco(int id) {
        try {
            Connection conexao = GeradorDeConexoes.gerarConexao();

            PreparedStatement statement = conexao.prepareStatement("""
                UPDATE ATIVIDADE
                SET ID = ID - 1
                WHERE ID > ?
            """);

            statement.setInt(1, id);

            statement.execute();
        }

        catch (SQLException erro) {
            throw new RuntimeException(erro);
        }
    }
}
