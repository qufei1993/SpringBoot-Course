package com.angelo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    /**
     * 扩展，通过名字查询
     * @param name
     * @return
     */
    public List<User> findByName(String name);
}
