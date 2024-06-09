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
//
//import ru.netology.cloud_service.repository.RoleRepository;
//
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static ru.netology.cloud_service.TestData.*;
//
//@ExtendWith(MockitoExtension.class)
//@MockitoSettings(strictness = Strictness.LENIENT)
//class RoleServiceTest {
//    @InjectMocks
//    private RoleService roleService;
//
//    @Mock
//    private RoleRepository roleRepository;
//
//    @Test
//    void findRoleByName() {
//        Mockito.when(roleRepository.findByName(ROLE_USER.getName())).thenReturn(Optional.of(ROLE_USER));
//        assertEquals(ROLE_USER, roleService.findRoleByName(ROLE_USER.getName()).get());
//    }
//}