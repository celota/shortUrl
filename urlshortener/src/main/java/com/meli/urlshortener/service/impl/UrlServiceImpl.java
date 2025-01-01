package com.meli.urlshortener.service.impl;

import java.net.URI;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import com.meli.urlshortener.domain.dto.url.UrlRequestDTO;
import com.meli.urlshortener.domain.dto.url.UrlResponseDTO;
import com.meli.urlshortener.domain.dto.url.UrlRequestDTOIdNameActive;
import com.meli.urlshortener.entities.UrlEntity;
import com.meli.urlshortener.exception.UrlNotFoundException;
import com.meli.urlshortener.repository.UrlRepository;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UrlServiceImpl implements com.meli.urlshortener.service.UrlService {
	
	private static String BASE_URL ="http://localhost:8080/";
	
	private UrlRepository urlRepository;

	@Override
	public UrlResponseDTO shortenUrl(UrlRequestDTO data, HttpServletRequest request) {
        String id;
		
		do {
            id = RandomStringUtils.randomAlphabetic(5);
        } while (urlRepository.existsById(id));
		
		//urlRepository.save(new UrlEntity(id, data.url(), LocalDateTime.now().plusMinutes(1)));
		urlRepository.save(new UrlEntity(id, data.url(),true));
		
		String redirectUrl = request.getRequestURL().toString().replace("shorten-url", id);
		
		return new UrlResponseDTO(data.url(), redirectUrl,true);
	}

	@Override
	public HttpHeaders redirect(String id) {
		UrlEntity url = urlRepository.findById(id)
                .orElseThrow(() -> new UrlNotFoundException(id));
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(url.getUrl()));
        return headers;
	}

	@Override
	public UrlResponseDTO enable(String id) {
		UrlEntity url = urlRepository.findById(id)
                .orElseThrow(() -> new UrlNotFoundException(id));
		url.setActive(true);
		//urlRepository.save(new UrlEntity(id, url.getUrl(),url.getActive()));
		urlRepository.save(url);
		//String localhost = "http://localhost:8081/";
		return new UrlResponseDTO(url.getUrl(), BASE_URL + url.getId() ,url.getActive());
	}

	@Override
	public UrlResponseDTO disable(String id) {
		UrlEntity url = urlRepository.findById(id)
                .orElseThrow(() -> new UrlNotFoundException(id));
		url.setActive(false);
		//urlRepository.save(new UrlEntity(id, url.getUrl(),url.getActive()));
		urlRepository.save(url);
		//String localhost = "http://localhost:8081/";
		return new UrlResponseDTO(url.getUrl(), BASE_URL + url.getId() ,url.getActive());
	}

	@Override
	public UrlResponseDTO modify(UrlRequestDTOIdNameActive data, HttpServletRequest request) {
		UrlEntity url = urlRepository.findByIdAndUrl(data.id(),data.nameUrl())
                .orElseThrow(() -> new UrlNotFoundException(data.id()));
		url.setActive(data.active());
		url.setUrl(data.newNameUrl());
		urlRepository.save(url);
		//String localhost = "http://localhost:8081/";
		return new UrlResponseDTO(url.getUrl(), BASE_URL + url.getId() ,url.getActive());
	}

}
