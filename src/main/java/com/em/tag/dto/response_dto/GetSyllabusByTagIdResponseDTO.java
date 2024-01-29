package com.em.tag.dto.response_dto;

import com.em.tag.utils.ResponseModel;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
public class GetSyllabusByTagIdResponseDTO extends ResponseModel {
    @JsonProperty("syllabus_list")
    private List<SyllabusResponseDTO> syllabusList;
}
