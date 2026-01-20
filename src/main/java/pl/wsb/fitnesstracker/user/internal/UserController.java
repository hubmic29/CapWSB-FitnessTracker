package pl.wsb.fitnesstracker.user.internal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pl.wsb.fitnesstracker.user.api.BasicUserDto;
import pl.wsb.fitnesstracker.user.api.User;
import pl.wsb.fitnesstracker.user.api.UserDto;
import pl.wsb.fitnesstracker.user.api.UserIdEmail;
import java.util.List;
import java.time.LocalDate;
import org.springframework.web.bind.annotation.*;
/**
 * UserController is responsible for handling HTTP requests related to user operations.
 * It provides endpoints for retrieving and creating users.
 */
@RestController
@RequestMapping("/v1/users")
class UserController {

    private final UserServiceImpl userService;

    private final UserMapper userMapper;

    public UserController(UserServiceImpl userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.findAllUsers()
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    @GetMapping("/simple")
    public List<BasicUserDto> getAllBasicUsersData(){
        return userService.findAllUsersWithBasicInformation();
    }

    @GetMapping("/{id}")
    public UserDto getAllBasicUsersData(@PathVariable Long id){
        return userService.findUserById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody UserDto userDto) {
        return userService.createUser(userDto);
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long userId){
        userService.deleteUser(userId);
    }

    @GetMapping("/email")
    public List<UserIdEmail> findUsersByEmail(@RequestParam String email) {
        return userService.findUserByEmail(email);
    }

    @PutMapping("/{userId}")
    public void updateUser(@PathVariable Long userId, @RequestBody UserDto userDto) {
        userService.updateUser(userId, userDto);

    }

    @GetMapping("/older/{time}")
    public List<UserDto> findAllUserOlderThan(@PathVariable LocalDate time){
        return userService.findAllUsersOlderThan(time);
    }


}

