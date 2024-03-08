package epicodeCapstone.portfolio.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import epicodeCapstone.portfolio.entities.PostContent;
import epicodeCapstone.portfolio.exceptions.NotFoundException;
import epicodeCapstone.portfolio.payloads.PostContentDTO;
import epicodeCapstone.portfolio.repositories.PostContentDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
public class PostContentService {

    @Autowired
    PostContentDAO postContentDAO;

    @Autowired
    Cloudinary cloudinary;

  public Page<PostContent> getAllPostContent(int page, int size, String orderBy){
      Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy));
      return postContentDAO.findAll(pageable);
  }

  public PostContent getPostContentById(UUID id){
      return postContentDAO.findById(id).orElseThrow(()->new NotFoundException(id));

  }

  public PostContent savePostContent(PostContentDTO payload){
      PostContent postContent = new PostContent();
      postContent.setTitle(payload.title());
      postContent.setContent(payload.content());
      postContent.setPost(payload.post());
      return postContentDAO.save(postContent);
  }


  public String uploadImg(MultipartFile img, UUID id) throws IOException{
      PostContent found = this.getPostContentById(id);
      String url = (String) cloudinary.uploader().upload(img.getBytes(), ObjectUtils.emptyMap()).get("url");
      found.setImage(url);
      postContentDAO.save(found);
      return url;
  }

  public PostContent findByIdAndUpdate(UUID id, PostContentDTO payload){
      PostContent found = this.getPostContentById(id);
      found.setTitle(payload.title());
      found.setContent(payload.content());
      found.setPost(payload.post());
     return postContentDAO.save(found);
  }

  public void deleteById(UUID id){
      PostContent found = this.getPostContentById(id);
      postContentDAO.delete(found);
  }



}
