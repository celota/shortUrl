package com.meli.urlshortener.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Document(collection = "urls")
@Data
public class UrlEntity {
	
	@Id
	private String id;
	
	private String url;
	
	private Boolean active;
	
//	@Indexed(expireAfterSeconds = 0)
//	private LocalDateTime expiresAt;

}
