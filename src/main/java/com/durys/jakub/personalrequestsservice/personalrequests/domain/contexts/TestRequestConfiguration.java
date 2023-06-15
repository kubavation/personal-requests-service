package com.durys.jakub.personalrequestsservice.personalrequests.domain.contexts;

import com.durys.jakub.personalrequestsservice.personalrequests.domain.contexts.test.requests.TestRemoteWorkApplication;
import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


@Retention(RetentionPolicy.RUNTIME)
@JacksonAnnotationsInside
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes( {
        @JsonSubTypes.Type(value = TestRemoteWorkApplication.class, name = "remote-work-application")
})
public @interface TestRequestConfiguration {
}

