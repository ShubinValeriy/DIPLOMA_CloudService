//package ru.netology.cloud_service.service;
//
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.mockito.junit.jupiter.MockitoSettings;
//import org.mockito.quality.Strictness;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import ru.netology.cloud_service.model.dtos.request.JwtRequest;
//import ru.netology.cloud_service.repository.AuthenticationRepository;
//import ru.netology.cloud_service.utils.JWT.JwtTokenUtil;
//
//import java.util.Optional;
//
//
//import static org.junit.jupiter.api.Assertions.*;
//import static ru.netology.cloud_service.TestData.*;
//
//@ExtendWith(MockitoExtension.class)
//@MockitoSettings(strictness = Strictness.LENIENT)
//class AuthenticationServiceTest {
//
//    @InjectMocks
//    private AuthenticationService authenticationService;
//
//    @Mock
//    private AuthenticationRepository authenticationRepository;
//
//    @Mock
//    private  AuthenticationManager authenticationManager;
//
//
//    @Mock
//    private JwtTokenUtil jwtTokenUtil;
//
//    @Mock
//    private UserService userService;
//
//    @Test
//    void login() {
//        Mockito.when(userService.findUserByUserName(USERNAME)).thenReturn(Optional.of(USER));
//        Mockito.when(jwtTokenUtil.generatedToken(USER)).thenReturn(TOKEN);
//        Mockito.when(authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
//                USERNAME,
//                PASSWORD
//        ))).thenReturn(new UsernamePasswordAuthenticationToken(
//                        USERNAME,
//                        PASSWORD
//        ));
//        assertEquals(TOKEN, authenticationService.login(USERNAME, PASSWORD));
//        Mockito.verify(authenticationRepository, Mockito.times(1)).putAuth(TOKEN, USERNAME);
//    }
//}