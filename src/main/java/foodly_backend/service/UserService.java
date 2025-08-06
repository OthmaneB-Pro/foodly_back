package foodly_backend.service;

import foodly_backend.entity.UserEntity;
import foodly_backend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserEntity> getUser() {
        return this.userRepository.findAll();
    }

    public void createUser(UserEntity user) {
        this.userRepository.save(user);
    }
}
