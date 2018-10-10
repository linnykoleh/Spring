package com.linnyk.learning.justgifit.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class UploadController {

	@Value("${spring.servlet.multipart.location}")
	private String location;

	@GetMapping
	@RequestMapping("/")
	public String home(){
		return "Just-Gif-It";
	}

	/**
	 * curl -F file=@/home/olinnyk/IdeaProjects/Spring/SpringWEB/SpringBoot/PexelsVideos.mp4 -F start=0 -F end=0 -F speed=1 -F repeat=0 localhost:8080/upload
	 */
	@PostMapping
	@RequestMapping(value = "/upload", produces = MediaType.IMAGE_GIF_VALUE)
	public String upload(@RequestParam("file") MultipartFile file,
			@RequestParam("start") int start,
			@RequestParam("end") int end,
			@RequestParam("speed") int speed,
			@RequestParam("repeat") boolean repeat) throws IOException {

		final File videoFile = new File(location + "/" + System.currentTimeMillis() + ".mp4");
		file.transferTo(videoFile);

		log.debug("Saved file to {}", videoFile.getAbsolutePath());
		return "";
	}
}
