package com.github.hanyaeger.api.engine.entities.entity;

import com.github.hanyaeger.api.engine.entities.entity.events.system.RemoveEntityEvent;

/**
 * A {@code Removable} denotes an {@code Object}, most likely an {@link YaegerEntity}, that is eligible for
 * removing from its parent.
 */
public interface Removable extends GameNode {

    /**
     * Perform all necessary actions to remove the entity.
     */
    void remove();

    /**
     * Send a {@link javafx.event.Event} to notify all {@code Listeners} that this {@link Removable}
     * should be removed.
     */
    default void notifyRemove() {
        var removeEvent = new RemoveEntityEvent(this);
        getNode().ifPresent(node -> node.fireEvent(removeEvent));
    }
}
