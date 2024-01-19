package dal;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.Connection;

public class DatabaseConnection {

    private SQLServerDataSource sqlServerDataSource;

    public DatabaseConnection() {
        sqlServerDataSource = new SQLServerDataSource();
        sqlServerDataSource.setDatabaseName("CSe2023b_e_28_PrivateMovie1");
        sqlServerDataSource.setUser("CSe2023b_e_28");
        sqlServerDataSource.setPassword("CSe2023bE28#23");
        sqlServerDataSource.setServerName("EASV-DB4");
        sqlServerDataSource.setTrustServerCertificate(true);
    }

    public Connection getConnection() throws SQLServerException {
        return sqlServerDataSource.getConnection();
    }
}
