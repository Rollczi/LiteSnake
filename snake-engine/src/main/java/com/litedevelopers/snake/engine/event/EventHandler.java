package com.litedevelopers.snake.engine.event;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class EventHandler {

    private final Map<Class<?>, Set<Listener>> listenersByType = new HashMap<>();

    public void registerListener(Listener listener) {
        for (Method method : listener.getClass().getDeclaredMethods()) {
            Subscribe subscribe = method.getAnnotation(Subscribe.class);

            if (subscribe == null) {
                continue;
            }

            Parameter[] parameters = method.getParameters();

            if (parameters.length != 1) {
                throw new IllegalStateException("Listener with @Subscribe must have only one parameter!");
            }

            Parameter parameter = parameters[0];
            Class<?> type = parameter.getType();

            if (!Event.class.isAssignableFrom(type)) {
                throw new IllegalStateException("Parameter in the method must be an event!");
            }

            Set<Listener> listeners = listenersByType.computeIfAbsent(type, key -> new HashSet<>());

            listeners.add(listener);
        }
    }

    public void call(Event event) {
        Set<Listener> listeners = this.listenersByType.get(event.getClass());

        if (listeners == null) {
            return;
        }

        for (Listener listener : listeners) {
            for (Method method : listener.getClass().getDeclaredMethods()) {
                Subscribe subscribe = method.getAnnotation(Subscribe.class);

                if (subscribe == null) {
                    continue;
                }

                Parameter parameter = method.getParameters()[0];
                Class<?> type = parameter.getType();

                if (!event.getClass().equals(type)) {
                    continue;
                }

                try {
                    method.setAccessible(true);
                    method.invoke(listener, event);
                }
                catch (IllegalAccessException | InvocationTargetException exception) {
                    throw new RuntimeException(exception);
                }
            }
        }
    }

}
