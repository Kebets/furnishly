package com.ast.furnishly.repositories.impl;

import com.ast.furnishly.entities.Type;
import com.ast.furnishly.repositories.ConnectionManager;
import com.ast.furnishly.repositories.TypeRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TypeRepositoryImpl implements TypeRepository {
    private static String FIND_ALL_TYPES = "SELECT * FROM type";

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
        return null;
    }

    @Override
    public void update(Type type) {

    }

    @Override
    public boolean deleteById(Long id) {
        return false;
    }

    @Override
    public Optional<Type> findById(Long id) {
        return Optional.empty();
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
        return false;
    }

    private Type createType(ResultSet resultSet) throws SQLException {
        Type type = new Type();
        type.setId(resultSet.getLong("id"));
        type.setName(resultSet.getString("name"));
        return type;
    }
}
