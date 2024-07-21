package com.ast.furnishly.repositories.impl;

import com.ast.furnishly.entities.Manufacturer;
import com.ast.furnishly.entities.Type;
import com.ast.furnishly.exceptions.NotFoundException;
import com.ast.furnishly.repositories.ConnectionManager;
import com.ast.furnishly.repositories.ManufacturerRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ManufacturerRepositoryImpl implements ManufacturerRepository {
    private static final String FIND_ALL_MANUFACTURERS = "SELECT * FROM manufacturer";
    private static final String FIND_BY_ID = "SELECT * FROM manufacturer WHERE id = ? LIMIT 1";
    private static final String SAVE = "INSERT INTO manufacturer (name, address, country) VALUES (?, ?, ?)";
    private static final String UPDATE = "UPDATE manufacturer SET name = ?, address = ?, country = ? WHERE id = ?";
    private static final String DELETE = "DELETE FROM manufacturer WHERE id = ?";
    private static final String EXIST_BY_ID = "SELECT 1 FROM manufacturer WHERE id = ? LIMIT 1";

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
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, manufacturer.getName());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                manufacturer = new Manufacturer(
                        resultSet.getLong("id"), manufacturer.getName(),
                        manufacturer.getAddress(), manufacturer.getCountry()
                );
            }
        } catch (SQLException e) {
            throw new NotFoundException("Manufacturer throws SQLException");
        }
        return manufacturer;
    }

    @Override
    public void update(Manufacturer manufacturer) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);) {
            preparedStatement.setString(1, manufacturer.getName());
            preparedStatement.setString(2, manufacturer.getAddress());
            preparedStatement.setString(3, manufacturer.getCountry());
            preparedStatement.setLong(4, manufacturer.getId());
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
            throw new NotFoundException("Manufacturer with id " + id + " throws SQLException");
        }
        return deleteResult;
    }

    @Override
    public Optional<Manufacturer> findById(Long id) {
        Manufacturer manufacturer = null;
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                manufacturer = createManufacturer(resultSet);
            }
        } catch (SQLException e) {
            throw new NotFoundException("Manufacturer with id " + id + " throws SQLException");
        }
        return Optional.ofNullable(manufacturer);
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
        boolean isExists = false;
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(EXIST_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                isExists = resultSet.getBoolean(1);
            }
        } catch (SQLException e) {
            throw new NotFoundException("Manufacturer with id " + id + " throws SQLException");
        }
        return isExists;
    }

    public Manufacturer createManufacturer(ResultSet resultSet) throws SQLException {
        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setId(resultSet.getLong(1));
        manufacturer.setName(resultSet.getString(2));
        manufacturer.setAddress(resultSet.getString(3));
        manufacturer.setCountry(resultSet.getString(4));
        return manufacturer;
    }
}
