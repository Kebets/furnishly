package com.ast.furnishly.repositories.impl;

import com.ast.furnishly.entities.Manufacturer;
import com.ast.furnishly.repositories.ConnectionManager;
import com.ast.furnishly.repositories.ManufacturerRepository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ManufacturerRepositoryImpl implements ManufacturerRepository {
    private static final String FIND_ALL_MANUFACTURERS = "SELECT * FROM manufacturer";

    private static ManufacturerRepository instance;
    private final ConnectionManager connectionManager = ConnectionManagerImpl.getConnectionManager();

    public static ManufacturerRepository getInstance() {
        if (instance == null) {
            instance = new ManufacturerRepositoryImpl();
        }
        return instance;
    }

    @Override
    public Manufacturer save(Manufacturer manufacturer) {
        return null;
    }

    @Override
    public void update(Manufacturer manufacturer) {

    }

    @Override
    public boolean deleteById(Long id) {
        return false;
    }

    @Override
    public Optional<Manufacturer> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Manufacturer> findAll() {
        List<Manufacturer> manufacturers = new ArrayList<>();
        try(Connection connection = connectionManager.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(FIND_ALL_MANUFACTURERS);
            while(resultSet.next()){
                manufacturers.add(createManufacturer(resultSet));
            }
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return manufacturers;
    }

    @Override
    public boolean existsById(Long id) {
        return false;
    }

    public Manufacturer createManufacturer(ResultSet resultSet) throws SQLException {
        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setId(resultSet.getLong(1));
        manufacturer.setName(resultSet.getString(2));
        return manufacturer;
    }
}
