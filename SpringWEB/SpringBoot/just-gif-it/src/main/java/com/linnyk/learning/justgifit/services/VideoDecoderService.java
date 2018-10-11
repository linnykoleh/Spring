package com.linnyk.learning.justgifit.services;

import java.io.File;

import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.FrameGrabber;
import org.springframework.stereotype.Service;

@Service
public class VideoDecoderService {

    public FFmpegFrameGrabber read(File video) throws FrameGrabber.Exception {
        FFmpegFrameGrabber frameGrabber = new FFmpegFrameGrabber(video);
        frameGrabber.start();
        return frameGrabber;
    }

}
