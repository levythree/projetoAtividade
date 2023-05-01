package Main;

import Atividades.Atividade;

public class AtividadesCadastradas {
    public static void listarAtividades() {
        for (int index = 0; index < Atividade.getListaDeAtividades().size(); index++) {
            System.out.printf("[%s] - ", index + 1);
            Atividade.getListaDeAtividades().get(index).listar();
        }
    }
}
