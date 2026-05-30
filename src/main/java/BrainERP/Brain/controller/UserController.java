package BrainERP.Brain.controller;

import BrainERP.Brain.UserDTOs.UserRequestDto;
import BrainERP.Brain.UserDTOs.UserResponseDto;
import BrainERP.Brain.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@RequestMapping("api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<UserResponseDto>creatUser(
            @Valid
            @RequestBody
            UserRequestDto userRequestDto){
        var user = userService.CreateAccount(userRequestDto);
//        return ResponseEntity.ok(userService.CreateAccount(userRequestDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserResponseDto>>getAllUsers(){
        return ResponseEntity.ok(userService.searchUsers());
    }
}
