package AtividadesDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Atividades.*;

public class AtividadeFisicaDao extends AtividadeDao {
    @Override
    public void inserir(Atividade atividade) throws SQLException {
        super.inserir(atividade);

        Connection conexao = gerarConexao();

        PreparedStatement stmt = conexao.prepareStatement("""
            INSERT INTO ATIVIDADE_FISICA
            VALUES (?, ?)
        """);

        stmt.setInt(1, atividade.getId());
        stmt.setInt(2, ((AtividadeFisica) atividade).getIntensidade());

        stmt.execute();

        stmt.close();
        conexao.close();
    }
}
