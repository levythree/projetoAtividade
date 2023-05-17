package Atividades;

import java.util.Date;

import Excecoes.*;

public class AtividadeFisica extends Atividade {
    private int intensidade;

    public AtividadeFisica(String descricao, Date dataDeRealizacao, int duracao, int satisfacao, int intensidade) {
        super(descricao, dataDeRealizacao, duracao, satisfacao);
        setIntensidade(intensidade);
    }

    public void setIntensidade(int intensidade) {
        try {
            ValorInvalidoException.validarIntensidade(intensidade);

            this.intensidade = intensidade;

            calcularGastoDeEnergia();
        }

        catch (ValorInvalidoException erro) {
            System.out.printf("----------------------------------------%n%s%n", erro);
        }
    }

    public int getIntensidade() {
        return intensidade;
    }

    @Override
    public void calcularGastoDeEnergia() {
        setGastoDeEnergia(getDuracao() * getIntensidade() * 3);

        calcularBemEstar();
    }

    @Override
    public void listar() {
        super.listar();

        System.out.printf("Intensidade: %s | ", getIntensidade());

        listarGastoDeEnergiaEBemEstar();
    }
}
