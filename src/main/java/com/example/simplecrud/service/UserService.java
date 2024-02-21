package com.example.simplecrud.service;

import com.example.simplecrud.dto.UserDTO;
import com.example.simplecrud.entity.User;
import com.example.simplecrud.repo.UserRepo;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ModelMapper modelMapper;
    public UserDTO saveUser(UserDTO userDTO) {
        userRepo.save(modelMapper.map(userDTO, User.class));
        return userDTO;
    }
    public List<UserDTO> getAllUsers() {
        List<User>userList = userRepo.findAll();
        return modelMapper.map(userList, new TypeToken<List<UserDTO>>(){}.getType());
    }

    public UserDTO updateUser(UserDTO userDTO) {
        userRepo.save(modelMapper.map(userDTO,User.class));
        return userDTO;
    }

    public boolean deleteUser(UserDTO userDTO) {
        userRepo.delete(modelMapper.map(userDTO, User.class));
        return true;
    }

    //pass user id from frontend and get that user's all details
    //select * from user where id = x
    public UserDTO getUserByUserID(String userID) {
        User user = userRepo.getUserByUserID(userID);
        return modelMapper.map(user, UserDTO.class);
    }

    //pass user id and the address from frontend and grt that user's all details
    //select * from user where id = x and address =  y
    public UserDTO getUserByUserIDAndAddress(String userId,String address) {
        User user = userRepo.getUserByUserIDAndAddress(userId,address);
        return modelMapper.map(user, UserDTO.class);
    }
}
