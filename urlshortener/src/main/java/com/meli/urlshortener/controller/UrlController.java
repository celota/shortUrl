package com.meli.urlshortener.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.meli.urlshortener.domain.dto.url.UrlRequestDTO;
import com.meli.urlshortener.domain.dto.url.UrlResponseDTO;
import com.meli.urlshortener.domain.dto.url.UrlRequestDTOIdNameActive;
import com.meli.urlshortener.service.UrlService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class UrlController {
	
	private UrlService urlService;
	
	//curl -v -X POST http://localhost:8080/shorten-url -d '{"url":"http://www.google.com"}' -H 'Content-type: application/json'
	@PostMapping("/shorten-url")
	public ResponseEntity<UrlResponseDTO> shortenUrl(@RequestBody UrlRequestDTO data, HttpServletRequest request){
        return ResponseEntity.ok(urlService.shortenUrl(data,request));
    }
	
	@GetMapping("/{id}")
    public ResponseEntity<Void> redirect(@PathVariable("id") String id) {
        HttpHeaders headers = urlService.redirect(id);
        return ResponseEntity.status(HttpStatus.FOUND).headers(headers).build();
    }
	
	//curl -v -X PATCH http://localhost:8080/enable/'
	@PatchMapping("/enable/{id}")
    public ResponseEntity<UrlResponseDTO> enable(@PathVariable("id") String id) {
		return ResponseEntity.ok(urlService.enable(id));
    }
	
	//curl -v -X PATCH http://localhost:8080/disable/'
	@PatchMapping("/disable/{id}")
    public ResponseEntity<UrlResponseDTO> disable(@PathVariable("id") String id) {
        return ResponseEntity.ok(urlService.disable(id));
    }
	
	//curl -v -X PATCH http://localhost:8080/modify -d '{"id":"ZazbL","nameUrl": "http://www.google.com","newNameUrl": "http://lubuntu.com","active": true}' -H 'Content-type: application/json'
	@PatchMapping("/modify")
    public ResponseEntity<UrlResponseDTO> modify(@RequestBody UrlRequestDTOIdNameActive data,HttpServletRequest request) {
        return ResponseEntity.ok(urlService.modify(data,request));
    }
	
	

}
