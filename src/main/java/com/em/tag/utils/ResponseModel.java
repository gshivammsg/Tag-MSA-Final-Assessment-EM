package com.em.tag.utils;


import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * The type Response model.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"status", "current_server_time", "message", "response"})
public class ResponseModel {

    @JsonProperty("status")
    private Integer status;

    @JsonProperty("current_server_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String currentServerTime;

    private String message;

    private String messageCode;
}

