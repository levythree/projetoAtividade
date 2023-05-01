package Main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import Atividades.*;
import Excecoes.*;
import Conexoes.*;

public class PesquisaDeAtividade {
    public static void pesquisarAtividade() {
        while (true) {
            Scanner input = new Scanner(System.in);

            System.out.printf("""
            ----------------------------------------
                            PESQUISA
            ----------------------------------------
            [1] - Pesquisar por tipo
            [2] - Pesquisar por data
            [3] - Top 3 atividades com maior gasto de energia
            [4] - Voltar
            ----------------------------------------
            """);

            System.out.printf("Escolha uma opção: ");

            try {
                int opcao = Integer.parseInt(input.nextLine());
                ValorInvalidoException.validarOpcao(opcao, 4);

                if (opcao == 1) {
                    pesquisarAtividadePorTipo();
                }

                else if (opcao == 2) {
                    pesquisarAtividadePorData();
                }

                else if (opcao == 3) {
                    listarAtividadesComMaiorGasto();
                }

                else if (opcao == 4) {
                    break;
                }
            }

            catch (NumberFormatException erro) {
                System.out.printf("----------------------------------------%n%s%n", erro);
            }

            catch (ValorInvalidoException erro) {
                System.out.printf("----------------------------------------%n%s%n", erro);
            }
        }
    }

    public static void pesquisarAtividadePorTipo() {
        while (true) {
            Scanner input = new Scanner(System.in);

            System.out.printf("""
            ----------------------------------------
                       PESQUISAR POR TIPO           
            ----------------------------------------
            [1] - Atividade de lazer
            [2] - Atividade de trabalho
            [3] - Atividade física
            [4] - Voltar
            ----------------------------------------
            """);

            System.out.printf("Escolha uma opção: ");

            try {
                int opcao = Integer.parseInt(input.nextLine());
                ValorInvalidoException.validarOpcao(opcao, 4);

                if (opcao == 4) {
                    break;
                }

                System.out.printf("----------------------------------------%n");

                if (opcao == 1) {
                    SelecaoDeAtividades.selecionarAtividades("ATIVIDADE_DE_LAZER");
                }

                else if (opcao == 2) {
                    SelecaoDeAtividades.selecionarAtividades("ATIVIDADE_DE_TRABALHO");
                }

                else if (opcao == 3) {
                    SelecaoDeAtividades.selecionarAtividades("ATIVIDADE_FISICA");
                }

                /*
                for (Atividade atividade : Atividade.getListaDeAtividades()) {
                    if (opcao == 1 && atividade instanceof AtividadeDeLazer) {
                        atividade.listar();
                    }

                    else if (opcao == 2 && atividade instanceof AtividadeDeTrabalho) {
                        atividade.listar();
                    }

                    else if (opcao == 3 && atividade instanceof AtividadeFisica) {
                        atividade.listar();
                    }
                }
                */
            }

            catch (NumberFormatException erro) {
                System.out.printf("----------------------------------------%n%s%n", erro);
            }

            catch (ValorInvalidoException erro) {
                System.out.printf("----------------------------------------%n%s%n", erro);
            }
        }
    }

    public static void pesquisarAtividadePorData() {
        Scanner input = new Scanner(System.in);

        System.out.printf("""
        ----------------------------------------
                   PESQUISAR POR DATA           
        ----------------------------------------
        """);

        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
            int gastoDeEnergiaDoPeriodo = 0;

            System.out.printf("Informe a data inicial: ");
            Date dataInicial = simpleDateFormat.parse(input.nextLine());

            System.out.printf("Informe a data final: ");
            Date dataFinal = simpleDateFormat.parse(input.nextLine());

            System.out.printf("----------------------------------------%n");

            for (Atividade atividade : Atividade.getListaDeAtividades()) {
                if (atividade.getDataDeRealizacao().compareTo(dataInicial) >= 0 &&
                atividade.getDataDeRealizacao().compareTo(dataFinal) <= 0) {
                    atividade.listar();
                    
                    gastoDeEnergiaDoPeriodo += atividade.getGastoDeEnergia();
                }
            }

            System.out.printf("%nGasto de energia total do período (%s - %s): %s%n", simpleDateFormat.format(dataInicial), simpleDateFormat.format(dataFinal), gastoDeEnergiaDoPeriodo);
        }

        catch (ParseException erro) {
            System.out.printf("----------------------------------------%n%s%n", erro);
        }
    }

    public static void listarAtividadesComMaiorGasto() {
        List<Atividade> listaDeAtividadesCopia = new ArrayList<Atividade>(Atividade.getListaDeAtividades());

        Collections.sort(listaDeAtividadesCopia, Collections.reverseOrder());

        System.out.printf("----------------------------------------%n");

        for (int i = 0; i < listaDeAtividadesCopia.size(); i++) {
            if (i == 3) {
                break;
            }

            listaDeAtividadesCopia.get(i).listar();
        }
    }
}
