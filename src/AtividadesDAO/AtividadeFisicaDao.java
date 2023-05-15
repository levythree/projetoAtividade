package AtividadesDAO;

import java.util.List;
import java.util.ArrayList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

import Atividades.*;

public class AtividadeFisicaDao extends AtividadeDao {
    public List<Atividade> atividadesFisicas = new ArrayList<Atividade>();

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

    @Override
    public List<Atividade> getAtividades() {
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
    
            return atividadesFisicas;
        }
        
        catch (SQLException erro) {
            return atividadesFisicas;
        }
    }
}
