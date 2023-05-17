package Main;

import java.util.Scanner;
import java.util.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import Atividades.Atividade;
import Atividades.AtividadeDeLazer;
import Atividades.AtividadeDeTrabalho;
import Atividades.AtividadeFisica;
import Excecoes.ValorInvalidoException;

public class AtualizacaoDeAtividade {
    public static void atualizarAtividadeMenu() {
        while (true) {
            Scanner input = new Scanner(System.in);

            System.out.printf("""
            ----------------------------------------
                    ATUALIZAÇÃO DE ATIVIDADE          
            ----------------------------------------
            """);

            Menu.dao.listarAtividades();

            System.out.printf("[%s] - Voltar%n----------------------------------------%nEscolha uma opção: ", Menu.dao.getAtividades().size() + 1);

            try {
                int opcao = Integer.parseInt(input.nextLine()) - 1;
                ValorInvalidoException.validarOpcao(opcao + 1, Menu.dao.getAtividades().size() + 1);

                if (opcao + 1 == Menu.dao.getAtividades().size() + 1) {
                    break;
                }
    
                else {
                    atualizarAtividade(opcao);
                }
            }

            catch (ValorInvalidoException erro) {
                System.out.printf("----------------------------------------%n%s%n", erro);
            }

            catch (NumberFormatException erro) {
                System.out.printf("----------------------------------------%n%s%n", erro);
            }
        }
    }

    public static void atualizarAtividade(int opcao) {
        String[] args = new String[3];
        args[0] = "ATIVIDADE";

        while (true) {
            Scanner input = new Scanner(System.in);

            int voltar;

            if (Menu.dao.getAtividades().get(opcao) instanceof AtividadeDeLazer) {
                voltar = 5;
            }

            else {
                voltar = 6;
            }

            System.out.printf("""
            ----------------------------------------
                    ATUALIZAÇÃO DE ATIVIDADE        
            ----------------------------------------
            [1] - Descrição
            [2] - Data de realização
            [3] - Duração
            [4] - Satisfação
            """);

            if (Menu.dao.getAtividades().get(opcao) instanceof AtividadeDeTrabalho) {
                System.out.printf("[5] - Dificuldade%n");
            }

            else if (Menu.dao.getAtividades().get(opcao) instanceof AtividadeFisica) {
                System.out.printf("[5] - Intensidade%n");
            }

            System.out.printf("[%s] - Voltar%n----------------------------------------%nEscolha uma opção: ", voltar);

            try {
                int segundaOpcao = Integer.parseInt(input.nextLine());
        
                ValorInvalidoException.validarOpcao(segundaOpcao, voltar);
    
                if (segundaOpcao == voltar) {
                    break;
                }
            
                if (segundaOpcao == 1) {
                    System.out.printf("Informe a nova descrição da atividade: ");
                    String descricao = input.nextLine();
                
                    Menu.dao.getAtividades().get(opcao).setDescricao(descricao);

                    args[1] = "DESCRICAO";
                    args[2] = descricao;
                }
            
                else if (segundaOpcao == 2) {
                    System.out.printf("Informe a nova data de realização da atividade (dd/MM/yyyy): ");
                    String dataDeRealizacaoString = input.nextLine();
                    
                    Date dataDeRealizacao = new SimpleDateFormat("dd/MM/yyyy").parse(dataDeRealizacaoString); 

                    Menu.dao.getAtividades().get(opcao).setDataDeRealizacao(dataDeRealizacao);

                    args[1] = "DATA_DE_REALIZACAO";
                    args[2] = dataDeRealizacaoString;
                }
            
                else if (segundaOpcao == 3) {
                    System.out.printf("Informe a nova duração da atividade (em minutos): ");
                    int duracao = Integer.parseInt(input.nextLine());
                
                    Menu.dao.getAtividades().get(opcao).setDuracao(duracao);

                    args[1] = "DURACAO";
                    args[2] = String.valueOf(duracao);
                }
            
                else if (segundaOpcao == 4) {
                    System.out.printf("Informe a nova satisfação da atividade (1 para satisfatória, -1 para não satisfatória): ");
                    int satisfacao = Integer.parseInt(input.nextLine());
                
                    Menu.dao.getAtividades().get(opcao).setSatisfacao(satisfacao);

                    args[1] = "SATISFACAO";
                    args[2] = String.valueOf(satisfacao);
                }
            
                else if (segundaOpcao == 5 && Menu.dao.getAtividades().get(opcao) instanceof AtividadeDeTrabalho) {
                    System.out.printf("Informe a nova dificuldade da atividade (1 para fácil, 2 para médio, 3 para difícil): ");
                    int dificuldade = Integer.parseInt(input.nextLine());
                
                    ((AtividadeDeTrabalho) Menu.dao.getAtividades().get(opcao)).setDificuldade(dificuldade);

                    args[0] = "ATIVIDADE_DE_TRABALHO";
                    args[1] = "DIFICULDADE";
                    args[2] = String.valueOf(dificuldade);
                }
            
                else if (segundaOpcao == 5 && Menu.dao.getAtividades().get(opcao) instanceof AtividadeFisica) {
                    System.out.printf("Informe a nova intensidade da atividade (2 para fraco, 3 para intenso, 4 para vigoroso): ");
                    int intensidade = Integer.parseInt(input.nextLine());
                
                    ((AtividadeFisica) Menu.dao.getAtividades().get(opcao)).setIntensidade(intensidade);

                    args[0] = "ATIVIDADE_FISICA";
                    args[1] = "INTENSIDADE";
                    args[2] = String.valueOf(intensidade);
                }
                
                Menu.dao.atualizar(Menu.dao.getAtividades().get(opcao), args);
            }

            catch (NumberFormatException erro) {
                System.out.printf("----------------------------------------%n%s%n", erro);
            }

            catch (ValorInvalidoException erro) {
                System.out.printf("----------------------------------------%n%s%n", erro);
            }

            catch (ParseException erro) {
                System.out.printf("----------------------------------------%n%s%n", erro);
            }

            catch (SQLException erro) {
                System.out.printf("----------------------------------------%n%s%n", erro);
            }
        }
    }
}