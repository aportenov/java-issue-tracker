package softuni.domains.entity;

import softuni.domains.enumeration.Role;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 5, max = 30, message = "ERROR: The username should be between 5 and 30 symbols")
    @Column(name = "user_name",unique = true, nullable = false)
    private String username;

    @Size(min = 5, message = "ERROR: The name should be min 5 symbols")
    @Column(name = "full_name")
    private String 	fullName;

   // @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\\\d)(?=.*[$@$!%*?&])[A-Za-z\\\\d$@$!%*?&]{8,}$")
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
