package ru.itis.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.models.Message;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MessageDto {

    private String text;

    private String senderName;

    private String timeSent;

    public static MessageDto from(Message message) {
        return MessageDto.builder()
                .text(message.getText())
                .timeSent(message.getTimeSent().toString())
                .senderName(message.getSender().getName())
                .build();
    }
}
