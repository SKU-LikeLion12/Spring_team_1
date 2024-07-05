package likelion.helloworld.DTO;

import likelion.helloworld.domain.Favorite;
import lombok.Data;


public class FavoriteDTO {

    @Data
    public static class ResponseFavorite {
        private String title;
        private String NickName;

        public ResponseFavorite(Favorite favorite) {
            this.title = favorite.getLikeThing().getTitle();
            this.NickName = favorite.getLiker().getNickName();
        }
    }


    @Data
    public static class RequestFavorite{
        private String token;
    }

}
