package likelion.helloworld.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import likelion.helloworld.domain.Article;
import likelion.helloworld.domain.Favorite;
import likelion.helloworld.domain.Member;
import lombok.RequiredArgsConstructor;
import org.hibernate.resource.beans.internal.FallbackBeanInstanceProducer;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class JpaFavoriteRepository implements FavoriteRepository{
    private final EntityManager em;

    @Override
    public Favorite createFavorite(Member member, Article article ) {
        Favorite favorite = new Favorite(member, article);
        em.persist(favorite);
        return favorite;
    }

    @Override
    public Favorite deleteFavorite(Member member, Article article ) {
        Favorite favorite = em.createQuery("select f from Favorite f where f.liker = :member and f.likeThing = :article", Favorite.class)
                .setParameter("member", member)
                .setParameter("article", article)
                .getSingleResult();
        em.remove(favorite);
        return favorite;
    }


    @Override
    public boolean findFavorite(Member member, Article article) {
        try {
            Favorite favorite = em.createQuery("select f from Favorite f where f.liker = :member and f.likeThing = :article", Favorite.class)
                    .setParameter("member", member)
                    .setParameter("article", article)
                    .getSingleResult();

            return true; // 쿼리 결과가 있으면 true 반환
        } catch (NoResultException e) {
            return false; // 쿼리 결과가 없으면 false 반환
        }
    }

    @Override
    public long findArticleFavorite(Article article) {
        Long count = em.createQuery("select count(f) from Favorite f where f.likeThing = :article", Long.class)
                .setParameter("article", article)
                .getSingleResult();

        if (count == null){
            return 0;
        }else{
           return count;
        }
    }



}
