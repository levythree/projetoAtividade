package AtividadesDAO;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import Atividades.*;

public class AtividadeDeLazerDao extends AtividadeDao {
    @Override
    public void inserir(Atividade atividade) throws SQLException {
        super.inserir(atividade);

        Connection conexao = gerarConexao();

        PreparedStatement stmt = conexao.prepareStatement("""
            INSERT INTO ATIVIDADE_DE_LAZER
            VALUES (?)
        """);

        stmt.setInt(1, atividade.getId());

        stmt.execute();

        stmt.close();
        conexao.close();
    }
}