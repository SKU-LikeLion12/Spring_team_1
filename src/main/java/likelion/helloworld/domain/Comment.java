package likelion.helloworld.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
public class Comment {

    @Id @GeneratedValue
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "writer_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Member writer;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Article article;

    @Column(columnDefinition = "TEXT")
    private String content;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    public Comment(Member writer, Article article, String content) {
        this.writer = writer;
        this.article = article;
        this.content = content;
        this.createdDate = LocalDateTime.now();
        this.updatedDate = this.updatedDate;
    }

    public void update(String content) { // 댓글의 경우 기존 글은 변경되지 않음
        this.content = content;
        this.updatedDate = LocalDateTime.now();
    }

}
