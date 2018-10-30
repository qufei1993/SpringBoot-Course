package com.angelo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    /**
     * 查询用户列表
     * @return
     */
    @GetMapping(value = "/user/list")
    public List<User> userList() {
        return userRepository.findAll();
    }

    @GetMapping(value = "/user/info")
    public User findUserById(@RequestParam("id") String id) {
        return userRepository.findById(id);
    }

    /**
     * 创建用户信息
     */
    @PostMapping(value = "/user")
    public User createUser(@RequestBody User params) {
        User user = new User();
        user.setName(params.getName());
        user.setAge(params.getAge());
        user.setIdCard(params.getIdCard());

        return userRepository.save(user);
    }


    /**
     * 更新用户信息
     */
    @PutMapping(value = "/user/{id}")
    public User updateUser(@PathVariable("id") String id, @RequestParam("name") String name, @RequestParam("age") Integer age,
        @RequestParam("idCard") String idCard) {
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setAge(age);
        user.setIdCard(idCard);

        return userRepository.save(user);
    }

    /**
     * 删除用户信息 deleteById(java.lang.long) in CurdRepository cannot be applied to (java.lang.string)
     */
    @DeleteMapping(value = "/user/{id}")
    public void deleteUserById(@PathVariable("id") String id) {
        userRepository.deleteById(id);
    }
}
