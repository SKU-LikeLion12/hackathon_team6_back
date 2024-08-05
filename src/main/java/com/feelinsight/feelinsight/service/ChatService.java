package com.feelinsight.feelinsight.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.feelinsight.feelinsight.DTO.ChatDTO;
import com.feelinsight.feelinsight.domain.Chat;
import com.feelinsight.feelinsight.domain.User;
import com.feelinsight.feelinsight.exception.ChatNotFoundException;
import com.feelinsight.feelinsight.repository.ChatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ChatService {
    private final ChatRepository chatRepository;

    @Value("${django.server.url}")
    private String djangoServerUrl;

    @Transactional
    public void processChatData(ChatDTO.ChatTransfer chatTransfer) {
        try {
            Chat chat = convertToChat(chatTransfer);
            // 데이터베이스에 저장
            chatRepository.saveNewChat(chat);
        } catch (Exception e) {
            throw new RuntimeException("채팅 데이터를 처리하는 중 오류가 발생했습니다.", e);
        }
    }

    private Chat convertToChat(ChatDTO.ChatTransfer chatTransfer) {
        Chat entity = new Chat();
        entity.setChatId(chatTransfer.getUserId());
        entity.setMessage(chatTransfer.getMessage());
        entity.setStartTime(chatTransfer.getStartTime());
        entity.setEndTime(chatTransfer.getEndTime());
        return entity;
    }

    public Chat findByChatId(Long chatId){
        Chat chat = chatRepository.findByChatId(chatId);
        if(chat==null){
            throw new ChatNotFoundException("해당 ID의 대화 데이터를 찾을 수 없습니다.");
        }
        return chat;
    }

    public void sendFiletoDjangoServer(MultipartFile file, String userId) throws IOException{
        WebClient webClient=WebClient.builder()
                .baseUrl(djangoServerUrl)
                .build();
        ByteArrayResource byteArrayResource = new ByteArrayResource(file.getBytes()) {
            @Override
            public String getFilename() {
                return file.getOriginalFilename();
            }
        };

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", byteArrayResource);
        body.add("userId", userId);

        webClient.post()
                .uri("/upload/")
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .bodyValue(body)
                .retrieve()
                .onStatus(status -> status.is4xxClientError() || status.is5xxServerError(), clientResponse -> clientResponse.bodyToMono(String.class)
                        .flatMap(errorMessage -> {
                            throw new ResponseStatusException(clientResponse.statusCode(), errorMessage);
                        }))
                .bodyToMono(String.class)
                .block();


    }

}
