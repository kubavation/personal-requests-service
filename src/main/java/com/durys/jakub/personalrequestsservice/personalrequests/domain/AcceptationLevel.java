package com.durys.jakub.personalrequestsservice.personalrequests.domain;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
class AcceptationLevel {
    private Integer level = 0;

    AcceptationLevel reset() {
        this.level = 0;
        return this;
    }

    AcceptationLevel increment() {
        this.level++;
        return this;
    }

}
