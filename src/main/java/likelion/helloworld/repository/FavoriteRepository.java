package likelion.helloworld.repository;

import likelion.helloworld.domain.Article;
import likelion.helloworld.domain.Favorite;
import likelion.helloworld.domain.Member;

import java.util.List;

public interface FavoriteRepository {
    public Favorite createFavorite( Member member, Article article );

    public Favorite deleteFavorite( Member member, Article article );

    public boolean findFavorite(Member member, Article article );

    public long findArticleFavorite(Article article);


}
