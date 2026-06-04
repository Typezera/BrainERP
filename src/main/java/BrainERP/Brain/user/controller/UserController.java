package BrainERP.Brain.user.controller;

import BrainERP.Brain.user.dto.UserPatchDto;
import BrainERP.Brain.user.dto.UserRequestDto;
import BrainERP.Brain.user.dto.UserResponseDto;
import BrainERP.Brain.user.query.ListUsersQuery;
import BrainERP.Brain.user.usecase.CreateUserUseCase;
import BrainERP.Brain.user.usecase.DeleteUserUseCase;
import BrainERP.Brain.user.usecase.UpdateUserUseCase;
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
    private final CreateUserUseCase createUserUseCase;
    private final UpdateUserUseCase updateUserUseCase;
    private final DeleteUserUseCase deleteUserUseCase;
    private final ListUsersQuery listUsersQuery;

    public UserController(CreateUserUseCase createUserUseCase,
                          UpdateUserUseCase updateUserUseCase,
                          DeleteUserUseCase deleteUserUseCase,
                          ListUsersQuery listUsersQuery)
    {
        this.createUserUseCase = createUserUseCase;
        this.updateUserUseCase = updateUserUseCase;
        this.deleteUserUseCase = deleteUserUseCase;
        this.listUsersQuery = listUsersQuery;
    }

    @PostMapping("/signup")
    public ResponseEntity<UserResponseDto>creatUser(
            @Valid
            @RequestBody
            UserRequestDto userRequestDto)
    {
        var user = createUserUseCase.createAccount(userRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserResponseDto>>getAllUsers(){
        return ResponseEntity.ok(listUsersQuery.searchUsers());
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<UserResponseDto>updatedUser(
            @PathVariable Long id,
            @RequestBody UserPatchDto userPatchDto
            ){
        return ResponseEntity.ok(updateUserUseCase.userPatch(id, userPatchDto));
    }

    @DeleteMapping("/deactivate/{id}")
    public ResponseEntity<String>deactivateUser (
            @PathVariable Long id
    ){
        deleteUserUseCase.deactivateUser(id);
        return ResponseEntity.ok("Sua conta foi desativada com sucesso");
    }

}
