package com.mkaszynski.tdd.mock.tools;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.util.List;

public class Serializer {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public <T> List<T> deserialize(String content, Class<T> elementClass) throws JsonProcessingException {
        OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        CollectionType type = OBJECT_MAPPER.getTypeFactory().constructCollectionType(List.class, elementClass);
        return OBJECT_MAPPER.readValue(content, type);
    }
}