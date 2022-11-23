package com.williamfeliciano.pokemonreviews.services;

import com.williamfeliciano.pokemonreviews.dto.ReviewDTO;

import java.util.List;

public interface ReviewService {

    ReviewDTO createReview(int pokemonId, ReviewDTO reviewDto);

    List<ReviewDTO> getReviewsByPokemonId(int id);

    ReviewDTO getReviewById(int reviewId, int pokemonId);

    ReviewDTO updateReview(int reviewId,int pokemonId,ReviewDTO reviewDto);

    void deleteReview(int reviewId, int pokemonId);


}