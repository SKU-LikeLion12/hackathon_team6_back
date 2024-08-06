package com.feelinsight.feelinsight.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.feelinsight.feelinsight.DTO.ChatDTO;
import com.feelinsight.feelinsight.domain.Chat;
import com.feelinsight.feelinsight.domain.User;
import com.feelinsight.feelinsight.exception.ChatNotFoundException;
import com.feelinsight.feelinsight.repository.ChatRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ChatService {
    private final UserService userService;
    private final ChatRepository chatRepository;

    @Value("${django.server.url}")
    private String djangoServerUrl;

    @Transactional
    public void processChatData(ChatDTO.ChatTransfer chatTransfer, String userId) {
        try {
            Chat chat = convertToChat(chatTransfer,userId);
            // 데이터베이스에 저장
            chatRepository.saveNewChat(chat);
        } catch (Exception e) {
            throw new RuntimeException("채팅 데이터를 처리하는 중 오류가 발생했습니다.", e);
        }
    }

    private Chat convertToChat(ChatDTO.ChatTransfer chatTransfer, String userId) {
        Chat entity = new Chat();
        entity.setUser(userService.tokenToUser(chatTransfer.getToken()));
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

//    public void sendFiletoDjangoServer(byte[] fileBytes) throws IOException {
//        WebClient webClient = WebClient.builder()
//                .baseUrl(djangoServerUrl)
//                .build();
//
//        // POST 요청 본문에 바이트 배열을 설정
//        String response = webClient.post()
//                .uri("/transcribe/")
//                .contentType(MediaType.APPLICATION_OCTET_STREAM) // 바이트 스트림 전송을 위한 MIME 타입
//                .bodyValue(fileBytes)
//                .retrieve()
//                .onStatus(status -> status.is4xxClientError() || status.is5xxServerError(), clientResponse -> clientResponse.bodyToMono(String.class)
//                        .flatMap(errorMessage -> {
//                            throw new ResponseStatusException(clientResponse.statusCode(), errorMessage);
//                        }))
//                .bodyToMono(String.class)
//                .block();
//
//        ObjectMapper mapper = new ObjectMapper();
//        ProcessedData processedData = mapper.readValue(response, ProcessedData.class);
//
//        User user = new User(); // 필요에 따라 User 객체를 조회해야 할 수 있음
//        // User 객체를 적절히 설정하세요
//
//        Chat chat = new Chat(user, processedData.getMessage());
//        chatRepository.saveNewChat(chat);
//    }

//    public void sendFileToDjangoServer(byte[] fileBytes, Long userId) throws IOException {
//        WebClient webClient = WebClient.builder()
//                .baseUrl(djangoServerUrl)
//                .build();
//
//        MultipartBodyBuilder bodyBuilder = new MultipartBodyBuilder();
//        bodyBuilder.part("file", new ByteArrayResource(fileBytes) {
//            @Override
//            public String getFilename() {
//                return "file.wav"; // 적절한 파일 이름으로 변경하세요
//            }
//        });
//
//
//
//        String response = webClient.post()
//                .uri("/transcribe/")
//                .contentType(MediaType.MULTIPART_FORM_DATA)
//                .body(BodyInserters.fromMultipartData(bodyBuilder.build()))
//                .retrieve()
//                .onStatus(status -> status.is4xxClientError() || status.is5xxServerError(),
//                        clientResponse -> clientResponse.bodyToMono(String.class)
//                                .flatMap(errorMessage -> {
//                                    throw new ResponseStatusException(clientResponse.statusCode(), errorMessage);
//                                }))
//                .bodyToMono(String.class)
//                .block();
//
//        ObjectMapper mapper = new ObjectMapper();
//        ProcessedData processedData = mapper.readValue(response, ProcessedData.class);
//
//        User user = userService.findByUserId(userId); // 필요에 따라 User 객체를 조회해야 할 수 있음
//
//        Chat chat = new Chat(user, processedData.getMessage());
//        chatRepository.saveNewChat(chat);
//    }


    @Getter @Setter
    private static class ProcessedData{
        private String userId;
        private int fileLength;
        private String message;
    }

}
