package Persistencia;

import java.sql.Connection;
import java.sql.Statement;
import javax.sql.DataSource;
import org.apache.derby.jdbc.EmbeddedDataSource;

/**
 *
 * @author Júlio
 */
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
            String sql = "CREATE TABLE Contribuintes ("
                    + "NOME VARCHAR(100) NOT NULL,"
                    + "CPF VARCHAR(20) PRIMARY KEY,"
                    + "IDADE NUMERIC(3) NOT NULL,"
                    + "NRODEP NUMERIC(2) NOT NULL,"
                    + "CONTRPREV DECIMAL(10,2) NOT NULL,"
                    + "TOTREND DECIMAL(10,2) NOT NULL"
                    + ")";
            sta.executeUpdate(sql);
        }
    }

    public static Connection conectarBd() throws Exception {
        return criarDataSource().getConnection();
    }
}
