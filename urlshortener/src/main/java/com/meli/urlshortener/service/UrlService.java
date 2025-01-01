package com.meli.urlshortener.service;

import org.springframework.http.HttpHeaders;

import com.meli.urlshortener.domain.dto.url.UrlRequestDTO;
import com.meli.urlshortener.domain.dto.url.UrlResponseDTO;
import com.meli.urlshortener.domain.dto.url.UrlRequestDTOIdNameActive;

import jakarta.servlet.http.HttpServletRequest;

public interface UrlService {
	
	UrlResponseDTO shortenUrl(UrlRequestDTO data, HttpServletRequest request);

    HttpHeaders redirect(String id);
    
    UrlResponseDTO enable(String id);
    
    UrlResponseDTO disable(String id);
    
    UrlResponseDTO modify(UrlRequestDTOIdNameActive data, HttpServletRequest request);

}
