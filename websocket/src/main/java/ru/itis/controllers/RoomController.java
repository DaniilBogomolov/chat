package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.itis.dto.CreateRoomDto;
import ru.itis.repositories.RoomRepository;
import ru.itis.services.RoomService;
import ru.itis.services.UserService;

@Controller
@RequestMapping("/room")
public class RoomController {

    @Autowired
    private RoomService roomService;


    @GetMapping
    public String getRoomsPage(@CookieValue(name = "Authorization", required = false) String cookieValue,
                               ModelMap modelMap) {
        modelMap.addAttribute("rooms", roomService.getRooms());
        return "rooms_page";
    }

    @GetMapping("/{room}")
    public String getRoom(@PathVariable String room,
                          @CookieValue(name = "Authorization", required = false) String cookieValue,
                          ModelMap modelMap) {
        modelMap.addAttribute("room", roomService.getRoomByGeneratedName(room));
        modelMap.addAttribute("userCookie", cookieValue);
        return "room";
    }


    @PostMapping
    public String createRoom(@CookieValue(name = "Authorization", required = false) String cookieValue,
                             CreateRoomDto createRoomDto) {
        roomService.createRoom(createRoomDto);
        return "redirect:/room";
    }

}
