package experience.demo.Controller;

import experience.demo.Dto.LoginRequest;
import experience.demo.Dto.SingUpRequest;
import experience.demo.Dto.TokenResponseDto;
import experience.demo.Service.AuthService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    @PostMapping("/singUp")
    public ResponseEntity<?> singUp(@RequestBody SingUpRequest singUpRequest){
        return ResponseEntity.ok(authService.singUp(singUpRequest));
    }
    @PostMapping("/login")
    public ResponseEntity<TokenResponseDto> login(@RequestBody LoginRequest loginRequest){
        return ResponseEntity.ok(authService.login(loginRequest));
    }

}
