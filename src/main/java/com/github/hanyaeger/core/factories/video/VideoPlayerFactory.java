package com.github.hanyaeger.core.factories.video;

import com.google.inject.Singleton;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

@Singleton
public class VideoViewFactory {
    public MediaPlayer create(final Media media) {
        var videoPlayer = new MediaPlayer(media);
        videoPlayer.setAutoPlay(true);

        return videoPlayer;
    }
}
