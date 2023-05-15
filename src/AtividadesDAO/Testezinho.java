package AtividadesDAO;

import Atividades.*;

import java.sql.SQLException;

public class Testezinho {
    public static void main(String[] args) throws SQLException {
        AtividadeDao dao = new AtividadeDao();

        dao.init();

        AtividadeDeLazerDao aldao = new AtividadeDeLazerDao();
        AtividadeDeTrabalhoDao atdao = new AtividadeDeTrabalhoDao();
        AtividadeFisicaDao afdao = new AtividadeFisicaDao();

        for (Atividade a : dao.getAtividades()) {
            a.listar();
        }

        for (Atividade a : aldao.getAtividades()) {
            a.listar();
        }

        for (Atividade a : atdao.getAtividades()) {
            a.listar();
        }

        for (Atividade a : afdao.getAtividades()) {
            a.listar();
        }
    }
}
