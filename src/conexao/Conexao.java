package conexao;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Conexao {
    private static Properties carregaPropriedades(){
        Properties props = new Properties();

        try (FileInputStream fis = new FileInputStream("database.properties")){
            props.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao carregar o arquivo de propriedades: " + e.getMessage());
        }
        return props;
    }

    public static Connection getConnection()throws SQLException {
        Properties props = carregaPropriedades();
        String url = props.getProperty("db.url");
        String user = props.getProperty("db.user");
        String pswd = props.getProperty("db.pswd");

        if (url == null || user == null || pswd == null) {
            throw new SQLException("As propriedades 'db.url', 'db.user' e 'db.password' devem ser definidas em database.properties.");
        }

        return DriverManager.getConnection(url, user, pswd);
    }
}
