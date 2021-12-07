package it.theboys.project0002api.mikhaylov;

import it.theboys.project0002api.enums.db.GameName;
import it.theboys.project0002api.model.CardSet;
import it.theboys.project0002api.model.cah.CahGameConfig;
import it.theboys.project0002api.service.cardgame.CardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;


@RestController
@Slf4j
@RequestMapping("/api/v1/{gameName}/game/")
public class GameController {
    @Autowired
    private GameMainframeService gameMainframeService;
    @Autowired
    private CardService cardService;
    
    @PostMapping("/launch")
    public ResponseEntity<?> newGame(
            @RequestBody CahGameConfig config,
            @PathVariable GameName gameName,
            Principal principal){
        try {
            String response = gameMainframeService.createGame(gameName,principal.getName(),config);
            log.info(String.format("Create new game with %s", config.toString()));
            return new ResponseEntity<>(config, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(config, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/config")
    public ResponseEntity<?> defaultConfig(
            @PathVariable GameName gameName){
        switch (gameName){
            case CAH:
                List<CardSet> setList=cardService.getAllSets(gameName);
                CahGameConfig defaultConfig=new CahGameConfig();
                defaultConfig.setSetList(setList);
                return new ResponseEntity<>(defaultConfig, HttpStatus.OK);
                default:
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("/{gameId}/join")
    public ResponseEntity<?> joinGame(@RequestBody String request,
                                      @PathVariable String gameName,
                                      @PathVariable String gameId){
        return new ResponseEntity<>(request, HttpStatus.OK);
    }
}
