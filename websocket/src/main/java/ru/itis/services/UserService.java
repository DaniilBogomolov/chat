package ru.itis.services;

import ru.itis.models.User;

public interface UserService {
    User findUserByCookie(String cookie);
}
