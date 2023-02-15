package com.chat.entity.role;

import com.chat.entity.role.type.UserAppRoleType;
import com.chat.entity.user.User;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "USER_APP_ROLE")
public class UserAppRole {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_APP_ROLE_SEQUENCE")
    @SequenceGenerator(name = "USER_APP_ROLE_SEQUENCE")
    private Long id;

    @OneToOne
    @JoinColumn(
            name = "user_id",
            foreignKey = @ForeignKey(name = "USER_APP_ROLE_USER_ID_USER_ID"),
            nullable = false,
            referencedColumnName = "id"
    )
    private User user;

    @Column(name = "user_app_role_type", nullable = false, length = 20)
    private UserAppRoleType userAppRoleType;

    public UserAppRole(User user, UserAppRoleType userAppRoleType) {
        this.user = user;
        this.userAppRoleType = userAppRoleType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UserAppRoleType getUserAppRoleType() {
        return userAppRoleType;
    }

    public void setUserAppRoleType(UserAppRoleType userAppRoleType) {
        this.userAppRoleType = userAppRoleType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserAppRole that = (UserAppRole) o;
        return Objects.equals(id, that.id) && Objects.equals(user, that.user) && userAppRoleType == that.userAppRoleType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, userAppRoleType);
    }

    @Override
    public String toString() {
        return "UserAppRole{" +
                "id=" + id +
                ", user=" + user +
                ", userAppRoleType=" + userAppRoleType +
                '}';
    }
}
