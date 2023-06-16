package com.durys.jakub.personalrequestsservice.shared.converters;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.text.CaseUtils;

import java.util.Map;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CamelCaseConverter {

    public static Map<String, Object> camelcase(Map<String, Object> map) {
        return map.entrySet()
                .stream()
                .collect(Collectors
                        .toMap(entry -> CaseUtils.toCamelCase(entry.getKey(), false, '_'),
                                Map.Entry::getValue));
    }
}
