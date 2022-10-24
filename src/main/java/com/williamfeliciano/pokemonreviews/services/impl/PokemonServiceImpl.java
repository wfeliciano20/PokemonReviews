package com.williamfeliciano.pokemonreviews.services.impl;

import com.williamfeliciano.pokemonreviews.dto.PokemonDTO;
import com.williamfeliciano.pokemonreviews.models.Pokemon;
import com.williamfeliciano.pokemonreviews.repositories.PokemonRepository;
import com.williamfeliciano.pokemonreviews.services.PokemonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class PokemonServiceImpl implements PokemonService {

    private final PokemonRepository pokemonRepository;

    @Override
    public PokemonDTO createPokemon(PokemonDTO pokemonDTO) {
        Pokemon pokemon = mapToEntity(pokemonDTO);
        Pokemon savedPokemon = pokemonRepository.save(pokemon);
        return mapToDTO(savedPokemon);
    }

    @Override
    public List<PokemonDTO> getAllPokemon() {
        List<Pokemon> allPokemon = pokemonRepository.findAll();
        return allPokemon.stream().map(this::mapToDTO).toList();
    }


    private PokemonDTO mapToDTO(Pokemon pokemon) {
        PokemonDTO dto= new PokemonDTO();
        dto.setId(pokemon.getId());
        dto.setName(pokemon.getName());
        dto.setType(pokemon.getType());
        return dto;
    }

    private Pokemon mapToEntity(PokemonDTO dto) {
        Pokemon pokemon = new Pokemon();
        pokemon.setName(dto.getName());
        pokemon.setType(dto.getType());
        return pokemon;
    }
}
