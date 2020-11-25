package com.sap.salesmanagement.service.impl;

import com.google.gson.Gson;
import com.sap.salesmanagement.enumerate.RoleType;
import com.sap.salesmanagement.domain.entity.UserEntity;
import com.sap.salesmanagement.domain.model.service.UserServiceModel;
import com.sap.salesmanagement.exception.InvalidUserException;
import com.sap.salesmanagement.repository.UserRepository;
import com.sap.salesmanagement.service.UserService;
import org.hibernate.exception.ConstraintViolationException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, Gson gson) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
    }

    @Override
    public UserServiceModel addUser(UserServiceModel user) throws InvalidUserException {
        try {
        UserEntity entity = this.modelMapper.map(user, UserEntity.class);
        entity.setRole(RoleType.SALES_REPRESENTATIVE);

        return this.modelMapper.map(this.userRepository.save(entity), UserServiceModel.class);
        } catch (DataIntegrityViolationException | ConstraintViolationException e) {
            throw new InvalidUserException("Could not save user " + this.gson.toJson(user), e);
        }
    }
}
