package Conexoes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.text.SimpleDateFormat;

import java.util.List;
import java.util.ArrayList;

public class SelecaoDeAtividades {
    public static void selecionarAtividades(String tabela) {
        try {
            List<Integer> idsTrabalho = new ArrayList<Integer>();
            List<Integer> idsFisica = new ArrayList<Integer>();

            List<Integer> dificuldades = new ArrayList<Integer>();
            List<Integer> intensidades = new ArrayList<Integer>();

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

            Connection conexao = GeradorDeConexoes.gerarConexao();

            if (tabela.equals("ATIVIDADE")) {
                PreparedStatement stmtAtividade = conexao.prepareStatement("""
                    SELECT *
                    FROM ATIVIDADE
                """);
                PreparedStatement stmtAtividadeDeTrabalho = conexao.prepareStatement("""
                    SELECT A.ID, AT.DIFICULDADE
                    FROM ATIVIDADE A, ATIVIDADE_DE_TRABALHO AT
                    WHERE A.ID = AT.ID
                """);
                PreparedStatement stmtAtividadeFisica = conexao.prepareStatement("""
                    SELECT A.ID, AF.INTENSIDADE
                    FROM ATIVIDADE A, ATIVIDADE_FISICA AF
                    WHERE A.ID = AF.ID
                """);

                ResultSet rsAtividade = stmtAtividade.executeQuery();
                ResultSet rsAtividadeDeTrabalho = stmtAtividadeDeTrabalho.executeQuery();
                ResultSet rsAtividadeFisica = stmtAtividadeFisica.executeQuery();

                while (rsAtividadeDeTrabalho.next()) {
                    idsTrabalho.add(rsAtividadeDeTrabalho.getInt("A.ID"));
                    dificuldades.add(rsAtividadeDeTrabalho.getInt("DIFICULDADE"));
                }

                while (rsAtividadeFisica.next()) {
                    idsFisica.add(rsAtividadeFisica.getInt("A.ID"));
                    intensidades.add(rsAtividadeFisica.getInt("INTENSIDADE"));
                }

                while (rsAtividade.next()) {
                    System.out.printf("ID: %s | ", rsAtividade.getInt("ID"));
                    System.out.printf("Descrição: %s | ", rsAtividade.getString("DESCRICAO"));
                    System.out.printf("Data de realização: %s | ", simpleDateFormat.format(rsAtividade.getDate("DATA_DE_REALIZACAO")));
                    System.out.printf("Duração: %s | ", rsAtividade.getInt("DURACAO"));
                    System.out.printf("Satisfação: %s | ", rsAtividade.getInt("SATISFACAO"));
                    
                    for (int i = 0; i < idsTrabalho.size(); i++) {
                        if (rsAtividade.getInt("ID") == idsTrabalho.get(i)) {
                            System.out.printf("Dificuldade: %s | ", dificuldades.get(i));
                        }
                    }

                    for (int i = 0; i < idsFisica.size(); i++) {
                        if (rsAtividade.getInt("ID") == idsFisica.get(i)) {
                            System.out.printf("Intensidade: %s | ", intensidades.get(i));
                        }
                    }
    
                    System.out.printf("Gasto de energia: %s | ", rsAtividade.getInt("GASTO_DE_ENERGIA"));
                    System.out.printf("Bem-estar: %.2f%n", rsAtividade.getDouble("BEM_ESTAR"));
                }
            }

            else if (tabela.equals("ATIVIDADE_DE_LAZER")) {
                PreparedStatement statement = conexao.prepareStatement("""
                    SELECT A.ID, A.DESCRICAO, A.DATA_DE_REALIZACAO, A.DURACAO, A.SATISFACAO, A.GASTO_DE_ENERGIA, A.BEM_ESTAR
                    FROM ATIVIDADE A, ATIVIDADE_DE_LAZER AL
                    WHERE A.ID = AL.ID
                """);

                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    System.out.printf("ID: %s | ", resultSet.getInt("ID"));
                    System.out.printf("Descrição: %s | ", resultSet.getString("DESCRICAO"));
                    System.out.printf("Data de realização: %s | ", simpleDateFormat.format(resultSet.getDate("DATA_DE_REALIZACAO")));
                    System.out.printf("Duração: %s | ", resultSet.getInt("DURACAO"));
                    System.out.printf("Satisfação: %s | ", resultSet.getInt("SATISFACAO"));
                    System.out.printf("Gasto de energia: %s | ", resultSet.getInt("GASTO_DE_ENERGIA"));
                    System.out.printf("Bem-estar: %.2f%n", resultSet.getDouble("BEM_ESTAR"));
                }
            }

            else if (tabela.equals("ATIVIDADE_DE_TRABALHO")) {
                PreparedStatement statement = conexao.prepareStatement("""
                    SELECT *
                    FROM ATIVIDADE A, ATIVIDADE_DE_TRABALHO AT
                    WHERE A.ID = AT.ID        
                """);

                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    System.out.printf("ID: %s | ", resultSet.getInt("A.ID"));
                    System.out.printf("Descrição: %s | ", resultSet.getString("A.DESCRICAO"));
                    System.out.printf("Data de realização: %s | ", simpleDateFormat.format(resultSet.getDate("A.DATA_DE_REALIZACAO")));
                    System.out.printf("Duração: %s | ", resultSet.getInt("A.DURACAO"));
                    System.out.printf("Satisfação: %s | ", resultSet.getInt("A.SATISFACAO"));
                    System.out.printf("Dificuldade: %s | ", resultSet.getInt("AT.DIFICULDADE"));    
                    System.out.printf("Gasto de energia: %s | ", resultSet.getInt("A.GASTO_DE_ENERGIA"));
                    System.out.printf("Bem-estar: %.2f%n", resultSet.getDouble("A.BEM_ESTAR"));
                }
            }

            else if (tabela.equals("ATIVIDADE_FISICA")) {
                PreparedStatement statement = conexao.prepareStatement("""
                    SELECT *
                    FROM ATIVIDADE A, ATIVIDADE_FISICA AF
                    WHERE A.ID = AF.ID
                """);

                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    System.out.printf("ID: %s | ", resultSet.getInt("A.ID"));
                    System.out.printf("Descrição: %s | ", resultSet.getString("A.DESCRICAO"));
                    System.out.printf("Data de realização: %s | ", simpleDateFormat.format(resultSet.getDate("A.DATA_DE_REALIZACAO")));
                    System.out.printf("Duração: %s | ", resultSet.getInt("A.DURACAO"));
                    System.out.printf("Satisfação: %s | ", resultSet.getInt("A.SATISFACAO"));
                    System.out.printf("Intensidade: %s | ", resultSet.getInt("AF.INTENSIDADE"));    
                    System.out.printf("Gasto de energia: %s | ", resultSet.getInt("A.GASTO_DE_ENERGIA"));
                    System.out.printf("Bem-estar: %.2f%n", resultSet.getDouble("A.BEM_ESTAR"));
                }
            }
        }
        
        catch (SQLException erro) {
            throw new RuntimeException(erro);
        }
    }
}
