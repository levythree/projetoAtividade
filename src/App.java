import Conexoes.*;

import java.sql.Connection;

public class App {
    public static void main(String[] args) throws Exception {
        Connection conexao = GeradorDeConexoes.gerarConexao();

        System.out.println("Conectado!");
        
        conexao.close();
    }
}
