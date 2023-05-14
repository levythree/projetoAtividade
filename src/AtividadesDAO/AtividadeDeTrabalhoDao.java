package AtividadesDAO;

import Atividades.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AtividadeDeTrabalhoDao extends AtividadeDao {
    @Override
    public void inserir(Atividade atividade) throws SQLException {
        super.inserir(atividade);

        Connection conexao = gerarConexao();

        PreparedStatement stmt = conexao.prepareStatement("""
            INSERT INTO ATIVIDADE_DE_TRABALHO
            VALUES (?, ?)
        """);

        stmt.setInt(1, atividade.getId());
        stmt.setInt(2, ((AtividadeDeTrabalho) atividade).getDificuldade());

        stmt.execute();

        stmt.close();
        conexao.close();
    }

    public void listar() throws SQLException {
        super.listar();

        Connection conexao = gerarConexao();

        PreparedStatement stmt = conexao.prepareStatement("""

        """);

        super.listarGastoDeEnergiaEBemEstar();
    }
}
