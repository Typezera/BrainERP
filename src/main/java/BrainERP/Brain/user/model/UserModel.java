package BrainERP.Brain.user.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;


@Entity
@Getter
@Setter
@Table(name="Usuario")
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    private String password;

    private boolean activate;

    @CreationTimestamp
    private LocalDateTime createdAt;

//    @Enumerated(EnumType.STRING)
//    private UserOrCompany howAreYou;


}
