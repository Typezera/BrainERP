package BrainERP.Brain.user.model;

import BrainERP.Brain.user.dto.UserRequestDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;


@Entity
@Getter
@Setter
@Table(name="Usuario")
@AllArgsConstructor
@NoArgsConstructor
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

    public UserModel(UserRequestDto data){
        this.name = data.name();
        this.email = data.email();
        this.password = data.password();
    };


}
