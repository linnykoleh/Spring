package com.linnyk.learning.justgifit.services;

import java.nio.file.Path;

import org.springframework.stereotype.Service;

import com.madgag.gif.fmsware.AnimatedGifEncoder;

@Service
public class GifEncoderService {

    public AnimatedGifEncoder getGifEncoder(boolean repeat, float frameRate, Path output) {
        AnimatedGifEncoder gifEncoder = new AnimatedGifEncoder();

        if (repeat) {
            gifEncoder.setRepeat(0);
        }

        gifEncoder.setFrameRate(frameRate);
        gifEncoder.start(output.toString());
        return gifEncoder;
    }

}
