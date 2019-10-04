package co.edu.unal.software_engineering.labs.service;

import co.edu.unal.software_engineering.labs.model.Role;
import co.edu.unal.software_engineering.labs.model.User;
import co.edu.unal.software_engineering.labs.pojo.RegisterUserPOJO;
import co.edu.unal.software_engineering.labs.repository.RoleRepository;
import co.edu.unal.software_engineering.labs.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
private final RoleRepository roleRepository;
    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository=roleRepository;
    }

    public User findById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public boolean isRightUser(RegisterUserPOJO user) {
        boolean correctness = user.getNames() != null && user.getPassword() != null && user.getUsername() != null
                && user.getSurnames() != null;
        if (correctness) {
            correctness = !user.getNames().trim().isEmpty() && !user.getPassword().trim().isEmpty()
                    && !user.getUsername().trim().isEmpty() && !user.getSurnames().trim().isEmpty();
        }
        return correctness;
    }
    public boolean isTeacher(User user){
        Role teacher=this.roleRepository.getOne(2);
        return this.userRepository.findByUsername(user.getUsername()).getRoles().contains(teacher);
    }


}