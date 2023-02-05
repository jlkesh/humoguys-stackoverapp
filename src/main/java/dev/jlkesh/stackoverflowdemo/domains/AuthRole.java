package dev.jlkesh.stackoverflowdemo.domains;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(schema = "stackoverflowapp")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthRole {

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
}
