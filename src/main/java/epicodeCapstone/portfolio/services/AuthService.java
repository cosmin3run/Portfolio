package epicodeCapstone.portfolio.services;

import epicodeCapstone.portfolio.entities.User;
import epicodeCapstone.portfolio.enums.Role;
import epicodeCapstone.portfolio.exceptions.AlreadyExistsException;
import epicodeCapstone.portfolio.exceptions.UnauthorizedException;
import epicodeCapstone.portfolio.payloads.UserDTO;
import epicodeCapstone.portfolio.payloads.UserLoginDTO;
import epicodeCapstone.portfolio.repositories.UserDAO;
import epicodeCapstone.portfolio.security.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private JWTTools jwtTools;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    UserService userService;
    @Autowired
    private PasswordEncoder bcrypt;

    public User save(UserDTO payload){
        if(userDAO.existsByUsername(payload.username()) ){
            throw new AlreadyExistsException("Username già assegnato");
        } else if (userDAO.existsByEmail(payload.email())){
            throw new AlreadyExistsException("Email già registrata");
        } else {
        User newUser = new User(payload.username(),payload.email(),bcrypt.encode(payload.password()), Role.USER);
        return userDAO.save(newUser);
        }
    }

    public String authenticateUserAndGenerateToken(UserLoginDTO payload) {
        User user = userService.findByEmail(payload.email());
        if (bcrypt.matches(payload.password(), user.getPassword())) {
            return jwtTools.createToken(user);
        } else {
            throw new UnauthorizedException("Credenziali sbagliate!");
        }
    }
}
