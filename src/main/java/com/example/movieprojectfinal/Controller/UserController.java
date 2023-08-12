package com.example.movieprojectfinal.Controller;


import com.example.movieprojectfinal.domain.User;
import com.example.movieprojectfinal.repository.UserRepository;
import com.example.movieprojectfinal.service.UserService;
import jakarta.servlet.http.HttpSession;
import java.util.Optional;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    private final UserService userService;


    private final UserRepository userRepository;

    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @GetMapping({"/users/new"})
    public String createform() {
        return "registerpage";
    }

    @PostMapping({"/users/new"})
    public String createuser(UserForm form) {
        if (form.getEmail() != null && !form.getEmail().isEmpty() && form.getPassword() != null && !form.getPassword().isEmpty()) {
            User user = new User();
            user.setUserName(form.getUserName());
            user.setEmail(form.getEmail());
            user.setPassword(form.getPassword());
            user.setPhoneNumber(form.getPhoneNumber());
            this.userService.register(user);
            return "redirect:/";
        } else {
            return "redirect:/users/new?error=true/emaill or pw null";
        }
    }

    @GetMapping({"/users/login"})
    public String loginform() {
        return "loginpage";
    }

    @PostMapping({"/users/login"})
    public String loginUser(UserForm form, HttpSession session) {
        Optional<User> userOptional = this.userRepository.findByEmail(form.getEmail());
        if (userOptional.isPresent() && ((User)userOptional.get()).getPassword().equals(form.getPassword())) {
            session.setAttribute("userId", ((User)userOptional.get()).getId());
            return "redirect:/users/moviepage/" + ((User)userOptional.get()).getId();
        } else {
            return "redirect:/users/login?error=true";
        }
    }
}

