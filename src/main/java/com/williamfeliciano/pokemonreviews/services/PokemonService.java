package com.williamfeliciano.pokemonreviews.services;

import com.williamfeliciano.pokemonreviews.dto.PokemonDTO;

import java.util.List;

public interface PokemonService {
    PokemonDTO createPokemon(PokemonDTO pokemonDTO);
    List<PokemonDTO> getAllPokemon();
}
