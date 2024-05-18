package fiap.ddd.Repositories;

import fiap.ddd.Entities.Login;
import fiap.ddd.Infrastructure.DatabaseConfig;
import fiap.ddd.Entities.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepositoryOrcl implements _BaseRepository<User> {
    DatabaseConfig oracleDatabaseConnection;
    public static final String ID_COLUMN = "ID";
    public static final String NOME_COLUMN = "NOME";
    public static final String EMAIL_COLUMN = "EMAIL";
    public static final String SENHA_COLUMN = "SENHA";
    public static final String TELEFONE_COLUMN = "TELEFONE";
    public static final String EMPRESA_COLUMN = "EMPRESA";
    public static final String TB_NAME = "USERS";

    public boolean verificarLogin(Login login) {
        for (User l : readAll()) {

            //Verifica se o username inserido é igual ao nome de usuário ou email cadastrado
            if (l.getEmail().equals(login.getEmail())) {
                //Verifica se a senha inserida corresponde a senha cadastrada
                if (l.getSenha().equals(login.getSenha())) {
                    return true;

                }
            }

        }
        return false;
    }

    public User saberLogin(Login login) {
        for (User l : readAll()) {

            if (l.getEmail().equals(login.getEmail())) {
                if (l.getSenha().equals(login.getSenha())) {

                    //retorna o Entities.Login que corresponde ao username e senha inseridos
                    return l;

                }
            }

        }
        return null;
    }

    // Recupera um usuário pelo ID
    public Optional<User> getUserById(int id) {
        String selectSQL = "SELECT * FROM " + TB_NAME + " WHERE id = ?";
        User user = null;

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(selectSQL)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return Optional.of(new User(rs.getInt("id"), rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("senha"),
                        rs.getString("telefone"),
                        rs.getString("empresa")));
            }

        } catch (Exception e) {
            System.out.println("Erro ao obter usuário: " + e.getMessage());
        }

        return Optional.empty();
    }

    @Override
    public void create(User entity) {

        try (var connection = oracleDatabaseConnection.getConnection();

             var statement = connection.prepareStatement(
                     "INSERT INTO " + TB_NAME + " (%s, %s, %s, %s, %s) VALUES (?, ?, ?, ?, ?)"
                             .formatted(NOME_COLUMN, EMAIL_COLUMN, SENHA_COLUMN, TELEFONE_COLUMN, EMPRESA_COLUMN))) {

            statement.setString(1, entity.getNome());
            statement.setString(2, entity.getEmail());
            statement.setString(3, entity.getSenha());
            statement.setString(4, entity.getTelefone());
            statement.setString(5, entity.getEmpresa());
            var result = statement.executeUpdate();
            System.out.println(("Insert realizado com sucesso, linhas afetadas: " + result));
        } catch (SQLException e) {
            System.out.println(("Erro ao inserir tarefa: " + e.getMessage()));
        }

    }

    @Override
    public List<User> readAll() {
        List<User> userList = new ArrayList<>();
        String selectAllSQL = "SELECT * FROM " + TB_NAME + " ORDER BY ID";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(selectAllSQL)) {

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                User user = new User(rs.getInt("id"), rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("senha"),
                        rs.getString("telefone"),
                        rs.getString("empresa"));
                userList.add(user);
            }

        } catch (Exception e) {
            System.out.println("Erro ao listar usuários: " + e.getMessage());
        }
        return userList;
    }

    @Override
    public void update(int id, User entity) {
        String updateSQL = "UPDATE " + TB_NAME + " SET nome = ?, email = ?, senha = ?, telefone = ?, empresa = ?  WHERE id = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(updateSQL)) {

            pstmt.setString(1, entity.getNome());
            pstmt.setString(2, entity.getEmail());
            pstmt.setString(3, entity.getSenha());
            pstmt.setString(4, entity.getTelefone());
            pstmt.setString(5, entity.getEmpresa());
            pstmt.setInt(6, id);

            pstmt.executeUpdate();
            System.out.println("Usuário atualizado com sucesso!");

        } catch (Exception e) {
            System.out.println("Erro ao atualizar usuário: " + e.getMessage());
        }
    }


    @Override
    public void delete(int id) {
        String deleteSQL = "DELETE FROM " + TB_NAME + " WHERE id = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(deleteSQL)) {

            pstmt.setInt(1, id);

            pstmt.executeUpdate();
            System.out.println("Usuário excluído com sucesso!");

        } catch (Exception e) {
            System.out.println("Erro ao excluir usuário: " + e.getMessage());
        }
    }
}