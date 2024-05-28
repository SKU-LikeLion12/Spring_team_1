package likelion.helloworld.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.bind.annotation.PostMapping;

public class MemberDTO {

    @Data
    public static class MemberCreateRequest {
        private String nickname;
        private String userId;
        private String password;
    }

    @Data
    public static  class MemberLoginRequest {
        private String userId;
        private String password;
    }

    @Data
    @AllArgsConstructor
    public static  class MemberResponse {
        private String userId;
        private String nickname;
    }

    @Data
    public static  class MemberUpdateRequest {
        private String token;
        private String nickname;
    }

    @Data
    public static  class MemberDeleteRequest {
        private String token;
    }




}
