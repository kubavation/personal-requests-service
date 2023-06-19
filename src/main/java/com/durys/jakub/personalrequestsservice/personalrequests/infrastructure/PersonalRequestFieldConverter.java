package com.durys.jakub.personalrequestsservice.personalrequests.infrastructure;

import com.durys.jakub.personalrequestsservice.personalrequests.infrastructure.out.PersonalRequestFieldEntity;
import com.durys.jakub.personalrequestsservice.requestypes.domain.RequestTypeField;
import com.durys.jakub.personalrequestsservice.shared.Status;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PersonalRequestFieldConverter {

    public static PersonalRequestFieldEntity convert(RequestTypeField definition, Object value) {

        PersonalRequestFieldEntity entity = new PersonalRequestFieldEntity();
        entity.setRequestFieldTypeId(definition.getId());
        entity.setStatus(Status.A);

        if (Objects.isNull(value)) {
            return entity;
        }

        return switch (definition.getType()) {
            case DATE -> entity.withDateValue(LocalDate.parse(value.toString()));
            case TEXT -> entity.withTextValue(value.toString());
            case NUMBER -> entity.withIntValue(Long.valueOf(value.toString()));
        };
    }

}
