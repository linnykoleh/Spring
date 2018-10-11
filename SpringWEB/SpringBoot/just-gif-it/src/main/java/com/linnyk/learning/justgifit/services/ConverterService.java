package com.linnyk.learning.justgifit.services;

import java.awt.image.BufferedImage;

import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.springframework.stereotype.Service;

import com.madgag.gif.fmsware.AnimatedGifEncoder;

@Service
public class ConverterService {

    public void toAnimatedGif(FFmpegFrameGrabber frameGrabber, AnimatedGifEncoder
            gifEncoder, int start, int end, int speed) throws FrameGrabber.Exception {

        final long startFrame = Math.round(start * frameGrabber.getFrameRate());
        final long endFrame = Math.round(end * frameGrabber.getFrameRate());

        final Java2DFrameConverter frameConverter = new Java2DFrameConverter();

        for (long i = startFrame; i < endFrame; i++) {

            if (i % speed == 0) {

                // Bug if frameNumber is set to 0
                if (i > 0) {
                    frameGrabber.setFrameNumber((int) i);
                }

                final BufferedImage bufferedImage = frameConverter.convert(frameGrabber.grabImage());
                gifEncoder.addFrame(bufferedImage);
            }

        }

        frameGrabber.stop();
        gifEncoder.finish();
    }

}
