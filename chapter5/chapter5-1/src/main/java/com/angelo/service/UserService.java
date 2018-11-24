package com.angelo.service;

import com.angelo.domain.User;
import com.angelo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * 根据id查询用户信息
     * @param id
     * @return
     */
    public User findById(Integer id) {
        return userRepository.getOne(id);
    }
}
