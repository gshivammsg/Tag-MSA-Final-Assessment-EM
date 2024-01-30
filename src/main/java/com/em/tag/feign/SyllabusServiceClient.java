package com.em.tag.feign;

import com.em.tag.dto.response_dto.SyllabusResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import static com.em.tag.constants.Constants.SYLLABUS_MSA_URL;

@FeignClient(url = SYLLABUS_MSA_URL, name = "Syllabus-MSA")
public interface SyllabusServiceClient {

    @GetMapping("getSyllabusById/{id}")
    public SyllabusResponseDTO getSyllabusById(@PathVariable Integer id);
}
