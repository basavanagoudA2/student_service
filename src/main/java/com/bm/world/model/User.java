package com.bm.world.model;

import com.bm.world.ApplicationConstants;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity @Getter @Setter
@Table(name = ApplicationConstants.USER_INFO_TABLE_NAME,
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "user_name"),
                @UniqueConstraint(columnNames = "emailId")
        }
)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "user_name")
    @NotBlank
   // @Max()
    private String userName;
    @Column(name = "password")
    @NotBlank
   // @Size(max = 50)
    private String password;
    @NotBlank
    @Size(max = 50)
    @Email
    private String emailId;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name="role_id")
    )
    private Set<Role> roles=new HashSet<>();

   /* public User() {
    }*/

   /* public User(@NotBlank @Max(25) String userName, @NotBlank @Size(max = 50) String password, @NotBlank @Size(max = 50) @Email String emailId) {
        this.userName = userName;
        this.password = password;
        this.emailId = emailId;
    }*/
}

