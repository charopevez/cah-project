package it.theboys.project0002api.model.database;

import com.fasterxml.jackson.annotation.JsonView;
import it.theboys.project0002api.enums.Provider;
import it.theboys.project0002api.enums.UserRole;
import it.theboys.project0002api.model.view.UserView;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

@Data
@Document(collection = "users")
public class User {
    @Id
    @JsonView(UserView.IdName.class)
    private String userId;
    @NotNull
    @JsonView(UserView.IdName.class)
    @Indexed(unique=true)
    private String userName;
    @NotNull
    private String password;
    private String userAvatar;
    @NotNull
    @JsonView(UserView.IdNameContact.class)
    @Indexed(unique=true)
    @Email(message = "Email should be valid")
    private String userContact;
    private boolean isActive;
    private boolean isVerified = false;
    private String verificationCode;
    private Provider userProvider;
    private String userLanguage;
    private Collection<UserRole> userRole = new ArrayList<>(Collections.singleton(UserRole.GUEST));
    private long registeredAt;
    private long lastVisitedAt;
    private long lastModifiedAt;

    public void addUserRole(UserRole role) {
        userRole.add(role);
    }
    public void removeUserRole(UserRole role) {
        userRole.remove(role);
    }
}
