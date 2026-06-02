package BrainERP.Brain.user.controller;

import BrainERP.Brain.user.dto.UserPatchDto;
import BrainERP.Brain.user.dto.UserRequestDto;
import BrainERP.Brain.user.dto.UserResponseDto;
import BrainERP.Brain.user.service.UserService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
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
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserResponseDto>>getAllUsers(){
        return ResponseEntity.ok(userService.searchUsers());
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<UserResponseDto>updatedUser(
            @PathVariable Long id,
            @RequestBody UserPatchDto userPatchDto
            ){
        return ResponseEntity.ok(userService.userPatch(id, userPatchDto));
    }

    @DeleteMapping("/deactivate/{id}")
    public ResponseEntity<String>deactivateUser (
            @PathVariable Long id
    ){
        userService.deactivateUser(id);
        return ResponseEntity.ok("Sua conta foi desativada com sucesso");
    }

}
