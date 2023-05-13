package Atividades;

import java.util.List;

import Conexoes.ContagemDeAtividades;

import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;

import Excecoes.ValorInvalidoException;

public abstract class Atividade implements Comparable<Atividade> {
    private static List<Atividade> listaDeAtividades = new ArrayList<Atividade>();

    private int id;
    private String descricao;
    private Date dataDeRealizacao;
    private int duracao;
    private int satisfacao;
    private int gastoDeEnergia;
    private double bemEstar;

    public Atividade(String descricao, Date dataDeRealizacao, int duracao, int satisfacao) {
        setId(ContagemDeAtividades.getQuantidadeDeAtividades() + 1);
        setDescricao(descricao);
        setDataDeRealizacao(dataDeRealizacao);
        setSatisfacao(satisfacao);
        setDuracao(duracao);
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDataDeRealizacao(Date dataDeRealizacao) {
        this.dataDeRealizacao = dataDeRealizacao;
    }

    public Date getDataDeRealizacao() {
        return dataDeRealizacao;
    }
    
    public void setDuracao(int duracao) {
        try {
            ValorInvalidoException.validarDuracao(duracao);

            this.duracao = duracao;

            calcularGastoDeEnergia();
        }

        catch (ValorInvalidoException erro) {
            System.out.printf("----------------------------------------%n%s%n", erro);
        }
    }

    public int getDuracao() {
        return duracao;
    }

    public void setSatisfacao(int satisfacao) {
        try {
            ValorInvalidoException.validarSatisfacao(satisfacao);

            this.satisfacao = satisfacao;

            calcularBemEstar();
        }

        catch (ValorInvalidoException erro) {
            System.out.printf("----------------------------------------%n%s%n", erro);
        }
    }

    public int getSatisfacao() {
        return satisfacao;
    }

    public void setGastoDeEnergia(int gastoDeEnergia) {
        this.gastoDeEnergia = gastoDeEnergia;
    }

    public int getGastoDeEnergia() {
        return gastoDeEnergia;
    }

    public void setBemEstar(double bemEstar) {
        this.bemEstar = bemEstar;
    }

    public double getBemEstar() {
        return bemEstar;
    }

    public abstract void calcularGastoDeEnergia();

    public void calcularBemEstar() {
        setBemEstar((getGastoDeEnergia() * getSatisfacao()) / 360.0);
    }

    public static List<Atividade> getListaDeAtividades() {
        return listaDeAtividades;
    }

    public void listar() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

        System.out.printf("Descrição: %s | ", getDescricao());
        System.out.printf("Data de realização: %s | ", simpleDateFormat.format(dataDeRealizacao));
        System.out.printf("Duração: %s | ", getDuracao());
        System.out.printf("Satisfação: %s | ", getSatisfacao());
    }

    public void listarGastoDeEnergiaEBemEstar() {
        System.out.printf("Gasto de energia: %s | ", getGastoDeEnergia());
        System.out.printf("Bem-estar: %.2f%n", getBemEstar());
    }

    @Override
    public int compareTo(Atividade outra) {
        return this.getGastoDeEnergia() - outra.getGastoDeEnergia();
    }
}
