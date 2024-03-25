package epicodeCapstone.portfolio.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import epicodeCapstone.portfolio.entities.Post;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    public UserInfo findByUser(User user){return userInfoDAO.findByUser(user).orElseThrow(()->new NotFoundException(user.getId()));}
    public UserInfo findById(UUID id){return userInfoDAO.findById(id).orElseThrow(()->new NotFoundException(id));}
    public Page<UserInfo> getAllUserInfo(int page, int size, String orderBy){
        Pageable pageable = PageRequest.of(page,size, Sort.by(orderBy));
        return userInfoDAO.findAll(pageable);
    }

    public UserInfoResponseDTO saveUserInfo(UserInfoDTO payload, User user ){

        Optional<UserInfo> existsUserInfo = userInfoDAO.findByUser(user);
        if (existsUserInfo.isPresent()){
            UserInfo userInfo = existsUserInfo.get();
            userInfo.setName(payload.name());
            userInfo.setSurname(payload.surname());
            userInfo.setLinkedin(payload.linkedin());
            userInfo.setGithub(payload.github());
            userInfo.setInstagram(payload.instagram());
            userInfo.setDescriptionBody(payload.descriptionBody());
            userInfo.setDescriptionTitle(payload.descriptionTitle());

            userInfoDAO.save(userInfo);
            return new UserInfoResponseDTO(userInfo.getId(), userInfo.getName(),
                    userInfo.getSurname(), userInfo.getLinkedin(), userInfo.getGithub(), userInfo.getInstagram(), userInfo.getDescriptionTitle(),
                    userInfo.getDescriptionBody());
        } else {



        User changeRole = userDAO.findById(user.getId()).get();
        changeRole.setRole(Role.USER);
        UserInfo newInfo = new UserInfo(changeRole);

        newInfo.setAvatar("https://ui-avatars.com/api/?name=" + newInfo.getName().charAt(0) + newInfo.getSurname().charAt(0));
        newInfo.setName(payload.name());
        newInfo.setSurname(payload.surname());
        newInfo.setDescriptionBody(payload.descriptionBody());
        newInfo.setDescriptionTitle(payload.descriptionTitle());
         userInfoDAO.save(newInfo);
        return new UserInfoResponseDTO(newInfo.getId(), newInfo.getName(), newInfo.getSurname(), newInfo.getLinkedin(), newInfo.getGithub(),newInfo.getInstagram(), newInfo.getDescriptionTitle(), newInfo.getDescriptionBody());
    }}

    public UserInfo findUserByIdAndUpdate(UUID id, UserInfoDTO payload){
        UserInfo found = this.findById(id);
        found.setName(payload.name());
        found.setSurname(payload.surname());
        found.setDescriptionBody(payload.descriptionBody());
        found.setDescriptionTitle(payload.descriptionTitle());
        found.setAvatar("https://ui-avatars.com/api/?name=" + found.getName().charAt(0) + found.getSurname().charAt(0));
        return userInfoDAO.save(found);
    }

    public void findByIdAndDelete(UUID id){
        UserInfo found = findById(id);
        this.userInfoDAO.delete(found);
    }

    public String uploadImg(MultipartFile img, User user) throws IOException {
        UserInfo found = findById(user.getUserInfo().getId());
        String url = (String) cloudinary.uploader().upload(img.getBytes(), ObjectUtils.emptyMap()).get("url");
        found.setAvatar(url);
        userInfoDAO.save(found);
        System.out.println(found.getAvatar());
        return url;
    }


}
