package Excecoes;

public class ValorInvalidoException extends Exception {
    public ValorInvalidoException(String mensagemDeErro) {
        super(mensagemDeErro);
    }

    public static void validarOpcao(int opcao, int voltar) throws ValorInvalidoException {
        if (opcao < 1 || opcao > voltar) {
            throw new ValorInvalidoException("Por favor, informe uma opção válida.");
        }
    }

    public static void validarDuracao(int duracao) throws ValorInvalidoException {
        if (duracao <= 1) {
            throw new ValorInvalidoException("Informe uma duração válida para a atividade (em minutos).");
        }
    }

    public static void validarSatisfacao(int satisfacao) throws ValorInvalidoException {
        if (satisfacao == 1 || satisfacao == -1) {
            ;
        }

        else {
            throw new ValorInvalidoException("Informe uma satisfação válida para a atividade (1 para satisfatória, -1 para não satisfatória).");
        }
    }

    public static void validarDificuldade(int dificuldade) throws ValorInvalidoException {
        if (dificuldade < 1 || dificuldade > 3) {
            throw new ValorInvalidoException("Informe uma dificuldade válida para a atividade (1 para fácil, 2 para médio, 3 para difícil).");
        }
    }

    public static void validarIntensidade(int intensidade) throws ValorInvalidoException {
        if (intensidade < 2 || intensidade > 4) {
            throw new ValorInvalidoException("Informe uma intensidade válida para atividade (2 para fraco, 3 para intenso, 4 para vigoroso).");
        }
    }
}
