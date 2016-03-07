package com.gem.support.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;

public interface BaseService<DTO, ID extends Serializable> {
    void create(DTO dto);
    void update(DTO dto);
    void delete(ID id);
    DTO findOne(ID id);
    Page<DTO> findAll(Pageable pageable);

}
