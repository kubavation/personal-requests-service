package com.durys.jakub.personalrequestsservice.personalrequests.infrastructure;

import com.durys.jakub.personalrequestsservice.personalrequests.domain.PersonalRequestField;
import com.durys.jakub.personalrequestsservice.requestypes.domain.FieldType;
import com.durys.jakub.personalrequestsservice.requestypes.domain.RequestTypeField;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PersonalRequestDTOFieldConverterTest {

    @Test
    void shouldConvertValueToLocalDate() {
        RequestTypeField requestTypeField = new RequestTypeField();
        requestTypeField.setType(FieldType.DATE);

        PersonalRequestField result = PersonalRequestFieldConverter.convert(requestTypeField, "2023-01-01");

        assertEquals(LocalDate.of(2023, 1, 1), result.getDateValue());
    }

    @Test
    void shouldConvertValueToLong() {
        RequestTypeField requestTypeField = new RequestTypeField();
        requestTypeField.setType(FieldType.NUMBER);

        PersonalRequestField result = PersonalRequestFieldConverter.convert(requestTypeField, 1);

        assertEquals(1, result.getIntValue());
    }

    @Test
    void shouldConvertValueToText() {
        RequestTypeField requestTypeField = new RequestTypeField();
        requestTypeField.setType(FieldType.TEXT);

        PersonalRequestField result = PersonalRequestFieldConverter.convert(requestTypeField, "TEST");

        assertEquals("TEST", result.getTextValue());
    }

    @Test
    void shouldNotThrowException_whenProvidedValueIsNull() {
        RequestTypeField requestTypeField = new RequestTypeField();
        requestTypeField.setType(FieldType.TEXT);

        assertDoesNotThrow(() -> PersonalRequestFieldConverter.convert(requestTypeField, null));
    }
}