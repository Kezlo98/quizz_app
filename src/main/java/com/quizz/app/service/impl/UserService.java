package com.quizz.app.service.impl;

import com.quizz.app.dto.PasswordUpdateRequest;
import com.quizz.app.dto.UserDto;
import com.quizz.app.entity.RoleEnum;
import com.quizz.app.entity.User;
import com.quizz.app.mapper.UserMapper;
import com.quizz.app.repository.UserRepository;
import com.quizz.app.service.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean isUsernameValid(String userName){
        Optional<User> userOptional = userRepository.getUserByUsername(userName);
        return !userOptional.isPresent();
    }

    @Override
    public boolean isLoginSuccess(UserDto userDto){
        Optional<User> userOptional = userRepository.getUserByUsername(userDto.getUsername());
        if(userOptional.isPresent()){
            User user = userOptional.get();
            return user.getPassword().equalsIgnoreCase(userDto.getPassword());
        }

        return false;
    }

    @Override
    public User updatePasswordWithEmail(String email, String resetPassword){
        Optional<User> userOptional = userRepository.getUserByEmail(email);
        if(!userOptional.isPresent()){
            return null;
        }

        User user = userOptional.get();
        user.setPassword(resetPassword);
        return userRepository.save(user);
    }

    @Override
    public User createUser(UserDto userDto) {

        User user = UserMapper.INSTANCE.toEntity(userDto);
        user.setRole(RoleEnum.USER);
        return userRepository.save(user);
    }

    @Override
    public User getUserWithUserName(String userName){
        if(!StringUtils.hasLength(userName)){
            return null;
        }
        Optional<User> userOptional = userRepository.getUserByUsername(userName);
        return userOptional.orElse(null);
    }

    @Override
    public User updateUser(UserDto userDto){
        User user = getUserWithUserName(userDto.getUsername());
        if(user == null){
            return null;
        }

        passValueToEntity(user,userDto);
        userRepository.save(user);
        return user;
    }

    @Override
    public String changePassword(PasswordUpdateRequest request, String userName){
        User user = getUserWithUserName(userName);
        if(user == null){
            return "User not found";
        }

        if(!user.getPassword().equals(request.getCurrentPassword())){
            return "Current password is incorrect";
        }

        user.setPassword(request.getNewPassword());
        userRepository.save(user);

        return "Password is updated";
    }

    private void passValueToEntity(User user, UserDto userDto){
        if(StringUtils.hasLength(userDto.getFirstName())){
            user.setFirstName(userDto.getFirstName());
        }
        if(StringUtils.hasLength(userDto.getLastName())){
            user.setLastName(userDto.getLastName());
        }
        if(StringUtils.hasLength(userDto.getDescription())){
            user.setDescription(userDto.getDescription());
        }
        if(StringUtils.hasLength(userDto.getUniversityName())){
            user.setUniversityName(userDto.getUniversityName());
        }
    }
}
