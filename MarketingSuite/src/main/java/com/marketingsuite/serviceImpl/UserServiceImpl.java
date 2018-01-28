package com.marketingsuite.serviceImpl;

import com.marketingsuite.entities.User;
import com.marketingsuite.errors.Errors;
import com.marketingsuite.models.RegistrationModel;
import com.marketingsuite.repositories.UserRepository;
import com.marketingsuite.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(ModelMapper modelMapper, BCryptPasswordEncoder bCryptPasswordEncoder, UserRepository userRepository) {
        this.modelMapper = modelMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public void register(RegistrationModel registrationModel) {
        User user = this.modelMapper.map(registrationModel, User.class);
        String encryptedPassword = this.bCryptPasswordEncoder.encode(registrationModel.getPassword());
        user.setPassword(encryptedPassword);
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setEnabled(true);
        user.setCredentialsNonExpired(true);
        this.userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findOneByUsername(username);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findOneByEmail(email);
    }

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.findOneByUsername(username);
        if (user == null) {
            user = this.userRepository.findOneByEmail(username);
            if (user==null){
                throw  new UsernameNotFoundException(Errors.INVALID_CREDENTIALS);
            }
        }
        return user;
    }
}
