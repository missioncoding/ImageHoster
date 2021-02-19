package ImageHoster.controller;

import ImageHoster.model.Comment;
import ImageHoster.model.Image;
import ImageHoster.model.User;
import ImageHoster.service.CommentService;
import ImageHoster.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import javax.xml.crypto.Data;
import java.time.LocalDate;
import java.util.Date;

@Controller
public class CommentController {

    @Autowired
    CommentService commentService;

    @Autowired
    ImageService imageService;

    @RequestMapping(value = "/image/{imageId}/{imageTitle}/comments", method = RequestMethod.POST)
    public String newComment(@PathVariable("imageId") Integer id, @PathVariable("imageTitle") String title,
                             @RequestParam("comment") String comment, HttpSession session, Model model) {
        Comment newComment = new Comment();
        User user = (User) session.getAttribute("loggeduser");
        Image image = imageService.getImage(id);
        newComment.setUser(user);
        newComment.setImage(image);
        newComment.setText(comment);
        newComment.setCreatedDate(LocalDate.now());
        newComment = commentService.createComment(newComment);

        model.addAttribute("comments", newComment);
        return "redirect:/images/" + id + "/" + title;


    }
}
