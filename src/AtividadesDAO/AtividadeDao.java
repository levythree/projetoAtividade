package AtividadesDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Atividades.*;

public class AtividadeDao {
    private List<Atividade> atividades = new ArrayList<Atividade>();

    public Connection gerarConexao() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/DB_ATIVIDADE", "root", "root");
        }
        
        catch (SQLException erro) {
            throw new RuntimeException(erro);
        }

        catch (ClassNotFoundException erro) {
            throw new RuntimeException(erro);
        }
    }

    public void init(AtividadeDeLazerDao aldao, AtividadeDeTrabalhoDao atdao, AtividadeFisicaDao afdao) {
        for (Atividade a : aldao.getAtividades()) {
            atividades.add(a);
        }

        for (Atividade a : atdao.getAtividades()) {
            atividades.add(a);
        }

        for (Atividade a : afdao.getAtividades()) {
            atividades.add(a);
        }

        atividades.sort((a1, a2) -> {
            return Integer.valueOf(a1.getId()).compareTo(Integer.valueOf(a2.getId()));
        });
    }

    public List<Atividade> getAtividades() {
        return atividades;
    }

    public void listarAtividades() {
        for (Atividade a : atividades) {
            a.listar();
        }
    }

    public void inserir(Atividade atividade) throws SQLException {
        Connection conexao = gerarConexao();

        PreparedStatement stmt = conexao.prepareStatement("""
            INSERT INTO ATIVIDADE
            VALUES (?, ?, ?, ?, ?, ?, ?)       
        """);

        stmt.setInt(1, atividade.getId());
        stmt.setString(2, atividade.getDescricao());
        stmt.setDate(3, new java.sql.Date(atividade.getDataDeRealizacao().getTime()));
        stmt.setInt(4, atividade.getDuracao());
        stmt.setInt(5, atividade.getSatisfacao());
        stmt.setInt(6, atividade.getGastoDeEnergia());
        stmt.setDouble(7, atividade.getBemEstar());

        stmt.execute();

        stmt.close();
        conexao.close();
    }

    public void atualizar(String[] args) throws SQLException {
        Connection conexao = gerarConexao();

        String sql = """
            UPDATE %s
            SET %s = %s
            WHERE ID = %s        
        """;

        sql = String.format(sql, args[0], args[1], args[2], args[3]);

        PreparedStatement stmt = conexao.prepareStatement(sql);

        stmt.execute();

        stmt.close();
        conexao.close();
    }

    public void deletar(int id) throws SQLException {
        atividades.remove(id - 1);

        Connection conexao = gerarConexao();

        PreparedStatement stmt = conexao.prepareStatement("""
            DELETE FROM
            ATIVIDADE
            WHERE ID = ?
        """);

        stmt.setInt(1, id);

        stmt.execute();

        for (Atividade a : atividades) {
            if (a.getId() > id) {
                a.setId(a.getId() - 1);
            }
        }

        stmt = conexao.prepareStatement("""
            UPDATE ATIVIDADE
            SET ID = ID - 1
            WHERE ID > ?
        """);

        stmt.setInt(1, id);

        stmt.execute();

        stmt.close();
        conexao.close();
    }
}
