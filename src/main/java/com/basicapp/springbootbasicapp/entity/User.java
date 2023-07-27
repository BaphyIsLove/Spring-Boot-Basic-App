package com.basicapp.springbootbasicapp.entity;

import java.util.Set;

import org.hibernate.annotations.GenericGenerator;

import com.basicapp.springbootbasicapp.service.UserServiceImpl;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

// import jakarta.validation.constraints.Pattern;
// import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    // @Setter(AccessLevel.NONE)
    private Long id;

    @Column
    @NotBlank(message = "Introduce un nombre")
    private String firstName;
    @Column
    @NotBlank(message = "Introduce al menos un apellido")
    private String lastName;
    @Column
    @NotBlank
    @Email
    private String email;
    @Column
    @NotBlank
    private String username;
    @Column
    @NotBlank
    // @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$", message = "La contraseña debe contener al menos una letra mayúscula, una letra minúscula y un número.")
    private String password;
    @Transient
    // @NotBlank
    private String confirmPassword;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name="user_id"), inverseJoinColumns = @JoinColumn(name="role_id"))
    @NotNull
    private Set<Role> roles; 
}
