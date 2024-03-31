package epicodeCapstone.portfolio.controllers;

import epicodeCapstone.portfolio.entities.Post;
import epicodeCapstone.portfolio.entities.PostContent;
import epicodeCapstone.portfolio.entities.User;
import epicodeCapstone.portfolio.payloads.PostDTO;
import epicodeCapstone.portfolio.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/post")
public class PostController {
    @Autowired
    private PostService postService;


    @GetMapping
    public Page<Post> getPosts(@RequestParam(defaultValue = "0") int page,
                               @RequestParam(defaultValue = "10") int size,
                               @RequestParam(defaultValue = "id") String sort){
        return postService.getAllPosts(page, size, sort);
    }

    @GetMapping("/{id}")
    public Post getPostById(@PathVariable UUID id){return postService.getPostById(id);}

    @GetMapping("/user/{userInfoId}")
    public List<Post> getPostsByUserInfoId(@PathVariable UUID userInfoId) {
        return postService.findAllByUserInfoId(userInfoId);
    }
    @GetMapping("/me")
    public List<Post> getPostByInfoId(@AuthenticationPrincipal User user){return postService.getPostByInfo(user.getUserInfo());}

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Post create(@RequestBody @Validated PostDTO payload, @AuthenticationPrincipal User user){
        return postService.savePost(payload, user);

    }

    @PostMapping("/{id}")
    public Post updateById(@PathVariable UUID id, @RequestBody PostDTO payload){
        return postService.updatePostById(id,payload);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable UUID id){ postService.deletePostById(id);}

    @PostMapping("/upload")
    public Map<String, String> uploadImage(@RequestParam("img") MultipartFile img, @RequestParam("id") UUID id) throws IOException {
        String mainImageUrl = this.postService.uploadImg(img, id);
        Map<String, String> resp = new HashMap<>();
        resp.put("imageUrl", mainImageUrl);
        return resp;
    }
//    @PostMapping("/upload")
//    public Map<String, String> uploadAvatar(@RequestParam("img") MultipartFile img, @AuthenticationPrincipal User user) throws IOException {
//        String imageUrl = this.userInfoService.uploadImg(img, user);
//        Map<String, String> response = new HashMap<>();
//        response.put("imageUrl", imageUrl);
//        return response;
//    }



}
