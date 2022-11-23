package com.williamfeliciano.pokemonreviews.controllers;

import com.williamfeliciano.pokemonreviews.dto.ReviewDTO;
import com.williamfeliciano.pokemonreviews.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ReviewController {

    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/pokemon/{pokemonId}/review")
    public ResponseEntity<ReviewDTO> createReview(@PathVariable(value = "pokemonId")int pokemonId, @RequestBody ReviewDTO reviewDTO){
        ReviewDTO review = reviewService.createReview(pokemonId,reviewDTO);
        return new ResponseEntity<>(review, HttpStatus.CREATED);
    }

    @GetMapping("/pokemon/{pokemonId}/review")
    public ResponseEntity<List<ReviewDTO>> getReviewsByPokemonId(@PathVariable(value = "pokemonId") int pokemonId) {
        List<ReviewDTO> reviews = reviewService.getReviewsByPokemonId(pokemonId);
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    @GetMapping("/pokemon/{pokemonId}/review/{id}")
    public ResponseEntity<ReviewDTO> getReviewById(@PathVariable(value = "pokemonId")int pokemonId, @PathVariable(value = "id") int reviewId) {
        ReviewDTO review = reviewService.getReviewById(reviewId,pokemonId);
        return new ResponseEntity<>(review, HttpStatus.OK);
    }

    @PutMapping("/pokemon/{pokemonId}/review/{id}")
    public ResponseEntity<ReviewDTO> updateReview(@PathVariable(value = "pokemonId")int pokemonId, @PathVariable(value = "id") int reviewId, @RequestBody ReviewDTO reviewDTO){
        ReviewDTO updatedReview = reviewService.updateReview(reviewId,pokemonId,reviewDTO);
        return new ResponseEntity<>(updatedReview, HttpStatus.OK);
    }

    @DeleteMapping("/pokemon/{pokemonId}/review/{id}")
    public ResponseEntity<String> deleteReview(@PathVariable(value = "pokemonId")int pokemonId, @PathVariable(value = "id")int reviewId){
        reviewService.deleteReview(reviewId,pokemonId);
        return new ResponseEntity<>("Review Deleted Successfully",HttpStatus.NO_CONTENT);
    }

}
