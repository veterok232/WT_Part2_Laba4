package by.bsuir.laba4.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.io.Serializable;

/**
 * User entity class
 */
@Data
@AllArgsConstructor
public class User implements BaseEntity, Serializable {
    /**
     *  Serial version uid
     */
    private static final long serialVersionUID = 5119543312123546198L;

    /**
     * User id
     */
    private Integer id;

    /**
     * User name
     */
    private String username;

    /**
     * Password
     */
    private String password;

    /**
     * User role
     */
    private Role role;

    /**
     * Constructor
     *
     * @param id user id
     * @param username user name
     * @param password password
     */
    public User(Integer id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }
}
