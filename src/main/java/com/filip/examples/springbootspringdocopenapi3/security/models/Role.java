package com.filip.examples.springbootspringdocopenapi3.security.models;

import com.filip.examples.springbootspringdocopenapi3.models.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Set;

//https://hellokoding.com/registration-and-login-example-with-spring-security-spring-boot-spring-data-jpa-hsql-jsp/
@Getter
@Setter
@Entity
@Table(name = "roles")
public class Role implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    /*
        @Enumerated(EnumType.STRING)
    @NaturalId
    @Column(length = 60)
    private RoleName name;
     */

    @ManyToMany(mappedBy = "roles")
    private Set<ApplicationUser> users;
    //private Set<User> users;

    public Role() {

    }

    public Role(String name) {
        this.name = name;
    }

}
