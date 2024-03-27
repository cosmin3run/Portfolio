package epicodeCapstone.portfolio.controllers;

import epicodeCapstone.portfolio.entities.PostContent;
import epicodeCapstone.portfolio.payloads.PostContentDTO;
import epicodeCapstone.portfolio.services.PostContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/postContent")
public class PostContentController {

@Autowired
private PostContentService postContentService;

    @GetMapping
    public Page<PostContent> getAllPostContent(@RequestParam(defaultValue = "0") int page,
                                               @RequestParam(defaultValue = "10") int size,
                                               @RequestParam(defaultValue = "id") String sort){
        return postContentService.getAllPostContent(page,size,sort);

    }

    @GetMapping("/{id}")
    public PostContent getPostContentById(@PathVariable UUID id){ return postContentService.getPostContentById(id);}

    @GetMapping("/post/{postId}")
    public List<PostContent> getPostContentByPostId(@PathVariable UUID postId) {
        return postContentService.findAllByPostId(postId);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PostContent create(@RequestBody @Validated PostContentDTO payload){
        return postContentService.savePostContent(payload);
    }

    @PostMapping("/{id}")
    public PostContent updateById(@PathVariable UUID id, @RequestBody PostContentDTO payload){
        return postContentService.findByIdAndUpdate(id, payload);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable UUID id){postContentService.deleteById(id);}

    @PostMapping("/upload")
    public Map<String, String> uploadAvatar(@RequestParam("img") MultipartFile img, @RequestParam("id") UUID id) throws IOException {
       String contentImg = this.postContentService.uploadImg(img, id);
       Map<String, String> resp = new HashMap<>();
       resp.put("contentImg", contentImg);
        return resp;
    }


   
}
