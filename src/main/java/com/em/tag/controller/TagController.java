package com.em.tag.controller;

import com.em.tag.dto.request_dto.TagRequestDTO;
import com.em.tag.dto.response_dto.AllTagResponseDTO;
import com.em.tag.dto.response_dto.TagResponseDTO;
import com.em.tag.services.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(GET_ALL_TAG)
    public AllTagResponseDTO getAllTag(){
        return tagService.getAllTag();
    }

    @GetMapping(GET_TAG_BY_ID+"/{id}")
    public TagResponseDTO getTagById(@PathVariable Integer id){
        return tagService.getTagById(id);
    }

    @DeleteMapping(DELETE_TAG_BY_ID+"/{id}")
    public TagResponseDTO deleteTagById(@PathVariable Integer id){
        return tagService.deleteTagById(id);
    }
}
