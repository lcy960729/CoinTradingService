
package com.cy.tradingbot.controller.user;

import com.cy.tradingbot.configure.security.JwtTokenProvider;
import com.cy.tradingbot.domain.user.User;
import com.cy.tradingbot.domain.user.service.UserService;
import com.cy.tradingbot.dto.user.RequestUserDTO;
import com.cy.tradingbot.dto.user.UserDTO;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(produces = MediaTypes.HAL_JSON_VALUE)
public class UserController {
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;

    public UserController(UserService userService, JwtTokenProvider jwtTokenProvider) {
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping(path = "/join", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> join(@RequestBody RequestUserDTO requestUserDTO) {
        userService.join(requestUserDTO);

        return ResponseEntity.ok().body(null);
    }

    @PostMapping(path = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> login(@RequestBody RequestUserDTO requestUserDTO) {
        UserDTO userDTO = userService.validate(requestUserDTO);

        return ResponseEntity.ok().header("Authorization", jwtTokenProvider.createToken(userDTO)).body(userDTO);
    }

    @GetMapping(path = "/api/v1/myInformation")
    public ResponseEntity<UserDTO> get(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok().body(userService.get(user));
    }
}
