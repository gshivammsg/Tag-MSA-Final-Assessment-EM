package com.em.tag.services;
import com.em.tag.dto.request_dto.TagRequestDTO;
import com.em.tag.dto.response_dto.AllTagResponseDTO;
import com.em.tag.dto.response_dto.GetSyllabusByTagIdResponseDTO;
import com.em.tag.dto.response_dto.SyllabusResponseDTO;
import com.em.tag.dto.response_dto.TagResponseDTO;
import com.em.tag.entity.SyllabusTagMappingEntity;
import com.em.tag.entity.TagEntity;
import com.em.tag.feign.SyllabusServiceClient;
import com.em.tag.repository.SyllabusTagMappingRepository;
import com.em.tag.repository.TagRepository;
import com.em.tag.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.em.tag.constants.Constants.ACTIVE;
import static com.em.tag.constants.Constants.IN_ACTIVE;
import static com.em.tag.constants.ResponseConstants.*;

@Service
public class TagServiceImpl implements TagService{
    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private SyllabusTagMappingRepository syllabusTagMappingRepository;

    @Autowired
    private SyllabusServiceClient syllabusServiceClient;


    @Override
    public TagResponseDTO addTag(TagRequestDTO tagRequestDTO) {
        TagEntity tagEntity  = tagRepository.findByTagName(tagRequestDTO.getTagName());
        if(tagEntity != null){
            if(tagEntity.getIsActive().equals(IN_ACTIVE)){
                tagEntity.setIsActive(ACTIVE);
                tagRepository.save(tagEntity);
                return TagResponseDTO.builder()
                        .id(tagEntity.getTagId())
                        .tagName(tagEntity.getTagName())
                        .status(HttpStatus.CREATED.value())
                        .message(HttpStatus.OK.toString())
                        .currentServerTime(Utils.getCurrentServerTime())
                        .build();
            } else {
                return TagResponseDTO.builder()
                        .status(HttpStatus.CONFLICT.value())
                        .message(TAG_ALREADY_EXIST)
                        .currentServerTime(Utils.getCurrentServerTime())
                        .build();
            }
        }
        else{
            tagEntity = TagEntity.builder()
                    .tagName(tagRequestDTO.getTagName())
                    .build();
            tagRepository.save(tagEntity);
            return TagResponseDTO.builder()
                    .id(tagEntity.getTagId())
                    .tagName(tagEntity.getTagName())
                    .status(HttpStatus.OK.value())
                    .message(HttpStatus.OK.toString())
                    .currentServerTime(Utils.getCurrentServerTime())
                    .build();
        }
    }

    @Override
    public AllTagResponseDTO getAllTag() {
        List<TagEntity> allTagEntity = tagRepository.findByIsActive(ACTIVE);
        if(allTagEntity.isEmpty()){
            return AllTagResponseDTO.builder()
                    .status(HttpStatus.NOT_FOUND.value())
                    .message(TAG_NOT_FOUND)
                    .currentServerTime(Utils.getCurrentServerTime())
                    .build();
        }

        List<TagResponseDTO> allTagResponseDTO = new ArrayList<>();
        for (TagEntity tagEntity:allTagEntity){
            allTagResponseDTO.add(TagResponseDTO.builder()
                            .tagName(tagEntity.getTagName())
                            .id(tagEntity.getTagId())
                    .build());
        }
        return AllTagResponseDTO.builder()
                .tagData(allTagResponseDTO)
                .status(HttpStatus.OK.value())
                .message(HttpStatus.OK.toString())
                .currentServerTime(Utils.getCurrentServerTime())
                .build();
    }

    @Override
    public TagResponseDTO getTagById(Integer id) {
        TagEntity tagEntity = tagRepository.findByTagIdAndIsActive(id, ACTIVE);
        if(tagEntity == null){
            return TagResponseDTO.builder()
                    .status(HttpStatus.NOT_FOUND.value())
                    .message(TAG_NOT_FOUND)
                    .currentServerTime(Utils.getCurrentServerTime())
                    .build();
        }
        return TagResponseDTO.builder()
                .status(HttpStatus.OK.value())
                .message(HttpStatus.OK.toString())
                .currentServerTime(Utils.getCurrentServerTime())
                .id(tagEntity.getTagId())
                .tagName(tagEntity.getTagName())
                .build();
    }

    @Override
    public TagResponseDTO deleteTagById(Integer id) {
        List<SyllabusTagMappingEntity> syllabusTagMappingEntity = syllabusTagMappingRepository.findByTagIdAndIsActive(id,ACTIVE);
        if(!syllabusTagMappingEntity.isEmpty()){
            return TagResponseDTO.builder()
            .status(HttpStatus.NOT_FOUND.value())
            .message(TAG_NOT_DELETED)
            .currentServerTime(Utils.getCurrentServerTime())
            .build();
        }
        TagEntity tagEntity = tagRepository.findByTagIdAndIsActive(id, ACTIVE);
        if(tagEntity == null){
            return TagResponseDTO.builder()
                    .status(HttpStatus.NOT_FOUND.value())
                    .message(TAG_NOT_FOUND)
                    .currentServerTime(Utils.getCurrentServerTime())
                    .build();
        }
        tagEntity.setIsActive(IN_ACTIVE);
        tagRepository.save(tagEntity);
        return TagResponseDTO.builder()
                .status(HttpStatus.OK.value())
                .message(HttpStatus.OK.toString())
                .currentServerTime(Utils.getCurrentServerTime())
                .build();
    }

    @Override
    public GetSyllabusByTagIdResponseDTO getSyllabusByTagIdResponseDTO(Integer id) {
        List<SyllabusTagMappingEntity> syllabusTagMappingEntityList =  syllabusTagMappingRepository.findByTagIdAndIsActive(id,ACTIVE);
        if(syllabusTagMappingEntityList.isEmpty()){
            return GetSyllabusByTagIdResponseDTO.builder()
                    .status(HttpStatus.NOT_FOUND.value())
                    .message(SYLLABUS_TAG_ASSOCIATION_NOT_FOUND)
                    .currentServerTime(Utils.getCurrentServerTime())
                    .build();
        }
        List<SyllabusResponseDTO> syllabusResponseDTOList = new ArrayList<>();
        for (SyllabusTagMappingEntity data:syllabusTagMappingEntityList){
            syllabusResponseDTOList.add(syllabusServiceClient.getSyllabusById(data.getSyllabusId()));
        }

        return GetSyllabusByTagIdResponseDTO.builder()
                .syllabusList(syllabusResponseDTOList)
                .status(HttpStatus.OK.value())
                .message(HttpStatus.OK.toString())
                .currentServerTime(Utils.getCurrentServerTime())
                .build();
    }
}