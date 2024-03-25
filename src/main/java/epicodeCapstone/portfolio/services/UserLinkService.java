//package epicodeCapstone.portfolio.services;
//
//import com.cloudinary.Cloudinary;
//import com.cloudinary.utils.ObjectUtils;
//import epicodeCapstone.portfolio.entities.User;
//import epicodeCapstone.portfolio.entities.UserInfo;
//import epicodeCapstone.portfolio.entities.UserLinks;
//import epicodeCapstone.portfolio.exceptions.NotFoundException;
//import epicodeCapstone.portfolio.payloads.UserLinksDTO;
//import epicodeCapstone.portfolio.repositories.UserLinkDAO;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//import java.util.List;
//import java.util.UUID;
//
//@Service
//public class UserLinkService {
//
//    @Autowired
//    private UserLinkDAO userLinkDAO;
//    @Autowired
//    Cloudinary cloudinary;
//
//    public UserLinks findById(UUID id) {return userLinkDAO.findById(id).orElseThrow(() -> new NotFoundException(id));}
//
//    public List<UserLinks> getUserLinksByUserInfo(UserInfo userInfo){return userLinkDAO.findAllByUserInfo(userInfo);}
//
//    public UserLinks saveUserLinks(UserLinksDTO payload){
//        UserLinks newUser = new UserLinks();
//        newUser.setTitle(payload.title());
//        newUser.setUrl(payload.url());
//        newUser.setImageUrl(payload.imageUrl());
////        newUser.setUserInfo(payload.userInfo());
//        return userLinkDAO.save(newUser);
//    }
//
//    public void findByIdAndDelete(UUID id){
//        UserLinks found = this.findById(id);
//        userLinkDAO.delete(found);
//    }
//
//    public UserLinks findByIdAndUpdate(UUID id, UserLinksDTO payload){
//        UserLinks found = this.findById(id);
//        found.setTitle(payload.title());
//        found.setUrl(payload.url());
//        found.setImageUrl((payload.imageUrl()));
////        found.setUserInfo(payload.userInfo());
//        return userLinkDAO.save(found);
//    }
//
//    public String uploadImg(MultipartFile img, UUID id) throws IOException {
//        UserLinks found = this.findById(id);
//        String url = (String) cloudinary.uploader().upload(img.getBytes(), ObjectUtils.emptyMap()).get("url");
//        found.setImageUrl(url);
//        userLinkDAO.save(found);
//        return url;
//    }
//
//    }
