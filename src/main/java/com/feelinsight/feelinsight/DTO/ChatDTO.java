package com.feelinsight.feelinsight.DTO;

import java.time.LocalDateTime;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor


public class ChatDTO {
    private Long userId;
    private String message;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Map<String, Integer> emotions;
    private Map<String, String> situation;

    // Getters and setters
    // …
}
