package Main;

import java.util.Scanner;

import Conexoes.*;
import Excecoes.ValorInvalidoException;

public class RemocaoMenu {
    public static void remocaoMenu() {
        while (true) {
            Scanner input = new Scanner(System.in);

            System.out.printf("""
            ----------------------------------------
                      REMOÇÃO DE ATIVIDADE          
            ---------------------------------------- 
            """);

            SelecaoDeAtividades.selecionarAtividades("ATIVIDADE");

            System.out.printf("[%s] - Voltar%n", ContagemDeAtividades.getQuantidadeDeAtividades() + 1);

            System.out.printf("----------------------------------------%nEscolha uma opção: ");

            try {
                int opcao = Integer.parseInt(input.nextLine());
                ValorInvalidoException.validarOpcao(opcao, ContagemDeAtividades.getQuantidadeDeAtividades() + 1);

                if (opcao == ContagemDeAtividades.getQuantidadeDeAtividades() + 1) {
                    break;
                }
    
                else {
                    RemocaoDeDados.remover(opcao);
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
