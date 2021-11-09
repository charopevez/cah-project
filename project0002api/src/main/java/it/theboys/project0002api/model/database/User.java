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

@Data
@Document(collection = "users")
public class User {
    @Id
    @JsonView(UserView.IdName.class)
    private String userId;
    @Indexed
    @NotNull
    @JsonView(UserView.IdName.class)
    private String userName;
    @NotNull
    private String password;
    private String userAvatar;
    @NotNull
    @JsonView(UserView.IdNameContact.class)
    @Email(message = "Email should be valid")
    private String userContact;
    private boolean isActive;
    private boolean isVerified=false;

    private Provider userProvider;
    private String userLanguage;
    private UserRole userRole = UserRole.GUEST;
    private long registeredAt;
    private long lastVisitedAt;
    private long lastModifiedAt;
}
