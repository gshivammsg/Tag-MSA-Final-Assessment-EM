package com.em.tag.dto.request_dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TagRequestDTO {
    @JsonProperty("id")
    private Integer id;

    @JsonProperty("tag_name")
    private String tagName;

}
