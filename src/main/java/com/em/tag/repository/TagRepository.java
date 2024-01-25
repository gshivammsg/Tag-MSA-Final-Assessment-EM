package com.em.tag.repository;

import com.em.tag.entity.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagRepository extends JpaRepository<TagEntity, Integer> {
    List<TagEntity> findByIsActive(Integer isActive);

    TagEntity findByIdAndIsActive(Integer id, Integer isActive);
}
