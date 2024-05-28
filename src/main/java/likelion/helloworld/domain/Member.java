package likelion.helloworld.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@NoArgsConstructor
@Getter
@Entity
public class Member {

    @Id @GeneratedValue
    private Long id;
    @Column(unique = true)
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