package com.durys.jakub.personalrequestsservice.personalrequests.infrastructure;

import com.durys.jakub.personalrequestsservice.personalrequests.infrastructure.out.PersonalRequestFieldEntity;
import com.durys.jakub.personalrequestsservice.requestypes.domain.FieldType;
import com.durys.jakub.personalrequestsservice.requestypes.domain.RequestTypeField;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PersonalRequestFieldConverterTest {

    @Test
    void shouldConvertValueToLocalDate() {
        RequestTypeField requestTypeField = new RequestTypeField();
        requestTypeField.setType(FieldType.DATE);

        PersonalRequestFieldEntity result = PersonalRequestFieldConverter.convert(requestTypeField, "2023-01-01");

        assertEquals(LocalDate.of(2023, 1, 1), result.getDateValue());
    }
}