package epicodeCapstone.portfolio.services;

import com.cloudinary.Cloudinary;
import epicodeCapstone.portfolio.entities.User;
import epicodeCapstone.portfolio.enums.Role;
import epicodeCapstone.portfolio.exceptions.NotFoundException;
import epicodeCapstone.portfolio.payloads.UserDTO;
import epicodeCapstone.portfolio.repositories.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserDAO userDAO;

    @Autowired
    Cloudinary cloudinary;

    public User findById(UUID id) {return userDAO.findById(id).orElseThrow(() -> new NotFoundException(id));}

    public User findByEmail(String email){return userDAO.findByEmail(email).orElseThrow(()-> new NotFoundException("Email '"+email+"' non trovata."));}

    public User findByUsername(String username){return userDAO.findByUsername(username).orElseThrow(() -> new NotFoundException("Username '"+ username + "' non trovato."));}

    public List<User> getUsers(){return userDAO.findAll();}

    public void findByIdAndDelete(UUID id){
        User found = this.findById(id);
        userDAO.save(found);
    }

    public User findByIdAndUpdate(UUID id, UserDTO payload){
        User found = this.findById(id);
        found.setUsername(payload.username());
        found.setEmail(payload.email());
        found.setPassword(payload.password());
        return userDAO.save(found);
    }

}
