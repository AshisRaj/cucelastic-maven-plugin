/*
 * Copyright 2018 Ashis Raj
 */

package com.araj.cucumber.elasticsearch.utils;

import java.time.Duration;

public class CucElasticPluginUtils {
    private static final int MICROSECOND_FACTOR = 1000000;

    /**
     * Convert microseconds to a human readable time string.
     *
     * @param microseconds The amount of microseconds.
     * @return The human readable string representation.
     */
    public static String convertMicrosecondsToTimeString(final long microseconds) {
        Duration durationMilliseconds = Duration.ofMillis(microseconds / MICROSECOND_FACTOR);
        long minutes = durationMilliseconds.toMinutes();
        long seconds = durationMilliseconds.minusMinutes(minutes).getSeconds();
        long milliseconds = durationMilliseconds.minusMinutes(minutes).minusSeconds(seconds).toMillis();
        return String.format("%dm %02ds %03dms", minutes, seconds, milliseconds);
    }

    /**
     * Convert microseconds to milliseconds.
     *
     * @param microseconds The amount of microseconds.
     * @return The millisecond representation.
     */
    public static long convertMicrosecondsToMilliseconds(final long microseconds) {
        return Duration.ofMillis(microseconds / MICROSECOND_FACTOR).toMillis();
    }

    /**
     * Return the current Cucumber version.
     *
     * @return The version string.
     */
    public static String getPluginVersion() {
        String version = CucElasticPluginUtils.class.getPackage().getImplementationVersion();
        if (version == null) {
            version = "unknown";
        }
        return version;
    }

    /**
     * Escape HTML tags in a string.
     *
     * @param sourceString The source string.
     * @return The escaped string.
     */
    public static String escapeHTML(final String sourceString) {
        StringBuilder stringBuilder = new StringBuilder(Math.max(16, sourceString.length()));
        for (int i = 0; i < sourceString.length(); i++) {
            char character = sourceString.charAt(i);
            if (character > 127 || character == '"' || character == '<' || character == '>' || character == '&') {
                stringBuilder.append("&#");
                stringBuilder.append((int) character);
                stringBuilder.append(';');
            } else {
                stringBuilder.append(character);
            }
        }
        return stringBuilder.toString();
    }
}
