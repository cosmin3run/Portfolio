package epicodeCapstone.portfolio.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import epicodeCapstone.portfolio.entities.UserInfo;
import epicodeCapstone.portfolio.enums.Role;
import epicodeCapstone.portfolio.exceptions.NotFoundException;
import epicodeCapstone.portfolio.payloads.UserInfoDTO;
import epicodeCapstone.portfolio.repositories.UserInfoDAO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
        newInfo.setUser(payload.user());
        newInfo.setDescriptionBody(payload.descriptionBody());
        newInfo.setDescriptionTitle(payload.descriptionTitle());
        newInfo.getUser().setRole(Role.USER);
        return userInfoDAO.save(newInfo);
    }

    public UserInfo findUserByIdAndUpdate(UUID id, UserInfoDTO payload){
        UserInfo found = this.findById(id);
        found.setName(payload.name());
        found.setSurname(payload.surname());
       found.setUser(payload.user());
        found.setDescriptionBody(payload.descriptionBody());
        found.setDescriptionTitle(payload.descriptionTitle());
        found.getUser().setRole(Role.USER);
        return userInfoDAO.save(found);
    }

    public void findByIdAndDelete(UUID id){
        UserInfo found = findById(id);
        this.userInfoDAO.delete(found);
    }

    public String uploadImg(MultipartFile img, UUID id) throws IOException {
        UserInfo found = findById(id);
        String url = (String) cloudinary.uploader().upload(img.getBytes(), ObjectUtils.emptyMap()).get("url");
        found.setAvatar(url);
        userInfoDAO.save(found);
        return url;
    }


}
