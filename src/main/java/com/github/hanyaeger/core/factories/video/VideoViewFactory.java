package com.github.hanyaeger.core.factories.video;

import com.github.hanyaeger.api.Size;
import com.google.inject.Singleton;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

@Singleton
public class VideoViewFactory {
    public MediaView create(MediaPlayer player) {
        return new MediaView(player);
    }
}
