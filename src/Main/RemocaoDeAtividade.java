package Main;

import java.sql.SQLException;
import java.util.Scanner;

import Excecoes.ValorInvalidoException;

public class RemocaoDeAtividade {
    public static void removerAtividade() {
        while (true) {
            Scanner input = new Scanner(System.in);

            System.out.printf("""
            ----------------------------------------
                      REMOÇÃO DE ATIVIDADE          
            ---------------------------------------- 
            """);

            Menu.dao.listarAtividades();

            System.out.printf("[%s] - Voltar%n", Menu.dao.getAtividades().size() + 1);

            System.out.printf("----------------------------------------%nEscolha uma opção: ");

            try {
                int opcao = Integer.parseInt(input.nextLine());
                ValorInvalidoException.validarOpcao(opcao, Menu.dao.getAtividades().size() + 1);

                if (opcao == Menu.dao.getAtividades().size() + 1) {
                    break;
                }
    
                else {
                    Menu.dao.deletar(Menu.dao.getAtividades().get(opcao - 1));
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
