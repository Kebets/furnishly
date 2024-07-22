package com.ast.furnishly.services.impl;

import com.ast.furnishly.dto.TypeDto;
import com.ast.furnishly.entities.Type;
import com.ast.furnishly.exceptions.NotFoundException;
import com.ast.furnishly.mappers.TypeMapper;
import com.ast.furnishly.mappers.impl.TypeMapperImpl;
import com.ast.furnishly.repositories.TypeRepository;
import com.ast.furnishly.repositories.impl.TypeRepositoryImpl;
import com.ast.furnishly.services.TypeService;

import java.util.List;

/**
 * Implementation of the TypeService interface.
 */
public class TypeServiceImpl implements TypeService {
    private TypeRepository typeRepository = TypeRepositoryImpl.getInstance();
    private final TypeMapper typeMapper = TypeMapperImpl.getInstance();
    private static TypeService typeService;

    private TypeServiceImpl() {}

    /**
     * Gets an instance of the TypeService.
     *
     * @return The TypeService instance.
     */
    public static synchronized TypeService getInstance(){
        if (typeService == null){
            typeService = new TypeServiceImpl();
        }
        return typeService;
    }

    @Override
    public TypeDto findById(Long id) {
        Type type = typeRepository.findById(id).orElseThrow(() -> new NotFoundException("Type not found."));
        return typeMapper.map(type);
    }

    @Override
    public List<TypeDto> findAll() {
        List<Type> typeList = typeRepository.findAll();
        return typeMapper.map(typeList);
    }

    @Override
    public boolean delete(Long id) throws NotFoundException {
        checkTypeExist(id);
        return typeRepository.deleteById(id);
    }

    private void checkTypeExist(Long id) throws NotFoundException {
        if (!typeRepository.existsById(id)) {
            throw new NotFoundException("Type not found.");
        }
    }

    @Override
    public TypeDto save(TypeDto typeDto) {
        Type type = typeMapper.map(typeDto);
        type = typeRepository.save(type);
        return typeMapper.map(type);
    }

    @Override
    public void update(TypeDto typeDto) throws NotFoundException {
        checkTypeExist(typeDto.getId());
        Type type = typeMapper.map(typeDto);
        typeRepository.update(type);
    }

}
