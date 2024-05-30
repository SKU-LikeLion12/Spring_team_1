package likelion.helloworld.controller;

import likelion.helloworld.DTO.CommentDTO;
import likelion.helloworld.domain.Comment;
import likelion.helloworld.service.CommentService;
import likelion.helloworld.service.JwtUtility;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    private final JwtUtility jwtUtility;



    @PostMapping("/comment")
    public CommentDTO.CommentResponse createComment(@RequestBody CommentDTO.CommentCreateRequest request) {
        Comment comment = commentService.saveComment(request.getToken(), request.getArticle_id(), request.getContent());
        return new CommentDTO.CommentResponse(comment);
    }

    @PutMapping("/comment")
    public CommentDTO.CommentResponse updateComment(@RequestBody CommentDTO.CommentUpdateRequest request) {
        Comment comment = commentService.updateComment(request.getToken(), request.getCommentId(), request.getContent());
        return new CommentDTO.CommentResponse(comment);
    }


    @DeleteMapping("/comment")
    public void deleteComment(@RequestBody CommentDTO.CommentDeleteRequest request) {
        commentService.deleteComment(request.getCommentId(), request.getToken());
    }

    @GetMapping("/comment/article/{id}")
    public List<CommentDTO.CommentResponse> getCommentsByArticle(@PathVariable("id") Long article_id) {
        List<CommentDTO.CommentResponse> responseList = new ArrayList<>();
        for( Comment comment : commentService.articleToComment(article_id)){
            responseList.add(new CommentDTO.CommentResponse(comment));
        }
        return responseList;
    }

}
