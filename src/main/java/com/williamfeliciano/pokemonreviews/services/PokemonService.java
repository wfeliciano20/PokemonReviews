package com.williamfeliciano.pokemonreviews.services;

import com.williamfeliciano.pokemonreviews.dto.PokemonDTO;
import com.williamfeliciano.pokemonreviews.dto.PokemonResponse;



public interface PokemonService {
    PokemonDTO createPokemon(PokemonDTO pokemonDTO);
    PokemonResponse getAllPokemon(int pageNo, int pageSize);
    PokemonDTO getPokemonById(Integer id);
    PokemonDTO updatePokemon(Integer id,PokemonDTO pokemonDTO);
    void deletePokemon(Integer id);
}
