package Main;

import java.util.Scanner;

import Conexoes.SelecaoDeAtividades;
import Excecoes.ValorInvalidoException;

public class Menu {
    public static void menu() {
        while (true) {
            Scanner input = new Scanner(System.in);

            System.out.printf("""
            ----------------------------------------
                              MENU                    
            ----------------------------------------
            [1] - Cadastrar atividade
            [2] - Listar todas as atividades
            [3] - Pesquisar atividade
            [4] - Atualizar atividade
            [5] - Remover atividade
            [6] - Sair
            ----------------------------------------
            """);

            System.out.printf("Escolha uma opção: ");

            try {
                int opcao = Integer.parseInt(input.nextLine());
                ValorInvalidoException.validarOpcao(opcao, 6);

                if (opcao == 1) {
                    CadastroDeAtividade.cadastrarAtividade();
                }

                else if (opcao == 2) {
                    System.out.printf("""
                ----------------------------------------
                         ATIVIDADES CADASTRADAS          
                ---------------------------------------- 
                """);

                    SelecaoDeAtividades.selecionarAtividades("ATIVIDADE");
                }

                else if (opcao == 3) {
                    PesquisaDeAtividade.pesquisarAtividade();
                }

                else if (opcao == 4) {
                    AtualizacaoDeAtividade.atualizarAtividadeMenu();
                }

                else if (opcao == 5) {
                    RemocaoDeAtividade.removerAtividade();
                }

                else if (opcao == 6) {
                    break;
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
}
