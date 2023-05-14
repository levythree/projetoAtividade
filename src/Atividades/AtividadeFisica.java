package Atividades;

import AtividadesDAO.*;

import Excecoes.ValorInvalidoException;
import java.util.Date;

import java.sql.SQLException;

public class AtividadeFisica extends Atividade {
    AtividadeFisicaDao dao = new AtividadeFisicaDao();

    private int intensidade;

    public AtividadeFisica(String descricao, Date dataDeRealizacao, int duracao, int satisfacao, int intensidade) throws SQLException {
        super(descricao, dataDeRealizacao, duracao, satisfacao);
        setIntensidade(intensidade);

        dao.inserir(this);
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
