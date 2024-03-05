package com.projpba.projpba.Repository;

import com.projpba.projpba.DTO.LoginDto;

public interface AuthService {
    String login(LoginDto loginDto);
}
