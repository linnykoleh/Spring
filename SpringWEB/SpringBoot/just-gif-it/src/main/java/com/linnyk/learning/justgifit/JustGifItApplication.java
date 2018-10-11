package com.linnyk.learning.justgifit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import com.linnyk.learning.justgifit.properties.JustGifItProperties;
import com.linnyk.learning.justgifit.services.ConverterService;
import com.linnyk.learning.justgifit.services.GifEncoderService;
import com.linnyk.learning.justgifit.services.VideoDecoderService;

@SpringBootApplication //By default scans the package which @SpringBootApplication class has
@EnableConfigurationProperties(JustGifItProperties.class)
public class JustGifItApplication {

	public static void main(String[] args) {
		SpringApplication.run(JustGifItApplication.class, args);
	}

	@Bean
	@ConditionalOnMissingBean(VideoDecoderService.class)
	public VideoDecoderService videoDecoderService() {
		return new VideoDecoderService();
	}

	@Bean
	@ConditionalOnMissingBean(GifEncoderService.class)
	public GifEncoderService gifEncoderService() {
		return new GifEncoderService();
	}

	@Bean
	@ConditionalOnMissingBean(ConverterService.class)
	public ConverterService converterService() {
		return new ConverterService();
	}

}
