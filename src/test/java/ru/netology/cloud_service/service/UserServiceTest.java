//package ru.netology.cloud_service.service;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.mockito.junit.jupiter.MockitoSettings;
//import org.mockito.quality.Strictness;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import ru.netology.cloud_service.repository.UserRepository;
//
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static ru.netology.cloud_service.TestData.*;
//
//@ExtendWith(MockitoExtension.class)
//@MockitoSettings(strictness = Strictness.LENIENT)
//class UserServiceTest {
//    @InjectMocks
//    private UserService userService;
//    @Mock
//    private  UserRepository userRepository;
//
//    @BeforeEach
//    void setUp() {
//        Mockito.when(userService.findUserByUserName(USERNAME)).thenReturn(Optional.of(USER));
//
//    }
//
//    @Test
//    void findUserByUserName() {
//        assertEquals(USER, userService.findUserByUserName(USERNAME).get());
//        Mockito.verify(userRepository, Mockito.times(1))
//                .findByUserName(USERNAME);
//    }
//
//    @Test
//    void loadUserByUsername() {
//        assertEquals(
//                new org.springframework.security.core.userdetails.User(
//                        USERNAME,
//                        PASSWORD,
//                        ONE_ROLE_USER.stream()
//                                .map(role -> new SimpleGrantedAuthority(role.getName()))
//                                .collect(Collectors.toList())
//                ),
//                userService.loadUserByUsername(USERNAME));
//    }
//
//    @Test
//    void UsernameNotFoundExceptionLoadUserByUsername() {
//        assertThrows(UsernameNotFoundException.class, () -> userService.loadUserByUsername(TOKEN));
//    }
//
//}