package it.theboys.project0002api.model;

import lombok.Data;

@Data
public class ServerHost {
    private String serverId;
    private String serverName;
    private String serverHostUsername;
    private ServerStatus serverStatus;
}
