package com.feelinsight.feelinsight.DTO;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

public class FilleDTO {
    @Data
    public static class RequestFile{
        private MultipartFile file;
    }

}
