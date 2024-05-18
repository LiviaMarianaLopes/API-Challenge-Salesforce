package fiap.ddd.Infrastructure;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInitialization {

    public static void initialize() {
        // SQL para criar a tabela "users"
        String createTableSQL =
                "CREATE TABLE USERS (" +
                        "id NUMBER(12) GENERATED ALWAYS AS IDENTITY PRIMARY KEY," +
                        "nome VARCHAR2(255) NOT NULL," +
                        "email VARCHAR2(255) NOT NULL UNIQUE," +
                        "senha VARCHAR2(255) NOT NULL," +
                        "telefone VARCHAR2(255) NOT NULL," +
                        "empresa VARCHAR2(255) NOT NULL" +
                        ")";

        try (Connection conn = DatabaseConfig.getConnection();
             Statement stmt = conn.createStatement()) {

            // Criar a tabela
            stmt.execute(createTableSQL);
            System.out.println("Tabela 'users' criada com sucesso!");

        } catch (Exception e) {
            System.out.println("Erro ao criar a tabela: " + e.getMessage());
        }
    }

    public static void dropTable() {
        try (Connection conn = DatabaseConfig.getConnection();
             Statement stmt = conn.createStatement()
        ) {
            String sql = "DROP TABLE USERS";
            stmt.executeUpdate(sql);
            System.out.println("Tabela 'users' deletada com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        initialize();
    }
}