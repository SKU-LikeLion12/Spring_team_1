package likelion.helloworld.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Getter
public class Member {

    private Long id;
    private String userID;
    private String password;
    @Setter

    private String nickName;

    public Member(String userID, String password, String nickName) {

        this.userID = userID;
        this.setPassword(password);
        this.nickName = nickName;
    }


    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    public void setPassword(String password){
        this.password = passwordEncoder.encode(password);
    }

    public boolean checkPassword(String password){
        return passwordEncoder.matches(password, this.password);

    }


}