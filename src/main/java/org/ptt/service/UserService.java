package org.ptt.service;

import org.ptt.persistence.entity.User;
import org.ptt.persistence.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> listUsers() {
        return userRepository.findAll();
    }

    public void saveUser(HttpServletRequest request) {

        String country = request.getParameter("country");
        String email = request.getParameter("email");
        String name = request.getParameter("name");

        User user = User.builder()
                .name(name)
                .email(email)
                .country(country)
                .build();

        userRepository.save(user);
    }

    public User getUser(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found-id: " + id));
    }
}
