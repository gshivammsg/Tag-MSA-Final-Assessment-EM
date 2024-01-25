package com.em.tag.dto.response_dto;

import com.em.tag.utils.ResponseModel;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
public class TagResponseDTO extends ResponseModel {
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("tag_name")
    private String tagName;
}
