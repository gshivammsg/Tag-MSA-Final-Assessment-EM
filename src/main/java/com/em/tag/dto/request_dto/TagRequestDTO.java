package com.em.tag.dto.request_dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TagRequestDTO {

    @JsonProperty("id")
    private Integer id;

    @NotNull
    @NotEmpty
    @JsonProperty("tag_name")
    private String tagName;

}
