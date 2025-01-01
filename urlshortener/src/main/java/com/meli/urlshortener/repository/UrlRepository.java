package com.meli.urlshortener.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.meli.urlshortener.entities.UrlEntity;

public interface UrlRepository extends MongoRepository<UrlEntity, String> {
	
	Optional<UrlEntity> findByIdAndUrl(String id, String name);

}
