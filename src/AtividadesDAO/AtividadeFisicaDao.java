package AtividadesDAO;

import java.util.List;
import java.util.ArrayList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

import Atividades.*;

public class AtividadeFisicaDao extends AtividadeDao {
    private List<Atividade> atividadesFisicas = new ArrayList<Atividade>();

    public void init() {
        try {
            Connection conexao = gerarConexao();

            PreparedStatement stmt = conexao.prepareStatement("""
                SELECT *
                FROM ATIVIDADE A, ATIVIDADE_FISICA AF
                WHERE A.ID = AF.ID
            """);
    
            ResultSet rs = stmt.executeQuery();
    
            while (rs.next()) {
                Atividade atividade = new AtividadeFisica(rs.getString("DESCRICAO"), 
                rs.getDate("DATA_DE_REALIZACAO"), 
                rs.getInt("DURACAO"), 
                rs.getInt("SATISFACAO"), 
                rs.getInt("INTENSIDADE"));
    
                atividade.setId(rs.getInt("ID"));
    
                atividadesFisicas.add(atividade);
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
        return atividadesFisicas;
    }

    @Override
    public void inserir(Atividade atividade) throws SQLException {
        super.inserir(atividade);

        Connection conexao = gerarConexao();

        PreparedStatement stmt = conexao.prepareStatement("""
            INSERT INTO ATIVIDADE_FISICA
            VALUES (?, ?)
        """);
        
        atividadesFisicas.add(atividade);

        stmt.setInt(1, atividade.getId());
        stmt.setInt(2, ((AtividadeFisica) atividade).getIntensidade());

        stmt.execute();

        stmt.close();
        conexao.close();
    }
}
