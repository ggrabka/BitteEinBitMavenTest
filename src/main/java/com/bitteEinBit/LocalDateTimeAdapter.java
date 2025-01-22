package com.bitteEinBit;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Diese Klasse dient als Adapter, um `LocalDateTime` für die Speicherung in JSON zu serialisieren und zu deserialisieren.
 */
public class LocalDateTimeAdapter implements JsonSerializer<LocalDateTime>, JsonDeserializer<LocalDateTime> {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE_TIME; // Standard-Format für Datum/Zeit

    /**
     * Serialisiert ein `LocalDateTime`-Objekt in eine JSON-Zeichenkette.
     */
    @Override
    public JsonElement serialize(LocalDateTime localDateTime, Type type, JsonSerializationContext jsonSerializationContext) {
        return new JsonPrimitive(localDateTime.format(FORMATTER));
    }

    /**
     * Deserialisiert esine JSON-Zeichenkette in ein `LocalDateTime`-Objekt.
     */
    @Override
    public LocalDateTime deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) {
        return LocalDateTime.parse(jsonElement.getAsString(), FORMATTER);
    }
}
