package com.quizz.app.service.impl;

import com.quizz.app.dto.UserDto;
import com.quizz.app.entity.RoleEnum;
import com.quizz.app.entity.User;
import com.quizz.app.mapper.UserMapper;
import com.quizz.app.repository.UserRepository;
import com.quizz.app.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
            if(user.getPassword().equalsIgnoreCase(userDto.getPassword())){
                return true;
            }
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

//        SecureRandom random = new SecureRandom();
//        byte[] salt = new byte[16];
//        random.nextBytes(salt);
//        String encodedSalt = Base64.getEncoder().encodeToString(salt);
//        String encodedPassword = hashService.getHashedValue(user.getPassword(),encodedSalt);
//        user.setPassword(encodedPassword);
//        return userMapper.insert(User.builder()
//                .firstName(user.getFirstName())
//                .lastName(user.getLastName())
//                .userId(null)
//                .username(user.getUsername())
//                .password(encodedPassword)
//                .salt(encodedSalt)
//                .build());
    }
}
