package com.em.tag.services;
import com.em.tag.dto.request_dto.TagRequestDTO;
import com.em.tag.dto.response_dto.TagResponseDTO;
import com.em.tag.entity.TagEntity;
import com.em.tag.repository.TagRepository;
import com.em.tag.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import static com.em.tag.constants.Constants.ACTIVE;

@Service
public class TagServiceImpl implements TagService{
    @Autowired
    private TagRepository tagRepository;
    @Override
    public TagResponseDTO addTag(TagRequestDTO tagRequestDTO) {
        TagEntity tagEntity = TagEntity.builder()
                .tagName(tagRequestDTO.getTagName())
                .isActive(ACTIVE)
                .build();
        tagRepository.save(tagEntity);
        return TagResponseDTO.builder()
                .id(tagEntity.getId())
                .tagName(tagEntity.getTagName())
                .status(HttpStatus.OK.value())
                .message(HttpStatus.OK.toString())
                .currentServerTime(Utils.getCurrentServerTime())
                .build();
    }
}