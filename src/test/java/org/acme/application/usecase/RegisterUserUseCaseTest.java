package org.acme.application.usecase;

import org.acme.application.dto.RegisterUserDto;
import org.acme.domain.models.User;
import org.acme.domain.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RegisterUserUseCaseTest {

    private UserRepository userRepository;
    private RegisterUserUseCase useCase;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        when(userRepository.create(any(User.class))).thenAnswer(inv -> inv.getArgument(0));
        useCase = new RegisterUserUseCase(userRepository);
    }

    @Test
    void executeShouldPersistUserWithFirebaseUuid() {
        RegisterUserDto dto = new RegisterUserDto();
        dto.setEmail("alice@test.com");
        dto.setFullName("Alice Smith");
        dto.setFirebaseUuid("firebase-uid-123");

        User result = useCase.execute(dto);

        assertNotNull(result);
        assertEquals("alice@test.com", result.getEmail());
        assertEquals("Alice Smith", result.getFullName());
        assertEquals("USER", result.getRole());
        assertTrue(result.isActive());
        assertEquals("firebase-uid-123", result.getFirebaseUuid());
        assertNotNull(result.getId());
    }

}
