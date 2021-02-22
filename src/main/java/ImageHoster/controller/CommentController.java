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

    /*
    POST request to add new comment to the image. Any user can post a comment for an image
    A user can post any number of comment for an image.
    The method receives the id of an image, the image ittle, the comment of the image
    along with the session and model object.
    The method uses the image service to get the image details and comment service to safestore the comment.
     */
    @RequestMapping(value = "/image/{imageId}/{imageTitle}/comments", method = RequestMethod.POST)
    public String newComment(@PathVariable("imageId") Integer id, @PathVariable("imageTitle") String title,
                             @RequestParam("comment") String comment, HttpSession session, Model model) {
        Comment newComment = new Comment();
        User loggeduser = (User) session.getAttribute("loggeduser");
        if (loggeduser == null) {
            // Looks like the session is gone
            //redirect to the login page
            return "redirect:/";
        }
        Image image = imageService.getImage(id);
        newComment.setUser(loggeduser);
        newComment.setImage(image);
        newComment.setText(comment);
        newComment.setCreatedDate(LocalDate.now());
        newComment = commentService.createComment(newComment);
        model.addAttribute("comments", newComment);
        return "redirect:/images/" + id + "/" + title;
    }
}
