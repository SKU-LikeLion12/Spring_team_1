package likelion.helloworld.repository;

import jakarta.persistence.EntityManager;
import likelion.helloworld.domain.Article;
import likelion.helloworld.domain.Comment;
import likelion.helloworld.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class JpaCommentRepository implements CommentRepository {

    private final EntityManager em;
    private final MemberRepository memberRepository;


    @Override
    public Comment findById(Long id) {
        return em.find(Comment.class, id);
    }

    @Override
    public void saveComment(Comment comment) {
        em.persist(comment);
    }

    @Override
    public void deleteComment(Comment comment) {
        em.remove(comment);
    }


    //게시글에 달린 댓글 목록 조회
    @Override
    public List<Comment> findArticleComment(Article article) {
        return em.createQuery("select c from Comment c where c.article = :article", Comment.class)
                .setParameter("article", article).getResultList();
    }

    //특정 멤버가 작성한 댓글 목록 조회
    @Override
    public List<Comment> findMemberComment(Member member) {
        return em.createQuery("select c from Comment c where c.writer=  :memberId", Comment.class)
                .setParameter("memberId", member.getId()).getResultList();
    }


    //특정 멤버가 작성한 적이 있는 게시글 목록 조회
    @Override
    public List<Article> findMemberCommentArticle(Member member) {
        // db의 테이블은 java로 돌아올 때 객체로 변환되서 리턴된다.
        return em.createQuery("select distinct c.article from Comment c where c.writer=  :memberId", Article.class)
                .setParameter("memberId", member.getId()).getResultList();
    }
}
