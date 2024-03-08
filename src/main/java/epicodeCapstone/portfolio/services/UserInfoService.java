package epicodeCapstone.portfolio.services;

import com.cloudinary.Cloudinary;
import epicodeCapstone.portfolio.entities.UserInfo;
import epicodeCapstone.portfolio.exceptions.NotFoundException;
import epicodeCapstone.portfolio.payloads.UserInfoDTO;
import epicodeCapstone.portfolio.repositories.UserInfoDAO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserInfoService {

    @Autowired
    private UserInfoDAO userInfoDAO;

    @Autowired
    Cloudinary cloudinary;

    public UserInfo findById(UUID id){return userInfoDAO.findById(id).orElseThrow(()->new NotFoundException(id));}

    public UserInfo saveUserInfo(UserInfoDTO payload){
        UserInfo newInfo = new UserInfo();
        newInfo.setName(payload.name());
        newInfo.setSurname(payload.surname());
        newInfo.setAvatar(payload.avatar());
        newInfo.setUser(payload.user());
        newInfo.setDescriptionBody(payload.descriptionBody());
        newInfo.setDescriptionTitle(payload.descriptionTitle());
        return userInfoDAO.save(newInfo);
    }

    public UserInfo findUserByIdAndUpdate(UUID id, UserInfoDTO payload){
        UserInfo found = this.findById(id);
        found.setName(payload.name());
        found.setSurname(payload.surname());
        found.setAvatar(payload.avatar());
       found.setUser(payload.user());
        found.setDescriptionBody(payload.descriptionBody());
        found.setDescriptionTitle(payload.descriptionTitle());
        return userInfoDAO.save(found);
    }

    public void findByIdAndDelete(UUID id){
        UserInfo found = findById(id);
        this.userInfoDAO.delete(found);
    }



}
