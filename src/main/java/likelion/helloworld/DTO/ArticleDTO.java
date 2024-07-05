package likelion.helloworld.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import likelion.helloworld.domain.Article;
import lombok.Data;

import java.time.LocalDateTime;



public class ArticleDTO {

    @Data
    public static class ResponseArticle {
        private String title;
        private String content;
        private String writer;
        private LocalDateTime createDate;
        private boolean isChange;
        private long favorite;
        private String isFavorite;

        public ResponseArticle(Article article, long favoriteCount){
            this.title = article.getTitle();
            this.content = article.getContent();
            this.writer = article.getWriter().getNickName();
            this.createDate = article.getCreateDate();
            this.favorite = favoriteCount;
            this.isFavorite = "로그인을 하세요!";

            if(article.getCreateDate().equals(article.getUpdatedDate())){
                this.isChange = false;
            }else{
                this.isChange = true;
            }
        }
        public ResponseArticle(Article article, long favoriteCount, boolean isFavorite){
            this(article, favoriteCount);
            System.out.println(isFavorite + " 입니다.!!!" );
            if (isFavorite){
                this.isFavorite = "좋아요 불가능";
            }else{
                this.isFavorite = "좋아요 가능";
            }
        }
    }



    @Data
    public static class RequestArticle {
        @Schema(description = "닉네임", example = "스프링 공부중 .....")
        private String title;
        @Schema(description = "닉네임", example = "JPA 너무 어렵다")
        private String content;
        @Schema(description = "닉네임", example = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiLsp4TsmIEiLCJpYXQiOjE3MjAwNzAyNzEsImV4cCI6MTcyMDA3Mzg3MX0.IJ59b1DTJvGDJVuKZR3YNzL2ebu4lJSFH5P37_2KKBm2kQlfnL_cupewVEvCn7W8in3BLwN_Szt0SccNDC-zRg")
        private String token;
    }

    @Data
    public static class RemoveArticle{
        private String token;
    }

    @Data
    public static class tokenArticle{
        private String token;
    }
}
