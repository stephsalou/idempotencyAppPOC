package com.df.myapp.service;

import com.df.myapp.service.dto.PostDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.df.myapp.domain.Post}.
 */
public interface PostService {
    /**
     * Save a post.
     *
     * @param postDTO the entity to save.
     * @return the persisted entity.
     */
    PostDTO save(PostDTO postDTO, String test, Long test2);

    /**
     * Updates a post.
     *
     * @param postDTO the entity to update.
     * @return the persisted entity.
     */
    PostDTO update(PostDTO postDTO);

    /**
     * Partially updates a post.
     *
     * @param postDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<PostDTO> partialUpdate(PostDTO postDTO);

    /**
     * Get all the posts.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<PostDTO> findAll(Pageable pageable);

    /**
     * Get all the posts with eager load of many-to-many relationships.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<PostDTO> findAllWithEagerRelationships(Pageable pageable);

    /**
     * Get the "id" post.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<PostDTO> findOne(Long id);

    /**
     * Delete the "id" post.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
