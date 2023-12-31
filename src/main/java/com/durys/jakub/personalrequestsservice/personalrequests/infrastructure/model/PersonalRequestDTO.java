package com.durys.jakub.personalrequestsservice.personalrequests.infrastructure.model;

import com.durys.jakub.personalrequestsservice.personalrequests.infrastructure.converter.PersonalRequestFieldDefinitionConverter;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@NoArgsConstructor
@Data
public class PersonalRequestDTO {

    private Long id;
    private String tenantId;
    private Long typeId;

    private Map<String, Object> fields = new HashMap<>();

    public Map<String, Object> rawFields() {
        return PersonalRequestFieldDefinitionConverter.snakecase(fields);
    }

    public Optional<Object> fieldValue(String fieldName) {
        return Optional.ofNullable(rawFields().get(fieldName));
    }

    @JsonAnySetter
    public void setField(String key, String value) {
        fields.put(key, value);
    }

    @JsonAnyGetter
    public Map<String, Object> getFields() {
        return fields;
    }
}
