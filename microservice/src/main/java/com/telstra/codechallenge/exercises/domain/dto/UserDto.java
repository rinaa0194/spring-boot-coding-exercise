package com.telstra.codechallenge.exercises.domain.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class UserDto {


    @JsonProperty("items")
    private List<Items> items;

    @JsonIgnoreProperties(ignoreUnknown = true)
    @Data
    public static class Items {
        private Long id;
        private String login;
        @JsonProperty("html_url")
        private String htmlUrl;

        @JsonCreator
        public Items(@JsonProperty("idCounter") long idCounter, @JsonProperty("login") String login,
                     @JsonProperty("html_url") String htmlUrl) {
            super();
            this.id = idCounter;
            this.login = login;
            this.htmlUrl = htmlUrl;
        }
    }
}
