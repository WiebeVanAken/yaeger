package com.github.hanyaeger.core.repositories;

import com.github.hanyaeger.core.Destroyable;
import com.github.hanyaeger.core.ResourceConsumer;
import com.github.hanyaeger.core.factories.video.VideoFactory;
import com.google.inject.Inject;
import javafx.scene.media.Media;

import java.util.Map;
import java.util.WeakHashMap;

public class VideoRepository implements ResourceConsumer, Destroyable {
    private final Map<String, Media> videoMap = new WeakHashMap<>();
    private VideoFactory factory;

    public Media get(final String url) {
        if(videoMap.containsKey(url)) {
            return videoMap.get(url);
        }

        var video = factory.create(url);
        videoMap.put(url, video);
        return video;
    }

    public int size() {
        return videoMap.size();
    }

    @Override
    public void destroy() {
        videoMap.clear();
    }

    @Inject
    public void setFactory(VideoFactory factory) {
        factory = factory;
    }
}
