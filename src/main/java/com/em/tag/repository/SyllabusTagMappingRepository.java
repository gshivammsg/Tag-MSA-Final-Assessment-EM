package com.em.tag.repository;

import com.em.tag.entity.SyllabusTagMappingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SyllabusTagMappingRepository extends JpaRepository<SyllabusTagMappingEntity, Integer> {
    List<SyllabusTagMappingEntity> findByTagIdAndIsActive(Integer tagId, Integer isActive);
}
