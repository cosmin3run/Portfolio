package epicodeCapstone.portfolio.controllers;

import epicodeCapstone.portfolio.entities.User;
import epicodeCapstone.portfolio.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/me")
    public User getUserById(@AuthenticationPrincipal User user ){return userService.findById(user.getId());}
}
