package it.theboys.project0002api.utils.json.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import it.theboys.project0002api.enums.cah.CahSetExpansion;
import it.theboys.project0002api.model.cah.CahGameConfig;

import java.io.IOException;

public class  CahSetExpansionSerializer extends StdSerializer<CahSetExpansion> {

    protected CahSetExpansionSerializer() {
        super(CahSetExpansion.class);
    }

    @Override
    public void serialize(CahSetExpansion value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();
        gen.writeFieldName("name");
        gen.writeString(value.name());
        gen.writeFieldName("code");
        gen.writeString(value.getCode().toString());
        gen.writeFieldName("description");
        gen.writeString(value.getDesc());
        gen.writeFieldName("title");
        gen.writeString(value.getTitle());
        gen.writeFieldName("writePermission");
        gen.writeString(value.getRole().toString());
        gen.writeEndObject();
    }
}
