package epicodeCapstone.portfolio.controllers;

import epicodeCapstone.portfolio.entities.UserInfo;
import epicodeCapstone.portfolio.payloads.UserInfoDTO;
import epicodeCapstone.portfolio.services.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserInfo saveUserInfo(@RequestBody UserInfoDTO newInfo){
        return this.userInfoService.saveUserInfo(newInfo);
    }

    @PutMapping("/{id}")
    public UserInfo findByIdAndUpdate(@PathVariable UUID id, @RequestBody UserInfoDTO payload){
        return this.userInfoService.findUserByIdAndUpdate(id, payload);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable UUID id){this.userInfoService.findByIdAndDelete(id);}

    @PostMapping("/upload")
    public String uploadAvatar(MultipartFile img, @RequestParam("id") UUID id) throws IOException {
        return this.userInfoService.uploadImg(img, id);
    }
}
