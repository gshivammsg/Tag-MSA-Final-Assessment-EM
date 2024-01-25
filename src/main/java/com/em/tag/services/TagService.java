package com.em.tag.services;

import com.em.tag.dto.request_dto.TagRequestDTO;
import com.em.tag.dto.response_dto.TagResponseDTO;

public interface TagService {
    public TagResponseDTO addTag(TagRequestDTO tagRequestDTO);
}
