package com.williamfeliciano.pokemonreviews.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Review {

    private int id;
    private String title;
    private String content;
    private int stars;

    public Review(String title, String content, int stars) {
        this.title = title;
        this.content = content;
        this.stars = stars;
    }
}
