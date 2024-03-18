package epicodeCapstone.portfolio.controllers;

import epicodeCapstone.portfolio.entities.User;
import epicodeCapstone.portfolio.entities.UserInfo;
import epicodeCapstone.portfolio.payloads.UserInfoDTO;
import epicodeCapstone.portfolio.payloads.response.UserInfoResponseDTO;
import epicodeCapstone.portfolio.services.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/userInfo")
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    @GetMapping("/{userInfoId}")
    public UserInfo getById(@PathVariable UUID userInfoId){
        return userInfoService.findById(userInfoId);
    }

    @GetMapping("/me")
    public UserInfo getUserInfoMe(@AuthenticationPrincipal User user){
        return userInfoService.findById(user.getId());
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserInfoResponseDTO saveUserInfo(@RequestBody UserInfoDTO newInfo, @AuthenticationPrincipal User user){
        return this.userInfoService.saveUserInfo(newInfo, user);
    }

    @PutMapping("/{id}")
    public UserInfo findByIdAndUpdate(@PathVariable UUID id, @RequestBody UserInfoDTO payload){
        return this.userInfoService.findUserByIdAndUpdate(id, payload);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable UUID id){this.userInfoService.findByIdAndDelete(id);}

    @PostMapping("/upload")
    public String uploadAvatar(MultipartFile img, @RequestParam("id") UUID id) throws IOException {
        return this.userInfoService.uploadImg(img, id);
    }
}
