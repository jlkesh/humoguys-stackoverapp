package dev.jlkesh.stackoverflowdemo.domains;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Entity
@Table(schema = "stackoverflowapp")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AuthRole implements GrantedAuthority {

    @Id
    @SequenceGenerator(
            name = "auth_role_sequence",
            sequenceName = "auth_role_id_seq",
            initialValue = 1,
            allocationSize = 1,
            schema = "stackoverflowapp"
    )
    @GeneratedValue(generator = "auth_role_sequence", strategy = GenerationType.SEQUENCE)
    private Integer id;

    private String code;

    @Override
    public String getAuthority() {
        return "ROLE_" + code;
    }

    public GrantedAuthority authority() {
        return new SimpleGrantedAuthority(getAuthority());
    }
}
