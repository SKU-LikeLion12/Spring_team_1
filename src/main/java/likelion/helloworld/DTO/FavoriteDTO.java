package likelion.helloworld.DTO;

import lombok.Data;

public class FavoriteDTO {

    @Data
    public static class ResponseDTO {
        
    }


    @Data
    public static class RequestFavorite{
        private String token;
    }


}
