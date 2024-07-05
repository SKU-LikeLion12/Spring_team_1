package likelion.helloworld.controller;


import likelion.helloworld.DTO.FavoriteDTO;
import likelion.helloworld.domain.Favorite;
import likelion.helloworld.service.FavoriteService;
import likelion.helloworld.service.JwtUtility;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class FavoriteController {
    private final FavoriteService favoriteService;
    private final JwtUtility jwtUtility;


    @PostMapping ("/Favorite/{ArticleId}/toggle")
    public FavoriteDTO.ResponseFavorite toggleFavorite(@PathVariable("ArticleId") Long ArticleId, @RequestBody FavoriteDTO.RequestFavorite request) {
        Favorite favorite = favoriteService.toggleFavorite(request.getToken(), ArticleId);

        return new FavoriteDTO.ResponseFavorite(favorite);
    }


    @PostMapping ("/Favorite/{ArticleId}")
    public FavoriteDTO.ResponseFavorite createFavorite(@PathVariable("ArticleId") Long ArticleId, @RequestBody FavoriteDTO.RequestFavorite request) {
        String userId = jwtUtility.validateToken(request.getToken()).getSubject();
        Favorite favorite = favoriteService.createFavorite(userId, ArticleId);
        return new FavoriteDTO.ResponseFavorite(favorite);
    }


    @DeleteMapping ("/Favorite/{ArticleId}")
    public Favorite deleteFavorite(@PathVariable("ArticleId") Long ArticleId, @RequestBody FavoriteDTO.RequestFavorite request) {
        String userId = jwtUtility.validateToken(request.getToken()).getSubject();
        Favorite favorite = favoriteService.deleteFavorite(userId, ArticleId);
        return favorite;
    }


    @GetMapping ("/Favorite")
    public List<FavoriteDTO.ResponseFavorite> memberToFavorite(@RequestBody FavoriteDTO.RequestFavorite request) {
        // 토큰 유효 체크
        List<FavoriteDTO.ResponseFavorite> responseFavorites = new ArrayList<>();
        for ( Favorite favorite : favoriteService.memberToFavorite(request.getToken()) ){
            responseFavorites.add(new FavoriteDTO.ResponseFavorite(favorite));
        }
        return responseFavorites;
    }


}
