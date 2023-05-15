package Atividades;

import AtividadesDAO.*;

import java.util.Date;

import java.sql.SQLException;

public class AtividadeDeLazer extends Atividade {
    AtividadeDeLazerDao dao = new AtividadeDeLazerDao();

    public AtividadeDeLazer(String descricao, Date dataDeRealizacao, int duracao, int satisfacao) throws SQLException {
        super(descricao, dataDeRealizacao, duracao, satisfacao);
    }

    @Override
    public void calcularGastoDeEnergia() {
        setGastoDeEnergia(getDuracao() * 1);

        calcularBemEstar();
    }

    @Override
    public void listar() {
        super.listar();

        listarGastoDeEnergiaEBemEstar();
    }
}
