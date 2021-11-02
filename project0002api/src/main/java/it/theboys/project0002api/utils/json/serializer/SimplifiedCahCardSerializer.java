package it.theboys.project0002api.utils.json.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import it.theboys.project0002api.dto.database.SimplifiedCahCardListDto;
import it.theboys.project0002api.model.database.cah.CahCard;
import it.theboys.project0002api.model.database.cah.CahGameConfig;

import java.io.IOException;

public class SimplifiedCahCardSerializer extends StdSerializer<SimplifiedCahCardListDto> {
    protected SimplifiedCahCardSerializer() {
        super(SimplifiedCahCardListDto.class);
    }
    @Override
    public void serialize(SimplifiedCahCardListDto value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartArray();
        for (CahCard card: value.getCardList()) {
            gen.writeStartObject();
            gen.writeStringField("cardId", card.getCardId());
            gen.writeStringField("cardType", card.getCardType().toString());
            gen.writeStringField("cardText", card.getCardText());
            gen.writeObjectField("cardActions", card.getCardActions());
            gen.writeEndObject();
        }
        gen.writeEndArray();
    }
}
