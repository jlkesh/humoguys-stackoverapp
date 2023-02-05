package dev.jlkesh.stackoverflowdemo.domains;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(schema = "stackoverflowapp")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthUser {

    @Id
    @SequenceGenerator(
            name = "auth_user_sequence",
            sequenceName = "auth_user_id_seq",
            initialValue = 1,
            allocationSize = 1,
            schema = "stackoverflowapp"
    )
    @GeneratedValue(generator = "auth_user_sequence", strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String username;

    private String password;
    @Column(unique = true, nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    private Status status = Status.INACTIVE;

    @ManyToMany(targetEntity = AuthRole.class,
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    @JoinTable(
            name = "auth_user_auth_role",
            schema = "stackoverflowapp",
            joinColumns = @JoinColumn(name = "auth_user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "auth_role_id", referencedColumnName = "id")
    )
    private Set<AuthRole> roleSet;

    @CreationTimestamp
    @CreatedDate
    private LocalDateTime createdAt;
    private LocalDateTime lastLoginAt;
    private int loginTryCount;

    public enum Status {
        ACTIVE,
        INACTIVE,
        BLOCKED
    }

}
