package com.em.tag.controller;

import com.em.tag.dto.request_dto.TagRequestDTO;
import com.em.tag.dto.response_dto.TagResponseDTO;
import com.em.tag.services.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.em.tag.constants.EndPointsConstants.*;

@RestController
@RequestMapping(TAG_API)
public class TagController {
    @Autowired
    private TagService tagService;
    @PostMapping(ADD_TAG)
    public TagResponseDTO addTag(@RequestBody TagRequestDTO tagRequestDTO){
        return tagService.addTag(tagRequestDTO);
    }
}
