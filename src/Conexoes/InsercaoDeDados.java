package Conexoes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Atividades.*;

public class InsercaoDeDados {
    public static void inserir(Atividade atividade) {
        try {
            Connection conexao = GeradorDeConexoes.gerarConexao();

            PreparedStatement statement = conexao.prepareStatement("""
                INSERT INTO ATIVIDADE
                VALUES (?, ?, ?, ?, ?, ?, ?)   
            """);

            statement.setInt(1, ContagemDeAtividades.getQuantidadeDeAtividades() + 1);
            statement.setString(2, atividade.getDescricao());
            statement.setDate(3, new java.sql.Date(atividade.getDataDeRealizacao().getTime()));
            statement.setInt(4, atividade.getDuracao());
            statement.setInt(5, atividade.getSatisfacao());
            statement.setInt(6, atividade.getGastoDeEnergia());
            statement.setDouble(7, atividade.getBemEstar());

            statement.execute();

            if (atividade instanceof AtividadeDeLazer) {
                PreparedStatement lazer = conexao.prepareStatement("""
                    INSERT INTO ATIVIDADE_DE_LAZER
                    VALUES (?)        
                """);

                lazer.setInt(1, ContagemDeAtividades.getQuantidadeDeAtividades());

                lazer.execute();
            }

            else if (atividade instanceof AtividadeDeTrabalho) {
                PreparedStatement trabalho = conexao.prepareStatement("""
                    INSERT INTO ATIVIDADE_DE_TRABALHO
                    VALUES (?, ?)
                """);

                trabalho.setInt(1, ContagemDeAtividades.getQuantidadeDeAtividades());
                trabalho.setInt(2, ((AtividadeDeTrabalho) atividade).getDificuldade());

                trabalho.execute();
            }

            else if (atividade instanceof AtividadeFisica) {
                PreparedStatement fisica = conexao.prepareStatement("""
                    INSERT INTO ATIVIDADE_FISICA
                    VALUES (?, ?)        
                """);

                fisica.setInt(1, ContagemDeAtividades.getQuantidadeDeAtividades());
                fisica.setInt(2, ((AtividadeFisica) atividade).getIntensidade());

                fisica.execute();
            }
        }
        
        catch (SQLException erro) {
            throw new RuntimeException(erro);
        }
    }
}
