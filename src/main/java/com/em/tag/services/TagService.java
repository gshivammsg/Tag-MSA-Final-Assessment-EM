package com.em.tag.services;

import com.em.tag.dto.request_dto.TagRequestDTO;
import com.em.tag.dto.response_dto.AllTagResponseDTO;
import com.em.tag.dto.response_dto.TagResponseDTO;

public interface TagService {
    public TagResponseDTO addTag(TagRequestDTO tagRequestDTO);

    public AllTagResponseDTO getAllTag();

    public TagResponseDTO getTagById(Integer id);
    public TagResponseDTO deleteTagById(Integer id);
}
