package com.blogproject.core.models;


/**
 * Interface for the Footer component model.
 */
public interface FooterModel {

    /**
     * Returns the home page link.
     * @return The home page link.
     */
    String getLink1();

    /**
     * Returns the published blogs link.
     * @return The published blogs link.
     */
    String getLink2();

    /**
     * Returns the current year for copyright notice.
     * @return The current year.
     */

    int getCurrentYear();
}

