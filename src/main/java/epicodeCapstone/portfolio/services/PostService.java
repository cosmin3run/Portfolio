package epicodeCapstone.portfolio.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import epicodeCapstone.portfolio.entities.Post;
import epicodeCapstone.portfolio.entities.PostContent;
import epicodeCapstone.portfolio.entities.User;
import epicodeCapstone.portfolio.entities.UserInfo;
import epicodeCapstone.portfolio.exceptions.NotFoundException;
import epicodeCapstone.portfolio.payloads.PostDTO;
import epicodeCapstone.portfolio.repositories.PostDAO;
import epicodeCapstone.portfolio.repositories.UserInfoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class PostService {
    @Autowired
    private PostDAO postDAO;

    @Autowired
    private UserInfoDAO userInfoDAO;

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

    public List<Post> findAllByUserInfoId(UUID userInfoId) {
        return postDAO.findAllByUserInfoId(userInfoId);
    }
    public List<Post> getPostByInfo(UserInfo userInfo){
        return postDAO.findByUserInfo(userInfo);
    }

    public Post savePost(PostDTO payload, User user){
        Post newPost = new Post();
        newPost.setTitle(payload.title());
        newPost.setPublicationDate(payload.publicationDate());
        newPost.setUserInfo(user.getUserInfo());
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
        String publicId = found.getImageId();
        if (publicId != null) {
            try {
                // Cancella l'immagine da Cloudinary utilizzando il publicId
                cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
            } catch (IOException e) {
                // Gestisci l'eccezione in modo opportuno, ad esempio stampando un messaggio di errore
                System.err.println("Errore durante la cancellazione dell'immagine da Cloudinary: " + e.getMessage());
            }
        }
        postDAO.delete(found);
    }

    public Map<?,?> uploadImg(MultipartFile img, UUID id) throws IOException {
        Post found = findById(id);
        Map<?,?> uploadResult = cloudinary.uploader().upload(img.getBytes(), ObjectUtils.emptyMap());
        String url = (String) uploadResult.get("url");
        String publicId = (String) uploadResult.get("public_id");
        System.out.println("Id immagine ="+publicId);

        found.setMainImg(url);
        found.setImageId(publicId);
        postDAO.save(found);
//        Map<String, String> uploaded = new HashMap<>();
//        uploaded.put("url", url);
//        uploaded.put("publicId", publicId);
        return uploadResult;
    }
}
