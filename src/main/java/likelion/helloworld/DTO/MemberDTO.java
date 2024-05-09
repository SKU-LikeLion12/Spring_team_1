package likelion.helloworld.DTO;

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
}
