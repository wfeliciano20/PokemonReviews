package com.williamfeliciano.pokemonreviews.services.impl;

import com.williamfeliciano.pokemonreviews.dto.ReviewDTO;
import com.williamfeliciano.pokemonreviews.exceptions.PokemonNotFoundException;
import com.williamfeliciano.pokemonreviews.exceptions.ReviewNotFoundException;
import com.williamfeliciano.pokemonreviews.models.Pokemon;
import com.williamfeliciano.pokemonreviews.models.Review;
import com.williamfeliciano.pokemonreviews.repositories.PokemonRepository;
import com.williamfeliciano.pokemonreviews.repositories.ReviewRepository;
import com.williamfeliciano.pokemonreviews.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final PokemonRepository pokemonRepository;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository, PokemonRepository pokemonRepository) {
        this.reviewRepository = reviewRepository;
        this.pokemonRepository = pokemonRepository;

    }

    @Override
    public ReviewDTO createReview(int pokemonId, ReviewDTO reviewDto) {
        Pokemon pokemon = pokemonRepository.findById(pokemonId).orElseThrow(() -> new PokemonNotFoundException("Pokemon With Associated Review Not Found"));
        Review reviewToSave = mapToEntity(reviewDto);
        reviewToSave.setPokemon(pokemon);
        Review savedReview = reviewRepository.save(reviewToSave);
        return mapToDto(savedReview);
    }

    @Override
    public List<ReviewDTO> getReviewsByPokemonId(int id) {
        List<Review> reviews = reviewRepository.findByPokemonId(id);
        return reviews.stream().map(this::mapToDto).collect(Collectors.toList());
    }
    @Override
    public ReviewDTO getReviewById(int reviewId, int pokemonId) {
        Pokemon pokemon = pokemonRepository.findById(pokemonId).orElseThrow(() -> new PokemonNotFoundException("Pokemon With Associated Review Not Found"));
        Review review = reviewRepository.findById(reviewId).orElseThrow(() -> new ReviewNotFoundException("Review With Associated Pokemon Not Found"));
        if(review.getPokemon().getId() != pokemon.getId()){
            throw new ReviewNotFoundException("This Review Does Not Belong To A Pokemon");
        }
        return mapToDto(review);
    }

    @Override
    public ReviewDTO updateReview(int reviewId, int pokemonId, ReviewDTO reviewDto) {
        Pokemon pokemon = pokemonRepository.findById(pokemonId).orElseThrow(() -> new PokemonNotFoundException("Pokemon With Associated Review Not Found"));
        Review review = reviewRepository.findById(reviewId).orElseThrow(() -> new ReviewNotFoundException("Review With Associated Pokemon Not Found"));
        System.out.println("pokemon" + pokemon.getId());
        System.out.println("review" + review.getId());
        if(review.getPokemon().getId() != pokemon.getId()){
            throw new ReviewNotFoundException("This Review Does Not Belong To A Pokemon");
        }

        review .setTitle(reviewDto.getTitle());
        review.setContent(reviewDto.getContent());
        review.setStars(reviewDto.getStars());
        System.out.println("updated review "+ review.getTitle());

        Review updateReview = reviewRepository.save(review);
        return mapToDto(updateReview);
    }

    @Override
    public void deleteReview(int reviewId, int pokemonId) {
        Pokemon pokemon = pokemonRepository.findById(pokemonId).orElseThrow(() -> new PokemonNotFoundException("Pokemon With Associated Review Not Found"));
        Review review = reviewRepository.findById(reviewId).orElseThrow(() -> new ReviewNotFoundException("Review With Associated Pokemon Not Found"));
        if(review.getPokemon().getId() != pokemon.getId()){
            throw new ReviewNotFoundException("This Review Does Not Belong To A Pokemon");
        }
        reviewRepository.delete(review);
    }

    private ReviewDTO mapToDto(Review review) {
        ReviewDTO reviewDto = new ReviewDTO();
        reviewDto.setId(review.getId());
        reviewDto.setTitle(review.getTitle());
        reviewDto.setContent(review.getContent());
        reviewDto.setStars(review.getStars());
        return reviewDto;
    }

    private Review mapToEntity(ReviewDTO reviewDto) {
        Review review = new Review();
        review.setId(reviewDto.getId());
        review.setTitle(reviewDto.getTitle());
        review.setContent(reviewDto.getContent());
        review.setStars(reviewDto.getStars());
        return review;
    }
}
