package epicodeCapstone.portfolio.controllers;

import epicodeCapstone.portfolio.entities.User;
import epicodeCapstone.portfolio.entities.UserInfo;
import epicodeCapstone.portfolio.entities.UserLinks;
import epicodeCapstone.portfolio.payloads.UserLinksDTO;
import epicodeCapstone.portfolio.services.UserLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/userLink")
public class UserLinkController {

    @Autowired
    private UserLinkService userLinkService;

    @GetMapping
    public List<UserLinks> getUserLinksByUserInfo(UserInfo userInfo){return this.userLinkService.getUserLinksByUserInfo(userInfo);}

    @GetMapping("/{userId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    public UserLinks findById(@PathVariable UUID userLink){
        return userLinkService.findById(userLink);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserLinks saveUserLink(@RequestBody UserLinksDTO newUserLink){
        return this.userLinkService.saveUserLinks(newUserLink);
    }

    @PostMapping("/id")
    public UserLinks findByIdAndUpdate(@PathVariable UUID id, @RequestBody UserLinksDTO linkToUpdate){
        return this.userLinkService.findByIdAndUpdate(id, linkToUpdate);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public  void findByIdAndDelete(@PathVariable UUID userLinkId){this.userLinkService.findByIdAndDelete(userLinkId);}

}
