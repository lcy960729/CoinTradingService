package com.cy.tradingbot.domain.user.service;

import com.cy.tradingbot.domain.user.User;
import com.cy.tradingbot.dto.user.RequestUserDTO;
import com.cy.tradingbot.dto.user.UserDTO;
import com.cy.tradingbot.exception.NotFoundEntityException;
import com.cy.tradingbot.mapper.UserMapper;
import com.cy.tradingbot.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(RuntimeException::new);
    }

    public void join(RequestUserDTO requestUserDTO) {
        User user = User.builder()
                .username(requestUserDTO.getUserName())
                .password(requestUserDTO.getPassword())
                .authority("ROLE_USER")
                .build();

        user = userRepository.save(user);
    }

    private boolean passwordValidate(String requestPassword, String userPassword) {
        return bCryptPasswordEncoder.matches(requestPassword, userPassword);
    }

    public UserDTO validate(RequestUserDTO requestUserDTO) {
        User user = userRepository.findByUsername(requestUserDTO.getUserName()).orElseThrow(NotFoundEntityException::new);

        if (!passwordValidate(requestUserDTO.getPassword(), user.getPassword()))
            throw new RuntimeException();

        return userMapper.toDTO(user);
    }

    public UserDTO get(long userId) {
        User user = userRepository.findById(userId).orElseThrow(NotFoundEntityException::new);

        return userMapper.toDTO(user);
    }

    public UserDTO get(User user) {
        return userMapper.toDTO(user);
    }

    public void updateCredential(long userId, String secretKey, String accessKey) {
        User user = userRepository.findById(userId).orElseThrow(NotFoundEntityException::new);
        user.setSecretKey(secretKey);
        user.setAccessKey(accessKey);
    }
}
