package com.em.tag.controller;

import com.em.tag.dto.request_dto.TagRequestDTO;
import com.em.tag.dto.response_dto.AllTagResponseDTO;
import com.em.tag.dto.response_dto.GetSyllabusByTagIdResponseDTO;
import com.em.tag.dto.response_dto.TagResponseDTO;
import com.em.tag.services.TagService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import static com.em.tag.constants.EndPointsConstants.*;

@RestController
@RequestMapping(TAG_API)
@CrossOrigin("*")
public class TagController {
    @Autowired
    private TagService tagService;
    @PostMapping(ADD_TAG)
    public TagResponseDTO addTag(@RequestBody @Valid TagRequestDTO tagRequestDTO){
        return tagService.addTag(tagRequestDTO);
    }

    @GetMapping(GET_ALL_TAG)
    public AllTagResponseDTO getAllTag(){
        return tagService.getAllTag();
    }

    @Cacheable(key = "#id", value = "getTagById")
    @GetMapping(GET_TAG_BY_ID+"/{id}")
    public TagResponseDTO getTagById(@PathVariable Integer id){
        return tagService.getTagById(id);
    }

    @CacheEvict(key = "#id", value = "getTagById")
    @DeleteMapping(DELETE_TAG_BY_ID+"/{id}")
    public TagResponseDTO deleteTagById(@PathVariable Integer id){
        return tagService.deleteTagById(id);
    }

    @GetMapping(GET_SYLLABUS_BY_TAG_ID+"/{id}")
    public GetSyllabusByTagIdResponseDTO getSyllabusByTagIdResponseDTO(@PathVariable Integer id) {
        return tagService.getSyllabusByTagIdResponseDTO(id);
    }

    @CacheEvict(value = "getTagById", allEntries = true)
    @GetMapping(REMOVE_CACHE)
    public String removeCache(){
        return "Cache Removed From Tag MSA";
    }
}
