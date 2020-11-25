package com.sap.salesmanagement.service;

import com.sap.salesmanagement.domain.model.service.UserServiceModel;
import com.sap.salesmanagement.exception.InvalidUserException;

public interface UserService {
    UserServiceModel addUser(UserServiceModel user) throws InvalidUserException;
}
