package by.bsuir.laba4.query.user;

import by.bsuir.laba4.domain.entity.Role;
import by.bsuir.laba4.query.CustomQuery;
import java.util.Collections;
import java.util.List;

/**
 * Find users by role query
 */
public class FindByRoleQuery implements CustomQuery {
    /**
     * User role
     */
    private final Role role;

    /**
     * Construtor
     *
     * @param role user role
     */
    public FindByRoleQuery(Role role) {
        this.role = role;
    }

    /**
     * Get sql part
     *
     * @return String
     */
    @Override
    public String toSql() {
        return "WHERE role = ?";
    }

    /**
     * Get parameters
     *
     * @return List<Object>
     */
    @Override
    public List<Object> getParameters() {
        return Collections.singletonList(role);
    }
}
