package com.example.testfour.config;

import com.example.testfour.dao.UserRepository;
import com.example.testfour.domain.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class CustomLogoutHandler implements LogoutHandler {

    private final UserRepository userRepository;

    public CustomLogoutHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        User user = userRepository.findByUsername(authentication.getName());
        if (user!=null){
            user.setStatus("Offline");
            userRepository.save(user);
        }
    }
}
