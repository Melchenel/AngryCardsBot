package ru.bot.angrycards.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserDTO {
    String name;
    Boolean isLeader;
    Integer count;
    String blackCart;
    List<String> whiteCards;
}
