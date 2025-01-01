package com.meli.urlshortener.exception;

import java.util.List;

import lombok.Builder;


@Builder
//public record UrlShortenerError(@JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss") LocalDateTime timestamp,Integer code,String status,List<String> errors)
public record UrlShortenerError(Integer code,String status,List<String> errors)
{

}
