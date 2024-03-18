package epicodeCapstone.portfolio.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import epicodeCapstone.portfolio.entities.User;
import epicodeCapstone.portfolio.entities.UserInfo;
import epicodeCapstone.portfolio.enums.Role;
import epicodeCapstone.portfolio.exceptions.NotFoundException;
import epicodeCapstone.portfolio.payloads.UserInfoDTO;
import epicodeCapstone.portfolio.payloads.response.UserInfoResponseDTO;
import epicodeCapstone.portfolio.repositories.UserDAO;
import epicodeCapstone.portfolio.repositories.UserInfoDAO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserInfoService {

    @Autowired
    private UserInfoDAO userInfoDAO;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    Cloudinary cloudinary;

    public UserInfo findById(UUID id){return userInfoDAO.findById(id).orElseThrow(()->new NotFoundException(id));}

    public UserInfoResponseDTO saveUserInfo(UserInfoDTO payload, User user ){

        Optional<UserInfo> existsUserInfo = userInfoDAO.findByUser(user);
        if (existsUserInfo.isPresent()){
            UserInfo userInfo = existsUserInfo.get();
            userInfo.setName(payload.name());
            userInfo.setSurname(payload.surname());
            userInfo.setDescriptionBody(payload.descriptionTitle());
            userInfo.setDescriptionTitle(payload.descriptionBody());
            userInfoDAO.save(userInfo);
            return new UserInfoResponseDTO(userInfo.getId(), userInfo.getName(),
                    userInfo.getSurname(), userInfo.getDescriptionTitle(),
                    userInfo.getDescriptionBody());
        } else {



        User changeRole = userDAO.findById(user.getId()).get();
        changeRole.setRole(Role.USER);
        UserInfo newInfo = new UserInfo(changeRole);

        newInfo.setName(payload.name());
        newInfo.setSurname(payload.surname());
        newInfo.setDescriptionBody(payload.descriptionBody());
        newInfo.setDescriptionTitle(payload.descriptionTitle());
         userInfoDAO.save(newInfo);
        return new UserInfoResponseDTO(newInfo.getId(), newInfo.getName(), newInfo.getSurname(), newInfo.getDescriptionTitle(), newInfo.getDescriptionBody());
    }}

    public UserInfo findUserByIdAndUpdate(UUID id, UserInfoDTO payload){
        UserInfo found = this.findById(id);
        found.setName(payload.name());
        found.setSurname(payload.surname());
        found.setDescriptionBody(payload.descriptionBody());
        found.setDescriptionTitle(payload.descriptionTitle());
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
