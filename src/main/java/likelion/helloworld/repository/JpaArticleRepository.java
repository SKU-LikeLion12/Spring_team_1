package likelion.helloworld.repository;

import jakarta.persistence.EntityManager;
import likelion.helloworld.domain.Article;
import likelion.helloworld.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class JpaArticleRepository implements ArticleRepository{

    private final EntityManager em;
    private final MemberRepository memberRepository;


    @Override
    public Article saveNewArticle(Article article) {
        em.persist(article);
        return article;
    }

    @Override
    public void deleteArticle(Article article) {
        em.remove(article);
    }

    @Override
    public Article findById(Long articleId) {
        return em.find(Article.class, articleId);
    }

    @Override
    public List<Article> findAll() {
        return em.createQuery("select a from Article a", Article.class).getResultList();
    }

    @Override
    public List<Article> findUserAll(Long memberId) {
        Member member = memberRepository.findById(memberId); // writer가 Member 객체라 그냥 member로 한다.
        return em.createQuery("select a from Article a where a.writer = :m", Article.class)
                .setParameter("m", member).getResultList();

    }


}
