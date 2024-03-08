package epicodeCapstone.portfolio.services;

import com.cloudinary.Cloudinary;
import epicodeCapstone.portfolio.entities.Post;
import epicodeCapstone.portfolio.exceptions.NotFoundException;
import epicodeCapstone.portfolio.payloads.PostDTO;
import epicodeCapstone.portfolio.repositories.PostDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PostService {
    @Autowired
    private PostDAO postDAO;

    @Autowired
    Cloudinary cloudinary;

    public Post findById(UUID id){return postDAO.findById(id).orElseThrow(()->new NotFoundException(id));}

    public Page<Post> getAllPosts(int page, int size, String orderBy){
        Pageable pageable = PageRequest.of(page,size, Sort.by(orderBy));
        return postDAO.findAll(pageable);
    }

    public Post getPostById(UUID id){
        return postDAO.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public Post savePost(PostDTO payload){
        Post newPost = new Post();
        newPost.setTitle(payload.title());
        newPost.setPublicationDate(payload.publicationDate());
        return postDAO.save(newPost);
    }

    public Post updatePostById(UUID id, PostDTO payload){
        Post found = getPostById(id);
        found.setTitle(payload.title());
        found.setPublicationDate(payload.publicationDate());
        return postDAO.save(found);
    }

    public void deletePostById(UUID id){
        Post found = getPostById(id);
        postDAO.delete(found);
    }


}
