package com.ast.furnishly.repositories.impl;

import com.ast.furnishly.entities.Furniture;
import com.ast.furnishly.entities.Manufacturer;
import com.ast.furnishly.exceptions.NotFoundException;
import com.ast.furnishly.mappers.ManufacturerMapper;
import com.ast.furnishly.mappers.TypeMapper;
import com.ast.furnishly.mappers.impl.ManufacturerMapperImpl;
import com.ast.furnishly.mappers.impl.TypeMapperImpl;
import com.ast.furnishly.repositories.ConnectionManager;
import com.ast.furnishly.repositories.FurnitureRepository;
import com.ast.furnishly.services.ManufacturerService;
import com.ast.furnishly.services.TypeService;
import com.ast.furnishly.services.impl.ManufacturerServiceImpl;
import com.ast.furnishly.services.impl.TypeServiceImpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Implementation of the {@link FurnitureRepository} interface for managing furniture data in a database.
 */
public class FurnitureRepositoryImpl implements FurnitureRepository {
    private static final String FIND_ALL = "SELECT * FROM furniture";
    private static final String FIND_BY_ID = "SELECT * FROM furniture WHERE id = ? LIMIT 1";
    private static final String SAVE = "INSERT INTO furniture (type_id, name, manufacturer_id, price, quantity) VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE furniture SET type_id = ?, name = ?, manufacturer_id = ?, price = ?, quantity = ? WHERE id = ?";
    private static final String DELETE = "DELETE FROM furniture WHERE id = ?";
    private static final String EXISTS_BY_ID = "SELECT 1 FROM furniture WHERE id = ? LIMIT 1";

    private static FurnitureRepository instance;
    private final ConnectionManager connectionManager = ConnectionManagerImpl.getConnectionManager();
    private final TypeService typeService = TypeServiceImpl.getInstance();
    private final TypeMapper typeMapper = TypeMapperImpl.getInstance();
    private final ManufacturerService manufacturerService = ManufacturerServiceImpl.getInstance();
    private final ManufacturerMapper manufacturerMapper = ManufacturerMapperImpl.getInstance();

    /**
     * Returns an instance of the `FurnitureRepositoryImpl`.
     *
     * @return The singleton instance of `FurnitureRepositoryImpl`.
     */
    public static FurnitureRepository getInstance() {
        if (instance == null) {
            instance = new FurnitureRepositoryImpl();
        }
        return instance;
    }

    @Override
    public Furniture save(Furniture furniture) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, furniture.getType().getId());
            preparedStatement.setString(2, furniture.getName());
            preparedStatement.setLong(3, furniture.getManufacturer().getId());
            preparedStatement.setBigDecimal(4, furniture.getPrice());
            preparedStatement.setInt(5, furniture.getQuantity());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                furniture = new Furniture(
                        resultSet.getLong("id"), furniture.getType(),
                        furniture.getName(), furniture.getManufacturer(),
                        furniture.getPrice(), furniture.getQuantity()
                );
            }
        } catch (SQLException e) {
            throw new NotFoundException("Furniture throws SQLException");
        }
        return furniture;
    }

    @Override
    public void update(Furniture furniture) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);) {
            preparedStatement.setLong(1, furniture.getType().getId());
            preparedStatement.setString(2, furniture.getName());
            preparedStatement.setLong(3, furniture.getManufacturer().getId());
            preparedStatement.setBigDecimal(4, furniture.getPrice());
            preparedStatement.setInt(5, furniture.getQuantity());
            preparedStatement.setLong(6, furniture.getId());
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
            throw new NotFoundException("Furniture with id " + id + " throws SQLException");
        }
        return deleteResult;
    }

    @Override
    public Optional<Furniture> findById(Long id) {
        Furniture furniture = null;
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                furniture = createFurniture(resultSet);
            }
        } catch (SQLException e) {
            throw new NotFoundException("Furniture with id " + id + " throws SQLException");
        }
        return Optional.ofNullable(furniture);
    }

    @Override
    public List<Furniture> findAll() {
        List<Furniture> furnitureList = new ArrayList<>();
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                furnitureList.add(createFurniture(resultSet));
            }
        } catch (SQLException e) {
            throw new NotFoundException("Furniture not found.");
        }
        return furnitureList;
    }

    @Override
    public boolean existsById(Long id) {
        boolean isExists = false;
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(EXISTS_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                isExists = resultSet.getBoolean(1);
            }
        } catch (SQLException e) {
            throw new NotFoundException("Furniture with id " + id + " throws SQLException");
        }
        return isExists;
    }

    /**
     * Creates a `Furniture` object from the given `ResultSet`.
     *
     * @param resultSet The result set containing furniture data.
     * @return A populated `Furniture` object.
     * @throws SQLException If an error occurs while retrieving data from the result set.
     */
    private Furniture createFurniture(ResultSet resultSet) throws SQLException {
        Furniture furniture = new Furniture();
        furniture.setId(resultSet.getLong("id"));
        furniture.setType(typeMapper.map(typeService.findById(resultSet.getLong("type_id"))));
        furniture.setName(resultSet.getString("name"));
        furniture.setManufacturer(manufacturerMapper.map(manufacturerService.findById(resultSet.getLong("manufacturer_id"))));
        furniture.setPrice(resultSet.getBigDecimal("price"));
        furniture.setQuantity(resultSet.getInt("quantity"));
        return furniture;
    }
}
