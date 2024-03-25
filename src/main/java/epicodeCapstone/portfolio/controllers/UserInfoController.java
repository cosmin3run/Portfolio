package epicodeCapstone.portfolio.controllers;

import epicodeCapstone.portfolio.entities.PostContent;
import epicodeCapstone.portfolio.entities.User;
import epicodeCapstone.portfolio.entities.UserInfo;
import epicodeCapstone.portfolio.payloads.UserInfoDTO;
import epicodeCapstone.portfolio.payloads.response.UserInfoResponseDTO;
import epicodeCapstone.portfolio.services.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
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
        return userInfoService.findByUser(user);
    }

    @GetMapping
    public Page<UserInfo> getAllUserInfo(@RequestParam(defaultValue = "0") int page,
                                               @RequestParam(defaultValue = "10") int size,
                                               @RequestParam(defaultValue = "id") String sort){
        return userInfoService.getAllUserInfo(page,size,sort);

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
    public Map<String, String> uploadAvatar(@RequestParam("img") MultipartFile img, @AuthenticationPrincipal User user) throws IOException {
        String imageUrl = this.userInfoService.uploadImg(img, user);
        Map<String, String> response = new HashMap<>();
        response.put("imageUrl", imageUrl);
        return response;
    }
}
