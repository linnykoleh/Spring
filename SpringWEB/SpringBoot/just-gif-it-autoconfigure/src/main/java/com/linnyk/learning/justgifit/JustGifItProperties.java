package com.linnyk.learning.justgifit;

import java.io.File;

import javax.annotation.PostConstruct;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Setter
@ConfigurationProperties(prefix = "com.justgifit")
public class JustGifItProperties {

	/**
	 * The location of the animated gifs
	 */
	private File gifLocation;

	/**
	 * Whether or not to optimize web filters
	 */
	private Boolean optimize;

	@PostConstruct
	private void init(){
		log.debug("Initialising JustGifItProperties, gifLocation: {}, optimize: {}", gifLocation, optimize);
	}


}
