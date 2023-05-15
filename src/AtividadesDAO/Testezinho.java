package AtividadesDAO;

import Atividades.*;

import java.sql.SQLException;

public class Testezinho {
    public static void main(String[] args) throws SQLException {
        AtividadeDao dao = new AtividadeDao();

        AtividadeDeLazerDao aldao = new AtividadeDeLazerDao();
        AtividadeDeTrabalhoDao atdao = new AtividadeDeTrabalhoDao();
        AtividadeFisicaDao afdao = new AtividadeFisicaDao();

        aldao.init();
        atdao.init();
        afdao.init();

        dao.init(aldao, atdao, afdao);

        dao.listarAtividades();
        aldao.listarAtividades();
        atdao.listarAtividades();
        afdao.listarAtividades();

        dao.inserir(new AtividadeDeLazer("TESTE", new java.util.Date(), 60, 1));

        dao.listarAtividades();
    }
}
