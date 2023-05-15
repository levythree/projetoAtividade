package Main;

import java.util.Scanner;
import java.util.Date;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.sql.SQLException;
import java.sql.Connection;

import Atividades.*;
import Excecoes.*;

public class CadastroDeAtividade {
    public static void cadastrarAtividade() {
        while (true) {
            Scanner input = new Scanner(System.in);

            System.out.printf("""
            ----------------------------------------
                            CADASTRO                
            ----------------------------------------
            [1] - Atividade de lazer
            [2] - Atividade de trabalho
            [3] - Atividade física
            [4] - Voltar
            ----------------------------------------
            """);

            System.out.printf("Escolha uma opção: ");

            try (Connection conexao = Menu.dao.gerarConexao()) {
                int opcao = Integer.parseInt(input.nextLine());
                ValorInvalidoException.validarOpcao(opcao, 6);

                if (opcao == 4) {
                    break;
                }
    
                else {
                    System.out.printf("----------------------------------------%nInforme a descrição da atividade: ");
                    String descricao = input.nextLine();
    
                    System.out.printf("Informe a data de realização da atividade (dd/MM/yyyy): ");
                    Date dataDeRealizacao = new SimpleDateFormat("dd/MM/yyyy").parse(input.nextLine());
    
                    System.out.printf("Informe a duração da atividade (em minutos): ");
                    int duracao = Integer.parseInt(input.nextLine());
                    ValorInvalidoException.validarDuracao(duracao);

                    System.out.printf("Informe a satisfação da atividade (1 para satisfatória, -1 para não satisfatória): ");
                    int satisfacao = Integer.parseInt(input.nextLine());
                    ValorInvalidoException.validarSatisfacao(satisfacao);

                    if (opcao == 1) {
                        Atividade atividadeDeLazer = new AtividadeDeLazer(descricao,dataDeRealizacao, duracao, satisfacao);

                        Menu.aldao.inserir(atividadeDeLazer);
                        Menu.dao.getAtividades().add(atividadeDeLazer);
                    }

                    else if (opcao == 2) {
                        System.out.printf("Informe a dificuldade da atividade (1 para fácil, 2 para médio, 3 para difícil): ");
                        int dificuldade = Integer.parseInt(input.nextLine());
                        ValorInvalidoException.validarDificuldade(dificuldade);
                    
                        Atividade atividadeDeTrabalho = new AtividadeDeTrabalho(descricao,dataDeRealizacao, duracao, satisfacao, dificuldade);

                        Menu.atdao.inserir(atividadeDeTrabalho);
                        Menu.dao.getAtividades().add(atividadeDeTrabalho);
                    }
                
                    else if (opcao == 3) {
                        System.out.printf("Informe a intensidade da atividade (2 para fraco, 3 para intenso, 4 para vigoroso): ");
                        int intensidade = Integer.parseInt(input.nextLine());
                        ValorInvalidoException.validarIntensidade(intensidade);

                        Atividade atividadeFisica = new AtividadeFisica(descricao,dataDeRealizacao, duracao, satisfacao, intensidade);
                        
                        Menu.afdao.inserir(atividadeFisica);
                        Menu.dao.getAtividades().add(atividadeFisica);
                    }
                }
            }

            catch (SQLException erro) {
                System.out.printf("----------------------------------------%n%s%n", erro);
            }

            catch (ValorInvalidoException erro) {
                System.out.printf("----------------------------------------%n%s%n", erro);
            }

            catch (NumberFormatException erro) {
                System.out.printf("----------------------------------------%n%s%n", erro);
            }
            
            catch (ParseException erro) {
                System.out.printf("----------------------------------------%n%s%n", erro);
            }
        }
    }
}
