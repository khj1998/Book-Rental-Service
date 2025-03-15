package com.bookservice.domain.model.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class IdName {
    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;
}

