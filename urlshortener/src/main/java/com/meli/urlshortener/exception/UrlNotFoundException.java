package com.meli.urlshortener.exception;

import lombok.Getter;

@Getter
public class UrlNotFoundException extends RuntimeException {
	private final String urlId;

    public UrlNotFoundException(String urlId) {
        
    	super(String.format("Url %s not found ", urlId));
        this.urlId = urlId;
    }

}
