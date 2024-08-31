package com.github.sniconmc.config;

import java.util.List;

/**
 * Represents the display configuration for a momentum area.
 */
public class MomentumDisplay {

    private boolean show_text;
    private List<List<String>> text_list;

    /**
     * Checks if text should be shown.
     * @return true if text should be shown, false otherwise.
     */
    public boolean isShow_text() {
        return show_text;
    }

    /**
     * Gets the list of text to be displayed.
     * @return A List of List of Strings representing the text to be displayed.
     */
    public List<List<String>> getText_list() {
        return text_list;
    }
}