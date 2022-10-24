package com.williamfeliciano.pokemonreviews.repositories;

import com.williamfeliciano.pokemonreviews.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
}
