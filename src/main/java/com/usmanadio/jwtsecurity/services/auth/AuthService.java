package com.usmanadio.jwtsecurity.services.auth;

import com.usmanadio.jwtsecurity.models.user.User;

public interface AuthService {
    String signUpUser(User user);
    String signInUser(String email, String password);
}
