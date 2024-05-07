package com.restapi.file.domian;

import lombok.Data;

@Data
public class FileUpload {

    private String uploadFileName;
    private String storeFileName;

    public FileUpload(String uploadFileName, String storeFileName) {
        this.uploadFileName = uploadFileName;
        this.storeFileName = storeFileName;
    }
}
