package com.telstra.codechallenge.exercise.service;

import com.telstra.codechallenge.exercise.domain.dto.UserDto;
import com.telstra.codechallenge.exercise.domain.exception.InternalSeverException;
import com.telstra.codechallenge.exercise.domain.exception.MethodArgumentNotValidException;
import com.telstra.codechallenge.exercise.domain.exception.UserNotFoundException;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Log
@Service
public class UserService {

/*    @Autowired
    private Environment env;*/

    @Value("${users.base.url}")
    private String baseUrl;

    @Autowired
    private RestTemplate restTemplate;

    public UserDto getUsers(Integer limit) {
        log.info("Inside @serviceMethod getUsers @param limit:" + limit);

        if (limit <= 0)
            throw new MethodArgumentNotValidException(
                    "Number of accounts to return should be greater than zero");

        UserDto user = null;
        try {

            user = restTemplate.getForObject(baseUrl
                    + "/search/users?q=followers:0&sort=joined&order=asc&per_page="
                    + limit, UserDto.class);

        } catch (Exception e) {
            throw new InternalSeverException("Error while accessing Git API");
        }

        if (Objects.isNull(user))
            throw new UserNotFoundException("Git API response is null or empty:" + limit);

        else if (CollectionUtils.isEmpty(user.getItems()))
            throw new UserNotFoundException("Requested number of accounts:" + limit);

        return user;
    }

}
