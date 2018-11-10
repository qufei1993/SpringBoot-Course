package com.angelo.controller;

import com.angelo.domain.Result;
import com.angelo.domain.User;
import com.angelo.enums.MessageEnum;
import com.angelo.exception.UserException;
import com.angelo.repository.UserRepository;
import com.angelo.util.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    /**
     * 保存一个用户
     * @param name
     * @param age
     * @return
     */
    @PostMapping(value = "/user")
    public User userAdd(@RequestBody User userParams) throws Exception {
        User user = new User();
        user.setName(userParams.getName());
        user.setAge(userParams.getAge());

        if (userParams.getName().length() > 4) {
            throw new UserException(MessageEnum.NAME_EXCEEDED_CHARRACTER_LIMIT);
        }

        return userRepository.save(user);
    }

    /**
     * 查询用户列表
     * @return
     */
    @RequestMapping(value = "/user/list/{exception}")
    public Result<User> userList(@PathVariable("exception") Boolean exception) {
        if (exception) {
            return null; // 测试空指针异常
        }

        return MessageUtil.success(userRepository.findAll());
    }

    /**
     * 根据id查找一个用户
     * @param id
     * @return
     */
    @RequestMapping(value = "/user/{id}")
    public Optional<User> userFindOne(@PathVariable("id") Integer id) {
        return userRepository.findById(id);
    }

    /**
     * 根据name获取用户信息
     * @param name
     * @return
     */
    @RequestMapping(value = "/user/name", method = RequestMethod.GET)
    public List<User> findUserListByName(@RequestParam(name="name",defaultValue="") String name) {
        return userRepository.findByName(name);
    }

    /**
     * 更新用户信息
     * @param id
     * @param name
     * @param age
     * @return
     */
    @PutMapping(value = "/user/{id}")
    public User userUpdate(
            @PathVariable("id") Integer id,
            @RequestParam("name") String name,
            @RequestParam("age") Integer age
    ) {
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setAge(age);

        return userRepository.save(user);
    }

    /**
     * 删除一个用户信息
     * @param id
     */
    @DeleteMapping(value = "/user/{id}")
    public void deleteUser(@PathVariable("id") Integer id) {
        userRepository.deleteById(id);
    }
}
