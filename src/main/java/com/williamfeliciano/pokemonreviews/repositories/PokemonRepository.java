package com.williamfeliciano.pokemonreviews.repositories;

import com.williamfeliciano.pokemonreviews.models.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PokemonRepository extends JpaRepository<Pokemon, Integer> {
}
