package it.theboys.project0002api.enums.cah;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import it.theboys.project0002api.enums.db.UserRole;
import it.theboys.project0002api.utils.json.serializer.CahGameConfigSerializer;
import it.theboys.project0002api.utils.json.serializer.CahSetExpansionSerializer;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonSerialize(using = CahSetExpansionSerializer.class)
public enum CahSetExpansion {
    CAH_EXPANSION(0,"All versions of the Cards Against Humanity base set, including international versions, officially sold by CAH.","Cards Against Humanity: Official Expansions", UserRole.MODERATOR),
    CAH_MAIN_DECK(1,"All versions of the Cards Against Humanity Expansions officially sold by CAH.","Cards Against Humanity: Main Decks", UserRole.MODERATOR),
    CAH_PACKS(2,"All the packs officially sold or released by CAH.","Cards Against Humanity: Official Packs", UserRole.MODERATOR),
    ETSY(3,"Sets created and sold on Etsy, usually downloadable PDFs or physical card sets created from sets that were sold on Etsy.","Cards Against Humanity expansions from Etsy", UserRole.MODERATOR),
    FAN_EXPANSION(4,"Sets created by fans of Cards Against Humanity to be used with the game under a Creative Commons BY-NC-SA 2.0 license, meaning that the sets can not be commercially sold.","Cards Against Humanity: Fan's Expansions", UserRole.MODERATOR),
    KICKSTARTER(5,"Sets funded on Kickstarter that were only available through the Kickstarter campaign. Kickstarter sets that end up being sold commercially are added to the Third Party Commercial or Stand Alone Games sheets.","Cards Against Humanity: Expansions from Kickstarter", UserRole.MODERATOR),
    SPECIAL_PURPOSE(6,"Sets created for special purposes like fundraising, events, etc., usually limited release.","Cards Against Humanity: Special purpose sets", UserRole.MODERATOR),
    STAND_ALONE_GAMES(7,"Sets (usually themed) that are sold separately from Cards Against Humanity (i.e., not sold as an unofficial expansion) that can be played as its own game or combined with Cards Against Humanity.","Stand Alone Games", UserRole.MODERATOR),
    THIRD_PARTY_COMMERCIAL(8,"Sets created by third party producers as unofficial expansions or packs to the Cards Against Humanity game, and sold commercially (Amazon, website, etc.).","Cards Against Humanity: Third party commercial expansions", UserRole.MODERATOR),
    PRINTED_ON_DEMAND(9, "Sets created and sold on print on demand sites like PrinterStudio, DriveThru Cards, The Game Crafter, etc.","Cards Against Humanity: Expansions printed on demand", UserRole.MODERATOR),
    PRIVATE(10,"Sets created by users","User's custom sets ", UserRole.USER);
    private final Integer code;
    private final String desc;
    private final String title;
    private final UserRole role;
}
