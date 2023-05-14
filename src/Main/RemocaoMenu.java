package Main;

import java.sql.SQLException;
import java.util.Scanner;

import Conexoes.*;
import AtividadesDAO.*;
import Excecoes.ValorInvalidoException;

public class RemocaoMenu {
    public static void remocaoMenu() {
        AtividadeDao dao = new AtividadeDao();

        while (true) {
            Scanner input = new Scanner(System.in);

            System.out.printf("""
            ----------------------------------------
                      REMOÇÃO DE ATIVIDADE          
            ---------------------------------------- 
            """);

            SelecaoDeAtividades.selecionarAtividades("ATIVIDADE");

            System.out.printf("[%s] - Voltar%n", dao.getQuantidadeDeAtividades() + 1);

            System.out.printf("----------------------------------------%nEscolha uma opção: ");

            try {
                int opcao = Integer.parseInt(input.nextLine());
                ValorInvalidoException.validarOpcao(opcao, dao.getQuantidadeDeAtividades() + 1);

                if (opcao == dao.getQuantidadeDeAtividades() + 1) {
                    break;
                }
    
                else {
                    dao.deletar(opcao);
                }
            }

            catch (ValorInvalidoException erro) {
                System.out.printf("----------------------------------------%n%s%n", erro);
            }

            catch (NumberFormatException erro) {
                System.out.printf("----------------------------------------%n%s%n", erro);
            }

            catch (SQLException erro) {
                System.out.printf("----------------------------------------%n%s%n", erro);
            }
        } 
    }
}
