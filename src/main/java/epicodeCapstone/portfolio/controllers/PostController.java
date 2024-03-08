package epicodeCapstone.portfolio.controllers;

import epicodeCapstone.portfolio.entities.Post;
import epicodeCapstone.portfolio.payloads.PostDTO;
import epicodeCapstone.portfolio.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Post create(@RequestBody @Validated PostDTO payload){
        return postService.savePost(payload);

    }

    @PostMapping("/{id}")
    public Post updateById(@PathVariable UUID id, @RequestBody PostDTO payload){
        return postService.updatePostById(id,payload);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable UUID id){ postService.deletePostById(id);}

    @PostMapping("/upload")
    public String uploadImage(@RequestParam("img") MultipartFile img, @RequestParam("id") UUID id) throws IOException {
        return this.postService.uploadImg(img, id);
    }



}
