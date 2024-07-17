package com.ast.furnishly.repositories.impl;

import com.ast.furnishly.entities.Furniture;
import com.ast.furnishly.repositories.ConnectionManager;
import com.ast.furnishly.repositories.FurnitureRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FurnitureRepositoryImpl implements FurnitureRepository {
    private static final String FIND_ALL_FURNITURES = "SELECT * FROM furniture";

    private static FurnitureRepository instance;
    private final ConnectionManager connectionManager = ConnectionManagerImpl.getConnectionManager();

    public static FurnitureRepository getInstance() {
        if (instance == null) {
            instance = new FurnitureRepositoryImpl();
        }
        return instance;
    }

    @Override
    public Furniture save(Furniture furniture) {
        return null;
    }

    @Override
    public void update(Furniture furniture) {

    }

    @Override
    public boolean deleteById(Long id) {
        return false;
    }

    @Override
    public Optional<Furniture> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Furniture> findAll() {
        List<Furniture> furnitureList = new ArrayList<>();
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_FURNITURES)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                furnitureList.add(createFurniture(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();;//TODO add logging
        }
        return furnitureList;
    }

    @Override
    public boolean existsById(Long id) {
        return false;
    }

    private Furniture createFurniture(ResultSet resultSet) throws SQLException {
        Furniture furniture = new Furniture();
        furniture.setId(resultSet.getLong("id"));
        furniture.setName(resultSet.getString("name"));
        return furniture;
    }
}
