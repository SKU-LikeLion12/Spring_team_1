package likelion.helloworld.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import likelion.helloworld.DTO.ArticleDTO;
import likelion.helloworld.domain.Article;
import likelion.helloworld.service.ArticleService;
import likelion.helloworld.service.FavoriteService;
import likelion.helloworld.service.JwtUtility;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

// controller는 필드를 만드는 곳이 아님 그러므로 NoArgumentConstruture 쓰지 않아도 됨
@RestController
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;
    private final FavoriteService favoriteService;

    private final JwtUtility jwtUtility;

    @GetMapping("/article/{id}")
    public ArticleDTO.ResponseArticle getArticle(@PathVariable("id") Long id) {
        Article article= articleService.findArticle(id);
        long favoriteCounter = favoriteService.findArticleFavorite(article.getId());
        return new ArticleDTO.ResponseArticle(article, favoriteCounter);
    }

    @GetMapping("/article/login/{id}")
    public ArticleDTO.ResponseArticle getArticleWithLogin(@PathVariable("id") Long id, @RequestBody ArticleDTO.tokenArticle request) {
        Article article= articleService.findArticle(id);
        boolean isFavorite = favoriteService.findMemberFavorite(request.getToken(), id);
        long favoriteCounter = favoriteService.findArticleFavorite(article.getId());
        return new ArticleDTO.ResponseArticle(article, favoriteCounter, isFavorite);
    }


    @PostMapping("/article/add")
    public ArticleDTO.ResponseArticle createArticle(@RequestBody ArticleDTO.RequestArticle request) {
        String userId = jwtUtility.validateToken(request.getToken()).getSubject();
        Article article = articleService.saveNewArticle(userId, request.getTitle(), request.getContent());
        long favoriteCounter = favoriteService.findArticleFavorite(article.getId());
        return new ArticleDTO.ResponseArticle(article, favoriteCounter);
    }

    @Operation(summary = "게시글 수정", description = "작성자의 token과 함께 수정할 제목과 내용을 입력" ,tags = {"Article"},
            responses = {@ApiResponse(responseCode = "200", description = "성골적인 수정"),
                    @ApiResponse(responseCode = "500", description = "미안 오류처리 안했어")}
    )

    @PutMapping("article/{id}")
    public ArticleDTO.ResponseArticle updateArticle(@Parameter(description = "게시글 번호", example = "1") @PathVariable("id") Long id, @RequestBody ArticleDTO.RequestArticle request) {
        Article article = articleService.updateArticle(id, request.getTitle(), request.getContent(), request.getToken());
        long favoriteCounter = favoriteService.findArticleFavorite(id);
        return new ArticleDTO.ResponseArticle(article, favoriteCounter);
    }


    @DeleteMapping("article/{id}")
    public void  deleteArticle(@RequestBody ArticleDTO.RemoveArticle request, @PathVariable("id") Long id) {
        articleService.deleteArticle(id, request.getToken());
    }

    @GetMapping("article/all")
    public List<ArticleDTO.ResponseArticle> getAllArticles() {
        List<ArticleDTO.ResponseArticle> responseArticles = new ArrayList<>();
        for (Article article : articleService.findAllArticle()) {
            long favoriteCounter = favoriteService.findArticleFavorite(article.getId());
            responseArticles.add(new ArticleDTO.ResponseArticle(article, favoriteCounter));
        }
        return responseArticles;
    }


    @GetMapping("article/all/sort")
    public List<ArticleDTO.ResponseArticle> getAllArticlesBySort() {
        List<ArticleDTO.ResponseArticle> responseArticles = new ArrayList<>();
        for (Article article : articleService.findAllArticle()) {
            long favoriteCounter = favoriteService.findArticleFavorite(article.getId());
            responseArticles.add(new ArticleDTO.ResponseArticle(article, favoriteCounter));
        }

        // favoriteCounter 기준으로 오름차순 정렬
        responseArticles.sort(Comparator.comparingLong(ArticleDTO.ResponseArticle::getFavorite).reversed());

        return responseArticles;
    }

    @GetMapping("/article/all/{member}")
    public List<ArticleDTO.ResponseArticle> writerArticleList(@PathVariable("member") String member) {
        List<ArticleDTO.ResponseArticle> responseArticles = new ArrayList<>();
        for (Article article : articleService.findUserArticles(member)) {
            long favoriteCounter = favoriteService.findArticleFavorite(article.getId());
            responseArticles.add(new ArticleDTO.ResponseArticle(article, favoriteCounter));
        }
        return responseArticles;
    }


}
