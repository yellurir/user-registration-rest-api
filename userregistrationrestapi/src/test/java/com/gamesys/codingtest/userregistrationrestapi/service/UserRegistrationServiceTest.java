package com.gamesys.codingtest.userregistrationrestapi.service;

import com.gamesys.codingtest.userregistrationrestapi.model.User;
import com.gamesys.codingtest.userregistrationrestapi.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserRegistrationServiceTest {

    @Autowired
    private UserRegistrationService userRegistrationService;

    @MockBean
    private UserRepository userRepository;

    @Test
    @DisplayName("Validate the service method creates user successfully into database")
    public void registerUserTest(){
        User user = new User();
        user.setUsername("thomas3dison");
        user.setPassword("ligHt6u18");
        user.setDateOfBirth("1847-02-11");
        user.setSsn("18081931");

        when(userRepository.save(user)).thenReturn(user);

        assertThat(userRegistrationService.registerUser(user)).isEqualTo(user);
    }
}

