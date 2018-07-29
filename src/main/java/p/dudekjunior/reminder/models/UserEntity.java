package p.dudekjunior.reminder.models;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "user_auth")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "user_login")
    private String userLogin;
    @Column(name = "user_email")
    private String userEmail;
    private String password;
    @Column(name = "join_date")
    private LocalDateTime joinDate;
}
