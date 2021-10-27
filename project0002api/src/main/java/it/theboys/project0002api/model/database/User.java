package it.theboys.project0002api.model.database;

import it.theboys.project0002api.enums.Role;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection="user")
public class User {
    @Id
    private String userId;
    private String userName;
    private Role userRole;
}
