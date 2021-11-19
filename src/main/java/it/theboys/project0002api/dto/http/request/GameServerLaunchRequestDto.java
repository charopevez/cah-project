package it.theboys.project0002api.dto.http.request;

import lombok.Data;

@Data
public class GameServerLaunchRequestDto<T> {
    String playerName;
    T config;
}
