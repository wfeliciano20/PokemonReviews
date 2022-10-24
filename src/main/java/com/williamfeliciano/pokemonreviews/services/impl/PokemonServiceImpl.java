package com.williamfeliciano.pokemonreviews.services.impl;

import com.williamfeliciano.pokemonreviews.dto.PokemonDTO;
import com.williamfeliciano.pokemonreviews.models.Pokemon;
import com.williamfeliciano.pokemonreviews.repositories.PokemonRepository;
import com.williamfeliciano.pokemonreviews.services.PokemonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PokemonServiceImpl implements PokemonService {

    private final PokemonRepository pokemonRepository;

    @Override
    public PokemonDTO createPokemon(PokemonDTO pokemonDTO) {
        Pokemon pokemon;
        pokemon = pokemonDTO.toPokemon(pokemonDTO);
        Pokemon savedPokemon = pokemonRepository.save(pokemon);
        return pokemonDTO.fromPokemon(savedPokemon);
    }
}
