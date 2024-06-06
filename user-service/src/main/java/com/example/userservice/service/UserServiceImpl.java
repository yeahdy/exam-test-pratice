package com.example.userservice.service;

import com.example.userservice.client.OrderServiceClient;
import com.example.userservice.common.response.ResponseMessage;
import com.example.userservice.dto.UserDto;
import com.example.userservice.jpa.UserEntity;
import com.example.userservice.repository.UserRepository;
import com.example.userservice.vo.ResponseOrder;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
//    private final OrderService orderService;
    private final OrderServiceClient orderServiceClient;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 일반적으로 사용자의 id 를 통해 pw 정보를 가져온 후, authentication에서 pw 인증을 한다.
        UserEntity userEntity = userRepository.findByEmail(username);
        if (userEntity == null) {
            throw new UsernameNotFoundException(username);
        }
        return new User(userEntity.getEmail(), userEntity.getEncryptedPw(),
                true, true, true, true, new ArrayList<>());
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        userDto.setKeyInfo(UUID.randomUUID().toString(), bCryptPasswordEncoder.encode(userDto.getPassword()));

        UserEntity userEntity = modelMapper.map(userDto, UserEntity.class);
        userRepository.save(userEntity);
        return modelMapper.map(userEntity, UserDto.class);
    }

    @Override
    public UserDto getUserByUserId(String userId) {
        UserEntity userEntity = userRepository.findByUserId(userId);
        if (userEntity == null) {
            throw new NullPointerException("Not found user");
        }
        UserDto userDto = modelMapper.map(userEntity, UserDto.class);
        ResponseMessage<List<ResponseOrder>> responseMessage = orderServiceClient.getOrders(userId);
        userDto.setOrderList(responseMessage.getData());
        return userDto;
    }

    @Override
    public List<UserDto> getUserByAll() {
        Iterable<UserEntity> userEntities = userRepository.findAll();
        List<UserDto> userDtos = new ArrayList<>();
        userEntities.forEach(
                userEntity -> userDtos.add(modelMapper.map(userEntity, UserDto.class))
        );
        return userDtos;
    }

    @Override
    public UserDto getUserDetailsByEmail(String email) {
        UserEntity userEntity = userRepository.findByEmail(email);
        if (userEntity == null) {
            throw new UsernameNotFoundException(email);
        }
        UserDto userDto = modelMapper.map(userEntity, UserDto.class);
        return userDto;
    }

}
