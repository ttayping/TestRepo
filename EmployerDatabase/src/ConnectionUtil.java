
import java.sql.*;

public class ConnectionUtil {
    private static final String URL = "jdbc:mysql://localhost:3306/university?useSSL=false";
    private static final String USER = "root";
    private static final String PASSWORD = "admin";

    private ConnectionUtil() {
    }

    public static Connection createConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void closeConnection(Connection connection) throws SQLException {
        connection.close();
    }

    public static void closeConnection(Connection connection,
                                       PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.close();
        connection.close();
    }
    public static void closeConnection(Connection connection,
                                       PreparedStatement preparedStatement, PreparedStatement preparedStatement2) throws SQLException {
        preparedStatement2.close();
        preparedStatement.close();
        connection.close();
    }

    public static void closeConnection(Connection connection,
                                       PreparedStatement preparedStatement,
                                       ResultSet resultSet) throws SQLException {
        resultSet.close();
        preparedStatement.close();
        connection.close();
    }
}
