package likelion.helloworld.DTO;


import likelion.helloworld.domain.Article;
import likelion.helloworld.domain.Comment;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class CommentDTO {

    @Data
    public static class CommentResponse {

        private String writer;
        private String writer_id;
        private String content;
        private LocalDateTime createDate;
        private boolean isUpdate;


        public CommentResponse(Comment comment){
            this.writer = comment.getWriter().getNickName();
            this.writer_id = comment.getWriter().getUserID();
            this.content = comment.getContent();
            if(comment.getCreatedDate().equals(comment.getUpdatedDate())){
                this.isUpdate = false;
            }else{
                this.isUpdate = true;
            }
        }

    }

    @Data
    public static class CommentCreateRequest{
        private Long article_id;
        private String content;
        private String token;
    }

    @Data
    public static class CommentUpdateRequest{
        private String token;
        private Long commentId;
        private String content;
    }

    @Data
    public static class CommentDeleteRequest{
        private String token;
        private Long commentId;
    }

}
