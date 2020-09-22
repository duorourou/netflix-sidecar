package com.github.duorourou.sidecar.videoservice;

public final class InvalidDurationException extends Exception {

    private InvalidDurationException(Long duration) {
        super(String.format("Invalid duration %d", duration));
    }

    public static InvalidDurationException raise(Long duration) {
        return new InvalidDurationException(duration);
    }
}
