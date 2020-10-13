package com.github.hanyaeger.api.engine;

import com.google.inject.Injector;

/**
 * Stating an {@code Object} is {@link Initializable} exposes the {@code init} lifecycle hook.
 */
public interface Initializable {

    /**
     * @param injector the {@link Injector} used for Dependency Injection
     */
    void init(final Injector injector);

    /**
     * A default method to be used as a lifecycle hook to be called after initialization has been done.
     * By default
     */
    default void afterInit(){};
}
