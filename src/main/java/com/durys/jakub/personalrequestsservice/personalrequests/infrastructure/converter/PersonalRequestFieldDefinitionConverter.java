package com.durys.jakub.personalrequestsservice.personalrequests.infrastructure.converter;

import com.google.common.base.CaseFormat;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Map;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PersonalRequestFieldDefinitionConverter {
    

    public static Map<String, Object> snakecase(Map<String, Object> map) {
        return map.entrySet()
                .stream()
                .collect(Collectors
                        .toMap(entry -> CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, entry.getKey()),
                                Map.Entry::getValue));
    }
}
