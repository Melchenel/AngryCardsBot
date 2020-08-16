package ru.bot.angrycards.dto;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Players {
    public static Map<Integer, UserDTO> players = new HashMap<>();
}
