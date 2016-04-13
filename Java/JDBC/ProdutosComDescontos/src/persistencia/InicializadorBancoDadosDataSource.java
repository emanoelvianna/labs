package persistencia;

import java.sql.Connection;
import java.sql.Statement;

import javax.sql.DataSource;

import org.apache.derby.jdbc.EmbeddedDataSource;

public class InicializadorBancoDadosDataSource {

    public static String DB_NAME = "cadastro";
    public static String USER_NAME = "usuario";
    public static String PASSWORD = "senha";
    private static DataSource dataSource;

    private static DataSource criarDataSource() throws Exception {
        if (dataSource == null) {
            EmbeddedDataSource ds = new EmbeddedDataSource();
            ds.setDatabaseName(DB_NAME);
            ds.setCreateDatabase("create");
            ds.setUser(USER_NAME);
            ds.setPassword(PASSWORD);
            dataSource = ds;
        }
        return dataSource;
    }

    public static void criarBd() throws Exception {
        try (Connection con = criarDataSource().getConnection();
                Statement sta = con.createStatement()) {
            String sqlProduto_code = "CREATE TABLE PRODUTO_CODE("
                    + "CODIGO CHAR(2) PRIMARY KEY NOT NULL,"
                    + "DISCOUNT_CODE CHAR(1) NOT NULL,"
                    + "DESCRITION VARCHAR(10))";
            sta.executeUpdate(sqlProduto_code);
            String sqlEditora = "CREATE TABLE DISCOUNT_CODE("
                    + "DISCOUNT_CODE CHAR(1) PRIMARY KEY NOT NULL,"
                    + "RATE DECIMAL(4,2))";
            sta.executeUpdate(sqlEditora);
        }
    }

    public static Connection conectarBd() throws Exception {
        return criarDataSource().getConnection();
    }
}
