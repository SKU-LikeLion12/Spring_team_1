package likelion.helloworld.service;


import likelion.helloworld.domain.Article;
import likelion.helloworld.domain.Member;
import likelion.helloworld.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final MemberService memberService;

    @Transactional
    public Article saveNewArticle(String writerId, String title, String content){ // 작성자 아이디, 제목, 글이름
        Member member = memberService.findByUserId(writerId); // 작성자의 개인 데이터 가져옴
        Article article = new Article(title, content , member ); // 글 데이터로 객체 만듬
        articleRepository.saveNewArticle(article);
        return article;
    }


    @Transactional
    public void deleteArticle(Long articleId, String token){ // 글 번호, 토큰값 받아서 삭제함
        Article article = articleRepository.findById(articleId);
        Member member = memberService.tokenToMember(token); // 현재 토근값과 작성한
        if(member == article.getWriter()){
            articleRepository.deleteArticle(article);
        }
    }


    @Transactional
    public Article updateArticle(Long articleId, String title, String content, String token){
        Article article = articleRepository.findById(articleId);
        Member member = memberService.tokenToMember(token);
        if(member == article.getWriter()){
            article.update(title, content);
        }
        return article;
    }



    public Article findById(Long articleId){
        return articleRepository.findById(articleId);
    }

    public List<Article> findAll(){
        return articleRepository.findAll();
    }

    public List<Article> findUserAll(String memberId){
        Member member = memberService.findByUserId(memberId);
        return articleRepository.findUserAll(member.getId());
    }


}
