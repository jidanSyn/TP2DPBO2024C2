import java.sql.*;

public class Database {
    private Connection connection;
    private Statement statement;

    public Database() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_mahasiswa", "dpbo", "pass");
            statement = connection.createStatement();
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ResultSet selectQuery(String sql) {
        try {
            statement.executeQuery(sql);
            return statement.getResultSet();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int insertUpdateDeleteQuery(String sql) {
        try {
            return statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean recordExists(String tableName, String attributeName, String attributeValue) throws SQLException {
        String sql = "SELECT COUNT(*) FROM " + tableName + " WHERE " + attributeName + " = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, attributeValue);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count > 0;
                }
            }
        }
        return false;
    }
}
