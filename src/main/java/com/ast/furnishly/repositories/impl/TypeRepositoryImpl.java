package com.ast.furnishly.repositories.impl;

import com.ast.furnishly.entities.Type;
import com.ast.furnishly.exceptions.NotFoundException;
import com.ast.furnishly.repositories.ConnectionManager;
import com.ast.furnishly.repositories.TypeRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TypeRepositoryImpl implements TypeRepository {
    private static String FIND_ALL_TYPES = "SELECT * FROM type";
    private static final String FIND_BY_ID = "SELECT * FROM type WHERE id = ? LIMIT 1";
    private static final String SAVE = "INSERT INTO type (name) VALUES (?)";
    private static final String UPDATE = "UPDATE type SET name = ? WHERE id = ?";
    private static final String DELETE = "DELETE FROM type WHERE id = ?";
    private static final String EXIST_BY_ID = "SELECT 1 FROM type WHERE id = ? LIMIT 1";

    private static TypeRepository instance;
    private final ConnectionManager connectionManager = ConnectionManagerImpl.getConnectionManager();

    public static TypeRepository getInstance() {
        if (instance == null) {
            instance = new TypeRepositoryImpl();
        }
        return instance;
    }

    @Override
    public Type save(Type type) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, type.getName());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                type = new Type(
                        resultSet.getLong("id"),
                        type.getName());
            }
        } catch (SQLException e) {
            throw new NotFoundException(e.getMessage());
        }
        return type;
    }

    @Override
    public void update(Type type) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);) {
            preparedStatement.setString(1, type.getName());
            preparedStatement.setLong(2, type.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new NotFoundException("Type not found.");
        }
    }

    @Override
    public boolean deleteById(Long id) {
        boolean deleteResult;
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE);) {
            preparedStatement.setLong(1, id);
            deleteResult = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new NotFoundException("Type with id " + id + " throws SQLException");
        }

        return deleteResult;
    }

    @Override
    public Optional<Type> findById(Long id) {
        Type type = null;
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {

            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                type = createType(resultSet);
            }
        } catch (SQLException e) {
            throw new NotFoundException(e.getMessage());
        }
        return Optional.ofNullable(type);
    }

    @Override
    public List<Type> findAll() {
        List<Type> types = new ArrayList<>();
        try(Connection connection = connectionManager.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(FIND_ALL_TYPES);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                types.add(createType(resultSet));
            }
        }catch (SQLException e) {
            e.printStackTrace();//TODO add logging
        }
        return types;
    }

    @Override
    public boolean existsById(Long id) {
        boolean isExists = false;
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(EXIST_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                isExists = resultSet.getBoolean(1);
            }
        } catch (SQLException e) {
            throw new NotFoundException("Type with id " + id + " throws SQLException");
        }
        return isExists;
    }

    private Type createType(ResultSet resultSet) throws SQLException {
        Type type = new Type();
        type.setId(resultSet.getLong("id"));
        type.setName(resultSet.getString("name"));
        return type;
    }
}
