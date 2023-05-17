package AtividadesDAO;

import Atividades.*;

import java.util.List;
import java.util.ArrayList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class AtividadeDeTrabalhoDao extends AtividadeDao {
    private List<Atividade> atividadesDeTrabalho = new ArrayList<Atividade>();

    public void init() {
        try {
            Connection conexao = gerarConexao();

            PreparedStatement stmt = conexao.prepareStatement("""
                SELECT *
                FROM ATIVIDADE A, ATIVIDADE_DE_TRABALHO AT
                WHERE A.ID = AT.ID
            """);
    
            ResultSet rs = stmt.executeQuery();
    
            while (rs.next()) {
                Atividade atividade = new AtividadeDeTrabalho(rs.getString("DESCRICAO"), 
                rs.getDate("DATA_DE_REALIZACAO"), 
                rs.getInt("DURACAO"), 
                rs.getInt("SATISFACAO"), 
                rs.getInt("DIFICULDADE"));
    
                atividade.setId(rs.getInt("ID"));
    
                atividadesDeTrabalho.add(atividade);
            }
    
            rs.close();
            stmt.close();
            conexao.close();
        }
        
        catch (SQLException erro) {
            throw new RuntimeException(erro);
        }
    }

    @Override
    public List<Atividade> getAtividades() {
        return atividadesDeTrabalho;
    }

    @Override
    public void inserir(Atividade atividade) throws SQLException {
        super.inserir(atividade);

        Connection conexao = gerarConexao();

        PreparedStatement stmt = conexao.prepareStatement("""
            INSERT INTO ATIVIDADE_DE_TRABALHO
            VALUES (?, ?)
        """);

        atividadesDeTrabalho.add(atividade);

        stmt.setInt(1, atividade.getId());
        stmt.setInt(2, ((AtividadeDeTrabalho) atividade).getDificuldade());

        stmt.execute();

        stmt.close();
        conexao.close();
    }
}
