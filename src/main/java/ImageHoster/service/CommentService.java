package ImageHoster.service;

import ImageHoster.model.Comment;
import ImageHoster.model.Image;
import ImageHoster.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    CommentRepository commentRepository;

    public Comment createComment(Comment comment) {
        return commentRepository.createComment(comment);
    }

    public List<Comment> getCommentsByImageid(Integer imageId) {
        return commentRepository.getCommentsByImageid(imageId);
    }

}
