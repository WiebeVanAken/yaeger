package com.github.hanyaeger.api.entities.impl;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.YaegerEntity;
import com.github.hanyaeger.core.ResourceConsumer;
import com.github.hanyaeger.core.factories.video.VideoFactory;
import com.github.hanyaeger.core.factories.video.VideoPlayerFactory;
import com.github.hanyaeger.core.factories.video.VideoViewFactory;
import com.github.hanyaeger.core.repositories.VideoRepository;
import com.google.inject.Inject;
import com.google.inject.Injector;
import javafx.scene.Node;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.util.Optional;

public abstract class VideoEntity extends YaegerEntity implements ResourceConsumer {
    private final String resource;
    private Size size;

    private VideoFactory videoFactory;
    private VideoPlayerFactory playerFactory;
    private VideoViewFactory viewFactory;
    private VideoRepository repository;

    private Optional<MediaView> videoView;
    private Optional<MediaPlayer> videoPlayer;


    /**
     * Create a new {@code YaegerEntity} on the given {@link Coordinate2D}.
     *
     * @param initialLocation the initial {@link Coordinate2D} of this {@link YaegerEntity}
     * @param resource
     */
    protected VideoEntity(final String resource, final Coordinate2D initialLocation) {
        super(initialLocation);
        this.resource = resource;
    }

    /**
     * Create a new {@code YaegerEntity} on the given {@link Coordinate2D}.
     *
     * @param initialLocation the initial {@link Coordinate2D} of this {@link YaegerEntity}
     * @param resource the url of the image file. Relative to the resources folder
     * @param size the bounding box of this {@link VideoEntity}
     */
    protected VideoEntity(final String resource, final Coordinate2D initialLocation, final Size size) {
        super(initialLocation);
        this.resource = resource;
        this.size = size;
    }

    public void start() {
        if(videoPlayer.isPresent())
            videoPlayer.get().play();
    }

    public void stop() {
        if(videoPlayer.isPresent())
            videoPlayer.get().stop();
    }

    public void pause() {
        if(videoPlayer.isPresent())
            videoPlayer.get().pause();
    }

    @Override
    public void beforeInitialize() {
        super.beforeInitialize();
    }

    @Override
    public void init(final Injector injector) {
        if(size == null) {
            size = new Size(64, 64);
        }

        videoView = Optional.ofNullable(viewFactory.create(videoPlayer.get()));

        super.init(injector);
    }

    @Override
    public String createPathForResource(String resource) {
        return ResourceConsumer.super.createPathForResource(resource);
    }

    @Override
    public Optional<? extends Node> getNode() {
        return videoView;
    }

    @Inject
    public void setVideoRepository(final VideoRepository repository) {
        this.repository = repository;
    }

    @Inject
    public void setVideoFactories(final VideoFactory videoFactory, final VideoPlayerFactory playerFactory, final VideoViewFactory viewFactory) {
        this.videoFactory = videoFactory;
        this.playerFactory = playerFactory;
        this.viewFactory = viewFactory;
    }

    private MediaView createVideoView(final String resource) {
        final var video = repository.get(resource);
        videoPlayer = Optional.ofNullable(playerFactory.create(video));

        return viewFactory.create(videoPlayer.get());
    }
}
