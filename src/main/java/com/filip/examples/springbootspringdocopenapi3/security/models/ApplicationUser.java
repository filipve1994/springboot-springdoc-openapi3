package com.filip.examples.springbootspringdocopenapi3.security.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.filip.examples.springbootspringdocopenapi3.models.auditing.Auditable;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.NaturalId;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "applicationusers", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "username"
        }),
        @UniqueConstraint(columnNames = {
                "email"
        })
})
public class ApplicationUser extends Auditable<String> implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Schema(description = "")
    @JsonProperty("id")
    private Long id;

    @Schema(description = "", example = "FilipUser")
    @JsonProperty("username")
    @Column(unique = true)
    @NotBlank
    private String username = null;

    @Schema(description = "", example = "toolongemail@gmail.com")
    @JsonProperty("email")
    @Size(
            min = 5,
            max = 40,
            message = "The user email '${validatedValue}' must be between {min} and {max} characters long"
    )
    @Email
    @NaturalId
    private String email = null;

    @Schema(description = "", example = "XXXXXXXXXXXXX")
    @JsonProperty("password")
    @NotBlank
    @Size(max = 100)
    private String password = null;

    @Transient
    private String passwordConfirm;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "applicationuser_roles",
            joinColumns = @JoinColumn(name = "applicationuser_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private Set<Role> roles = new HashSet<>();

    @Schema(description = "", example = "Filip Vanden Eynde")
    @JsonProperty("name")
    @NotNull(message = "name cannot be null")
    @NotEmpty(message = "custom message - must not be empty")
    @NotBlank
    @Size(max = 40)
    private String name = null;

    @Schema(description = "User Status", example = "1")
    @JsonProperty("userStatus")
    private Integer userStatus = null;

    public ApplicationUser() {

    }

    public ApplicationUser(String name, String username, String email, String password) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
    }

}
