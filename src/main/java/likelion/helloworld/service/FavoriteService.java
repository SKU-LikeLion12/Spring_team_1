package likelion.helloworld.service;


import likelion.helloworld.domain.Article;
import likelion.helloworld.domain.Favorite;
import likelion.helloworld.domain.Member;
import likelion.helloworld.repository.ArticleRepository;
import likelion.helloworld.repository.FavoriteRepository;
import likelion.helloworld.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FavoriteService {

    private final FavoriteRepository favoriteRepository;
    private final MemberRepository memberRepository;
    private final ArticleRepository articleRepository;
    private final MemberService memberService;


    @Transactional
    public Favorite createFavorite( String userId, Long articleId ) {
        Member member = memberRepository.findByUserId(userId);
        Article article = articleRepository.findById(articleId);
        if (member == null || article == null) {
            return null;
        }
        boolean isFavorite = favoriteRepository.findFavorite(member, article);
        if (isFavorite){
            return null;
        }else{
            favoriteRepository.createFavorite(member, article);
            return new Favorite(member, article);
        }
    }


    @Transactional
    public Favorite deleteFavorite( String userId, Long articleId ) {
        Member member = memberRepository.findByUserId(userId);
        Article article = articleRepository.findById(articleId);
        if (member == null || article == null) {
            return null;
        }
        boolean isFavorite = favoriteRepository.findFavorite(member, article);
        if (!isFavorite){
            return null;
        }else{
            favoriteRepository.deleteFavorite(member, article);
        }
        return null;
    }

    public long findArticleFavorite( long articleId ) {
        Article article = articleRepository.findById(articleId);
        long favoriteCounter = favoriteRepository.findArticleFavorite(article);
        return favoriteCounter;
    }


    public boolean findMemberFavorite( String token , long articleId ) {
        Member member = memberService.tokenToMember(token);
        Article article = articleRepository.findById(articleId);
        boolean isFavorite = favoriteRepository.findFavorite(member, article);
        return isFavorite;
    }

    public List<Favorite> memberToFavorite(String token) {
        Member member = memberService.tokenToMember(token);
        return favoriteRepository.memberToFavorite(member);
    }

}
