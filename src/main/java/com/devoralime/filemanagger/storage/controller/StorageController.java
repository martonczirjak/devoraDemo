package com.devoralime.filemanagger.storage.controller;

import com.devoralime.filemanagger.storage.dto.CreateStorageDto;
import com.devoralime.filemanagger.storage.dto.StorageDetailDto;
import com.devoralime.filemanagger.storage.mapper.StorageMapper;
import com.devoralime.filemanagger.storage.service.IStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/storages")
public class StorageController {

    @Autowired
    private IStorageService storageService;

    @Autowired
    private StorageMapper storageMapper;

    @PostMapping
    public StorageDetailDto create(@RequestBody @Valid CreateStorageDto dto){
        return storageMapper.toDto(storageService.create(dto));
    }

    @GetMapping
    public List<StorageDetailDto> storageList(){
        return storageService.findAll().stream().map(it->storageMapper.toDto(it)).collect(Collectors.toList());
    }
}
