package com.github.hanyaeger.core.factories.video;

import com.google.inject.Singleton;
import javafx.scene.media.Media;

@Singleton
public class VideoFactory {
    public Media create(String url) {
        return new Media(url);
    }
}
