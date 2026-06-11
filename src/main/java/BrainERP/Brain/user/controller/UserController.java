package BrainERP.Brain.user.controller;

import BrainERP.Brain.user.dto.UserPatchDto;
import BrainERP.Brain.user.dto.UserRequestDto;
import BrainERP.Brain.user.dto.UserResponseDto;
import BrainERP.Brain.user.query.FindByEmailUserQuery;
import BrainERP.Brain.user.query.FindByIdUserQuery;
import BrainERP.Brain.user.query.ListUsersQuery;
import BrainERP.Brain.user.usecase.CreateUserUseCase;
import BrainERP.Brain.user.usecase.DeleteUserUseCase;
import BrainERP.Brain.user.usecase.UpdateUserUseCase;
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
    private final CreateUserUseCase createUserUseCase;
    private final UpdateUserUseCase updateUserUseCase;
    private final DeleteUserUseCase deleteUserUseCase;
    private final ListUsersQuery listUsersQuery;
    private final FindByIdUserQuery findByIdUserQuery;
    private final FindByEmailUserQuery findByEmailUserQuery;

    public UserController(CreateUserUseCase createUserUseCase,
                          UpdateUserUseCase updateUserUseCase,
                          DeleteUserUseCase deleteUserUseCase,
                          ListUsersQuery listUsersQuery,
                          FindByIdUserQuery findByIdUserQuery,
                          FindByEmailUserQuery findByEmailUserQuery)
    {
        this.createUserUseCase = createUserUseCase;
        this.updateUserUseCase = updateUserUseCase;
        this.deleteUserUseCase = deleteUserUseCase;
        this.listUsersQuery = listUsersQuery;
        this.findByIdUserQuery = findByIdUserQuery;
        this.findByEmailUserQuery = findByEmailUserQuery;
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

    @GetMapping("/search/{id}")
    public ResponseEntity<UserResponseDto>findUserId(
            @PathVariable Long id
    ){
        return ResponseEntity.ok(findByIdUserQuery.findUserByIdQuery(id));
    }

    @GetMapping("/search/email/{email}")
    public ResponseEntity<UserResponseDto>findUserEmail(
            @PathVariable String email
    ){
        return ResponseEntity.ok(findByEmailUserQuery.findUserByEmail(email));
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
