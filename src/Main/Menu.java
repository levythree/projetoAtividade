package Main;

import java.util.Scanner;

import AtividadesDAO.*;
import Excecoes.ValorInvalidoException;

public class Menu {
    public static AtividadeDao dao = new AtividadeDao();
    public static AtividadeDeLazerDao aldao = new AtividadeDeLazerDao();
    public static AtividadeDeTrabalhoDao atdao = new AtividadeDeTrabalhoDao();
    public static AtividadeFisicaDao afdao = new AtividadeFisicaDao();

    public static void menu() {
        aldao.init();
        atdao.init();
        afdao.init();

        dao.init(aldao, atdao, afdao);

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

                    dao.listarAtividades();
                }

                else if (opcao == 3) {
                    PesquisaDeAtividade.pesquisarAtividade();
                }

                else if (opcao == 4) {
                    AtualizacaoDeAtividade.atualizarAtividadeMenu();
                }

                else if (opcao == 5) {
                    RemocaoMenu.remocaoMenu();
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
