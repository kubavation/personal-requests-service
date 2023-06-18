package com.durys.jakub.personalrequestsservice.personalrequests.infrastructure.model;

import com.durys.jakub.personalrequestsservice.shared.converters.CamelCaseConverter;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
@Data
public class PersonalRequest {

    private Long id;
    private String tenantId;
    private Long typeId;


    private Map<String, Object> fields = new HashMap<>();

    public Map<String, Object> rawFields() {
        return fields;
    }

    @JsonAnySetter
    public void setField(String key, String value) {
        fields.put(key, value);
    }

    @JsonAnyGetter
    public Map<String, Object> getFields() {
        return CamelCaseConverter.camelcase(fields);
    }
}
