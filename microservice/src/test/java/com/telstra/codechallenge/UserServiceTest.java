package com.telstra.codechallenge;

import com.telstra.codechallenge.exercises.domain.dto.UserDto;
import com.telstra.codechallenge.exercises.domain.exception.InternalSeverException;
import com.telstra.codechallenge.exercises.domain.exception.MethodArgumentNotValidException;
import com.telstra.codechallenge.exercises.domain.exception.UserNotFoundException;
import com.telstra.codechallenge.exercises.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
    private static long idCounter = 1;
    @InjectMocks
    UserService service = new UserService();

    @Mock
    RestTemplate restTemplate;

    @Mock
    private Environment env;
    @Value("${users.base.url}")
    private String baseUrl;

    @Test
    public void testGetUsers() {
        UserDto userObj = new UserDto();
        List<UserDto.Items> list = Arrays.asList(new UserDto.Items(++idCounter, "Jon", "https://github.com/jon001"),
                new UserDto.Items(++idCounter, "Rina", "https://github.com/engineyard"));
        userObj.setItems(list);
        when(restTemplate.getForObject(
                baseUrl + "/search/users?q=followers:0&sort=joined&order=asc&per_page=" + 2,
                UserDto.class)).thenReturn(userObj);
        UserDto user = service.getUsers(2);
        assertEquals(userObj, user);
        assertEquals(2, user.getItems().size());
    }

    @Test(expected = InternalSeverException.class)
    public void testInternalServerException() {
        UserDto userObj = new UserDto();
        List<UserDto.Items> list = Arrays.asList(new UserDto.Items(++idCounter, "Jon", "https://github.com/jon001"),
                new UserDto.Items(++idCounter, "Rina", "https://github.com/engineyard"));
        userObj.setItems(list);
        when(restTemplate.getForObject(
                baseUrl + "/search/users?q=followers:0&sort=joined&order=asc&per_page=" + 2,
                UserDto.class)).thenThrow(new InternalSeverException("Error while accessing Git API"));
        service.getUsers(2);
    }

    @Test(expected = MethodArgumentNotValidException.class)
    public void testMethodArgumentNotValidException() {
        int limit = 0;
        service.getUsers(limit);
    }

    @Test(expected = UserNotFoundException.class)
    public void testUserNotFoundException() {
        UserDto userObj = new UserDto();
        List<UserDto.Items> list = Arrays.asList();
        userObj.setItems(list);
        when(restTemplate.getForObject(
                baseUrl + "/search/users?q=followers:0&sort=joined&order=asc&per_page=" + 2,
                UserDto.class)).thenReturn(userObj);
        service.getUsers(2);

    }

    @Test(expected = UserNotFoundException.class)
    public void testExceptionForEmptyList() {
        when(restTemplate.getForObject(
                baseUrl + "/search/users?q=followers:0&sort=joined&order=asc&per_page=" + 2,
                UserDto.class)).thenReturn(null);
        service.getUsers(2);
    }

}
