package ru.bot.angrycards.api.imp;

import org.springframework.stereotype.Component;
import ru.bot.angrycards.dto.UserDTO;
import ru.bot.angrycards.api.BotState;
import ru.bot.angrycards.api.DataCache;

import java.util.HashMap;
import java.util.Map;

@Component
public class DefaultDataCache implements DataCache {
    private Map<Integer, BotState> usersBotStates = new HashMap<>();
    private static Map<Integer, UserDTO> users = new HashMap<>();

    @Override
    public void setBotStateCurrentForCurrentUser(int userId, BotState botState) {
        usersBotStates.put(userId, botState);
    }

    @Override
    public BotState getBotStateForCurrentUser(int userId) {
        BotState botState = usersBotStates.get(userId);
        if(botState == null){
            botState = BotState.FILLING_PLAYERS;
        }
        return botState;
    }

    @Override
    public UserDTO getUserDTO(int userId) {
        UserDTO userDTO = users.get(userId);
        if(userDTO == null){
            userDTO = new UserDTO();
        }
        return userDTO;
    }

    @Override
    public void setUserDTO(int userId, UserDTO user) {
        users.put(userId,user);
    }
}
