package likelion.helloworld.DTO;

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


        public ResponseArticle(Article article){
            this.title = article.getTitle();
            this.content = article.getContent();
            this.writer = article.getWriter().getNickName();
            this.createDate = article.getCreateDate();

            if(article.getCreateDate().equals(article.getUpdatedDate())){
                this.isChange = false;
            }else{
                this.isChange = true;
            }
        }
    }


    @Data
    public static class RequestArticle {
        private String title;
        private String content;
        private String token;
    }

    @Data
    public static class RemoveArticle{
        private String token;
    }

}
