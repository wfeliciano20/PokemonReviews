package com.williamfeliciano.pokemonreviews.repositories;

import com.williamfeliciano.pokemonreviews.models.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PokemonRepository extends JpaRepository<Pokemon, Integer> {
}
