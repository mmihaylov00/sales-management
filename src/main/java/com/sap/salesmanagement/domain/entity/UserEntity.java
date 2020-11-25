package com.sap.salesmanagement.domain.entity;

import com.sap.salesmanagement.enumerate.RoleType;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(generator = "uuid-string")
    @GenericGenerator(name = "uuid-string",
            strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", nullable = false, unique = true, updatable = false, length = 36)
    private String id;

    @Column(name = "email", unique = true, nullable = false, length = 40)
    private String email;

    @Column(name = "password", length = 128)
    private String password;

    @Column(name = "first_name", length = 20)
    private String firstName;

    @Column(name = "last_name", length = 20)
    private String lastName;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "role")
    private RoleType role;

    public UserEntity() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public RoleType getRole() {
        return role;
    }

    public void setRole(RoleType role) {
        this.role = role;
    }
}
