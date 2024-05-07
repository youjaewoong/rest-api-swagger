package com.restapi.file.util;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;

@UtilityClass
@Slf4j
public class FileDirectoryCreate {

    /**
     * 주어진 경로에 파일을 생성합니다. 필요한 디렉토리도 생성합니다.
     * @param filePath 생성할 파일의 경로
     * @return 파일 생성 성공 여부를 반환합니다.
     */
    public static boolean createFileWithNecessaryDirectories(String filePath) throws IOException {
        File file = new File(filePath);
        if (ensureDirectoriesExist(file.getParentFile())) {
            return createFile(file);
        }
        return false;
    }

    /**
     * 필요한 모든 상위 디렉토리를 생성합니다.
     * @param directory 파일의 상위 디렉토리
     * @return 디렉토리 생성 성공 여부
     */
    private static boolean ensureDirectoriesExist(File directory) throws IOException {
        if (!directory.exists()) {
            if (directory.mkdirs()) {
                log.debug("Directories created successfully.");
                return true;
            } else {
                log.error("Failed to create directories.");
                throw new IOException();
            }
        }
        return true;
    }

    /**
     * 파일을 생성합니다.
     * @param file 생성할 파일
     * @return 파일 생성 성공 여부
     */
    private static boolean createFile(File file) throws IOException {
        if (!file.exists()) {
            try {
                if (file.createNewFile()) {
                    log.debug("File created successfully.");
                    return true;
                } else {
                    log.error("Failed to create the file.");
                    throw new IOException();
                }
            } catch (IOException e) {
                log.error("Error while creating the file.");
                e.printStackTrace();
                throw new IOException();
            }
        } else {
            log.debug("File already exists.");
            return false;
        }
    }
}
