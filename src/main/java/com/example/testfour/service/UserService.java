package com.example.testfour.service;

import com.example.testfour.dao.UserRepository;
import com.example.testfour.domain.Role;
import com.example.testfour.domain.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final SessionService sessionService;

    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       SessionService sessionService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.sessionService = sessionService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        user.setLoginDate(new Date());
        if (Boolean.TRUE.equals(user.getNotBlocked())){
            user.setStatus("Online");
        }
        save(user);
        return user;
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public void create(String username, String password, String email) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setEmail(email);
        user.setCreationDate(new Date());
        user.setLoginDate(new Date());
        user.setStatus("Offline");
        user.setRole(Role.USER);
        user.setNotBlocked(true);
        save(user);
    }

    public void delete(Long id) {
        User user = findById(id);
        sessionService.expireUserSessions(user.getUsername());
        userRepository.delete(user);
    }

    public void block(Long id) {
        User user = findById(id);
        user.setNotBlocked(false);
        user.setStatus("Offline");
        userRepository.save(user);
        sessionService.expireUserSessions(user.getUsername());
    }

    public void unblock(Long id) {
        User user = findById(id);
        user.setNotBlocked(true);
        userRepository.save(user);
    }
}
