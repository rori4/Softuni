package com.securitydemo.serviceImpl;

import com.securitydemo.config.Errors;
import com.securitydemo.entities.User;
import com.securitydemo.model.RegistrationModel;
import com.securitydemo.repositories.UserRepository;
import com.securitydemo.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(BCryptPasswordEncoder bCryptPasswordEncoder, UserRepository userRepository, ModelMapper modelMapper) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
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
    public void delete() {
        System.out.println("DELETE TOPIC");
    }

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.findOneByUsername(username);
        if (user == null){
            throw new UsernameNotFoundException(Errors.INVALID_CREDENTIALS);
        }
            return user;
    }
}
