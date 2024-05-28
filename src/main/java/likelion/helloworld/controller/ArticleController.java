package likelion.helloworld.controller;


import likelion.helloworld.DTO.ArticleDTO;
import likelion.helloworld.domain.Article;
import likelion.helloworld.service.ArticleService;
import likelion.helloworld.service.JwtUtility;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

// controller는 필드를 만드는 곳이 아님 그러므로 NoArgumentConstruture 쓰지 않아도 됨
@RestController
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;
    private final JwtUtility jwtUtility;


    @GetMapping("/article/{id}")
    public ArticleDTO.ResponseArticle getArticle(@PathVariable("id") Long id) {
        Article article= articleService.findArticle(id);
        return new ArticleDTO.ResponseArticle(article);
    }

    @PostMapping("/article/add")
    public ArticleDTO.ResponseArticle createArticle(@RequestBody ArticleDTO.RequestArticle request) {
        String userId = jwtUtility.validateToken(request.getToken()).getSubject();
        Article article = articleService.saveNewArticle(userId, request.getTitle(), request.getContent());
        return new ArticleDTO.ResponseArticle(article);
    }

    @PutMapping("article/{id}")
    public ArticleDTO.ResponseArticle updateArticle(@PathVariable("id") Long id, @RequestBody ArticleDTO.RequestArticle request) {
        Article article = articleService.updateArticle(id, request.getTitle(), request.getContent(), request.getToken());
        return new ArticleDTO.ResponseArticle(article);
    }

    @DeleteMapping("article{id}")
    public void  deleteArticle(@RequestBody ArticleDTO.RemoveArticle request, @PathVariable("id") Long id) {
        articleService.deleteArticle(id, request.getToken());
    }

    @GetMapping("article/all")
    public List<ArticleDTO.ResponseArticle> getAllArticles() {
        List<ArticleDTO.ResponseArticle> responseArticles = new ArrayList<>();
        for (Article article : articleService.findAllArticle()) {
            responseArticles.add(new ArticleDTO.ResponseArticle(article));
        }
        return responseArticles;
    }

    @GetMapping("/article/add/{member}")
    public List<ArticleDTO.ResponseArticle> writerArticleList(@PathVariable("member") String member) {
        List<ArticleDTO.ResponseArticle> responseArticles = new ArrayList<>();
        for (Article article : articleService.findUserArticles(member)) {
            responseArticles.add(new ArticleDTO.ResponseArticle(article));
        }
        return responseArticles;
    }


}
