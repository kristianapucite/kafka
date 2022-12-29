package com.example.mappers;

import com.example.models.UserModel;
import com.example.repositories.UserDAO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserModel toModel(UserDAO dao);
    UserDAO toDAO(UserModel model);

    List<UserModel> toModelList(List<UserDAO> daos);
    List<UserDAO> toDAOList(List<UserModel> models);

}
