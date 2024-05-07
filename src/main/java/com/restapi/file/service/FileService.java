package com.restapi.file.service;

import com.restapi.file.domian.FileItem;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class FileService {

    private final Map<Long, FileItem> store = new HashMap<>();
    private long sequence = 0L;

    public FileItem fileSave(FileItem fileItem) {
        fileItem.setId(++sequence);
        store.put(fileItem.getId(), fileItem);
        return fileItem;
    }

    public FileItem fileFindById(Long id) {
        return store.get(id);
    }
}
