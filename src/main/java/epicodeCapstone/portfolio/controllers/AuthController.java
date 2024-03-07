package epicodeCapstone.portfolio.controllers;

import epicodeCapstone.portfolio.entities.User;
import epicodeCapstone.portfolio.payloads.LoginResponseDTO;
import epicodeCapstone.portfolio.payloads.UserDTO;
import epicodeCapstone.portfolio.payloads.UserLoginDTO;
import epicodeCapstone.portfolio.services.AuthService;
import epicodeCapstone.portfolio.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody UserLoginDTO payload){
        return  new LoginResponseDTO(authService.authenticateUserAndGenerateToken(payload));
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public User saveUser(@RequestBody UserDTO payload){return this.authService.save(payload);}
}
