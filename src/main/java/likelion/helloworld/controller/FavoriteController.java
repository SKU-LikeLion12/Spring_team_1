package likelion.helloworld.controller;


import likelion.helloworld.DTO.FavoriteDTO;
import likelion.helloworld.domain.Favorite;
import likelion.helloworld.service.FavoriteService;
import likelion.helloworld.service.JwtUtility;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class FavoriteController {
    private final FavoriteService favoriteService;
    private final JwtUtility jwtUtility;


    @PostMapping ("/Favorite/{ArticleId}")
    public Favorite createFavorite(@PathVariable("ArticleId") Long ArticleId, @RequestBody FavoriteDTO.RequestFavorite request) {
        String userId = jwtUtility.validateToken(request.getToken()).getSubject();
        Favorite favorite = favoriteService.createFavorite(userId, ArticleId);
        return favorite;
    }

    @DeleteMapping ("/Favorite/{ArticleId}")
    public Favorite deleteFavorite(@PathVariable("ArticleId") Long ArticleId, @RequestBody FavoriteDTO.RequestFavorite request) {
        String userId = jwtUtility.validateToken(request.getToken()).getSubject();
        Favorite favorite = favoriteService.deleteFavorite(userId, ArticleId);
        return favorite;
    }


}
