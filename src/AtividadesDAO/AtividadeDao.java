package AtividadesDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.List;
import java.text.SimpleDateFormat;

import Atividades.*;

public class AtividadeDao {
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

    public int getQuantidadeDeAtividades() {
        Connection conexao = gerarConexao();
        
        try {
            PreparedStatement stmt = conexao.prepareStatement("""
                SELECT COUNT(*)
                FROM ATIVIDADE
            """);

            ResultSet rs = stmt.executeQuery();

            rs.next();

            return rs.getInt("COUNT(*)");
        }
        
        catch (SQLException erro) {
            throw new RuntimeException(erro);
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

    public void listar() throws SQLException {
        Connection conexao = gerarConexao();

        SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");

        PreparedStatement stmt = conexao.prepareStatement("""
            SELECT *
            FROM ATIVIDADE
        """);

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            System.out.printf("[%s] - ", rs.getInt("ID"));
            System.out.printf("Descrição: %s | ", rs.getString("DESCRICAO"));
            System.out.printf("Data de realização: %s | ", formatador.format(rs.getDate("DATA_DE_REALIZACAO")));
            System.out.printf("Duração: %s | ", rs.getInt("DURACAO"));
            System.out.printf("Satisfação: %s | ", rs.getInt("SATISFACAO"));
        }
    }

    public void listarGastoDeEnergiaEBemEstar() throws SQLException {
        Connection conexao = gerarConexao();

        PreparedStatement stmt = conexao.prepareStatement("""
            SELECT GASTO_DE_ENERGIA, BEM_ESTAR
            FROM ATIVIDADE      
        """);

        ResultSet rs = stmt.executeQuery();
        
        System.out.printf("Gasto de energia: %s | ", rs.getInt("GASTO_DE_ENERGIA"));
        System.out.printf("Bem-estar: %.2f%n", rs.getDouble("BEM_ESTAR"));
    }

    public void atualizar(Atividade atividade, String[] parametros) throws SQLException {

    }

    public void deletar(int id) throws SQLException {
        Connection conexao = gerarConexao();

        PreparedStatement stmt = conexao.prepareStatement("""
            DELETE FROM
            ATIVIDADE
            WHERE ID = ?
        """);

        stmt.setInt(1, id);

        stmt.execute();

        stmt = conexao.prepareStatement("""
            UPDATE ATIVIDADE
            SET ID = ID - 1
            WHERE ID > ?
        """);

        stmt.setInt(1, id);

        stmt.execute();
    }
}
