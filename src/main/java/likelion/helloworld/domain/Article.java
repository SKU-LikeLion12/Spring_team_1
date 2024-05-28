package likelion.helloworld.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Article {

    @Id @GeneratedValue
    private long id; // pk

    @ManyToOne(fetch = FetchType.LAZY) // 참조하는 쪽에서 적용
    @JoinColumn(name ="writer_id") // fk
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Member writer; // 멤버 객체를 다 가지고 있기 때문에 테이블속성 name이랑 다르게 해야함

    private LocalDateTime createDate;
    private LocalDateTime updatedDate;
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    public Article(String title, String content, Member writer) {
        this.title = title;
        this.content = content;
        this.createDate = LocalDateTime.now();
        this.updatedDate = this.createDate;
        this.writer = writer;
    }


    public void update(String title, String content) {
        this.title = title;
        this.content = content;
        this.updatedDate = LocalDateTime.now();
    }

}
