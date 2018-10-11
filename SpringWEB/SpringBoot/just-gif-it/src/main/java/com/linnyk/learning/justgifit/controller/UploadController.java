package com.linnyk.learning.justgifit.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.FrameGrabber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.linnyk.learning.justgifit.services.ConverterService;
import com.linnyk.learning.justgifit.services.GifEncoderService;
import com.linnyk.learning.justgifit.services.VideoDecoderService;
import com.madgag.gif.fmsware.AnimatedGifEncoder;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class UploadController {

	@Value("${spring.servlet.multipart.location}")
	private String location;

	private final ConverterService converterService;
	private final GifEncoderService gifEncoderService;
	private final VideoDecoderService videoDecoderService;

	@Autowired
	public UploadController(ConverterService converterService, GifEncoderService gifEncoderService, VideoDecoderService videoDecoderService) {
		this.converterService = converterService;
		this.gifEncoderService = gifEncoderService;
		this.videoDecoderService = videoDecoderService;
	}

	@GetMapping
	@RequestMapping("/")
	public String home() {
		return "Just-Gif-It";
	}

	/**
	 * curl -F file=@/home/olinnyk/IdeaProjects/Spring/SpringWEB/SpringBoot/just-get-it/video/PexelsVideos.mp4 -F start=0 -F end=5 -F speed=1 -F repeat=0 localhost:8080/upload
	 */
	@PostMapping
	@RequestMapping(value = "/upload", produces = MediaType.IMAGE_GIF_VALUE)
	public String upload(@RequestPart("file") MultipartFile file,
			@RequestParam("start") int start,
			@RequestParam("end") int end,
			@RequestParam("speed") int speed,
			@RequestParam("repeat") boolean repeat) throws IOException, FrameGrabber.Exception {

		final File videoFile = new File(location + "/" + System.currentTimeMillis() + ".mp4");
		file.transferTo(videoFile);

		log.info("Saved video file to {}", videoFile.getAbsolutePath());

		final Path output = Paths.get(location + "/gif/" + System.currentTimeMillis() + ".gif");

		final FFmpegFrameGrabber frameGrabber = videoDecoderService.read(videoFile);
		final AnimatedGifEncoder gifEncoder = gifEncoderService.getGifEncoder(repeat, (float) frameGrabber.getFrameRate(), output);
		converterService.toAnimatedGif(frameGrabber, gifEncoder, start, end, speed);

		log.info("Saved generated gif to {}", output.toString());

		return output.getFileName().toString();
	}
}
