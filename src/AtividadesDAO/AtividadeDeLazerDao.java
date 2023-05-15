package AtividadesDAO;

import java.util.List;
import java.util.ArrayList;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Atividades.*;

public class AtividadeDeLazerDao extends AtividadeDao {
    private List<Atividade> atividadesDeLazer = new ArrayList<Atividade>();

    public void init() {
        try {
            Connection conexao = gerarConexao();

            PreparedStatement stmt = conexao.prepareStatement("""
                SELECT *
                FROM ATIVIDADE A, ATIVIDADE_DE_LAZER AL
                WHERE A.ID = AL.ID
            """);
    
            ResultSet rs = stmt.executeQuery();
    
            while (rs.next()) {
                Atividade atividade = new AtividadeDeLazer(rs.getString("DESCRICAO"), 
                rs.getDate("DATA_DE_REALIZACAO"), 
                rs.getInt("DURACAO"), 
                rs.getInt("SATISFACAO"));
    
                atividade.setId(rs.getInt("ID"));
    
                atividadesDeLazer.add(atividade);
            }
    
            rs.close();
            stmt.close();
            conexao.close();  
        }
        
        catch (SQLException erro) {
            ;
        }
    }

    @Override
    public List<Atividade> getAtividades() {
        return atividadesDeLazer;
    }

    @Override
    public void inserir(Atividade atividade) throws SQLException {
        super.inserir(atividade);

        Connection conexao = gerarConexao();

        PreparedStatement stmt = conexao.prepareStatement("""
            INSERT INTO ATIVIDADE_DE_LAZER
            VALUES (?)
        """);

        atividadesDeLazer.add(atividade);

        stmt.setInt(1, atividade.getId());

        stmt.execute();

        stmt.close();
        conexao.close();
    }
}
