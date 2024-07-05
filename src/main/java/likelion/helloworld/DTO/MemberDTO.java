package likelion.helloworld.DTO;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.bind.annotation.PostMapping;

public class MemberDTO {

    @Data
    public static class MemberCreateRequest {
        @Schema(description = "닉네임", example = "test_nickname")
        private String nickname;
        @Schema(description = "아이디", example = "test_id")
        private String userId;
        @Schema(description = "비밀번호", example = "test_pwd")
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
