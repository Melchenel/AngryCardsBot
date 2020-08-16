package ru.bot.angrycards.api;

import ru.bot.angrycards.dto.UserDTO;

public interface DataCache {
    void setBotStateCurrentForCurrentUser(int userId, BotState botState); //назначить для пользователя конктретное состояние бота
    BotState getBotStateForCurrentUser(int userId);
    UserDTO getUserDTO(int userId);
    void setUserDTO(int userId, UserDTO user);

}
