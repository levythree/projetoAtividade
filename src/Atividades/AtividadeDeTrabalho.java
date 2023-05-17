package Atividades;

import java.util.Date;

import Excecoes.*;

public class AtividadeDeTrabalho extends Atividade {
    private int dificuldade;

    public AtividadeDeTrabalho(String descricao, Date dataDeRealizacao, int duracao, int satisfacao, int dificuldade) {
        super(descricao, dataDeRealizacao, duracao, satisfacao);
        setDificuldade(dificuldade);
    }

    public void setDificuldade(int dificuldade) {
        try {
            ValorInvalidoException.validarDificuldade(dificuldade);

            this.dificuldade = dificuldade;

            calcularGastoDeEnergia();
        }

        catch (ValorInvalidoException erro) {
            System.out.printf("----------------------------------------%n%s%n", erro);
        }
    }

    public int getDificuldade() {
        return dificuldade;
    }

    @Override
    public void calcularGastoDeEnergia() {
        setGastoDeEnergia(getDuracao() * getDificuldade() * 2);

        calcularBemEstar();
    }

    @Override
    public void listar() {
        super.listar();

        System.out.printf("Dificuldade: %s | ", getDificuldade());

        listarGastoDeEnergiaEBemEstar();
    }
}
