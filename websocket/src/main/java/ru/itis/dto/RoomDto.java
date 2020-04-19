package ru.itis.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.models.Room;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoomDto {

    private String name;
    private String link;

    public static RoomDto from(Room room) {
        return RoomDto.builder()
                .name(room.getOriginalName())
                .link(room.getGeneratedName())
                .build();
    }
}
