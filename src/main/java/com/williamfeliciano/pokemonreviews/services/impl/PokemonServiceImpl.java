package com.williamfeliciano.pokemonreviews.services.impl;

import com.williamfeliciano.pokemonreviews.dto.PokemonDTO;
import com.williamfeliciano.pokemonreviews.dto.PokemonResponse;
import com.williamfeliciano.pokemonreviews.exceptions.PokemonNotFoundException;
import com.williamfeliciano.pokemonreviews.models.Pokemon;
import com.williamfeliciano.pokemonreviews.repositories.PokemonRepository;
import com.williamfeliciano.pokemonreviews.services.PokemonService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.data.domain.PageRequest.*;


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
    public PokemonResponse getAllPokemon(int pageNo, int pageSize) {
        Pageable pageable = of(pageNo, pageSize);
        Page<Pokemon> allPokemon = pokemonRepository.findAll(pageable);
        List<Pokemon> listOfPokemon = allPokemon.getContent();
        List<PokemonDTO> content = listOfPokemon.stream().map(this::mapToDTO).toList();
        PokemonResponse pokemonResponse = new PokemonResponse();
        pokemonResponse.setContent(content);
        pokemonResponse.setPageNo(allPokemon.getNumber());
        pokemonResponse.setPageSize(allPokemon.getSize());
        pokemonResponse.setTotalElements(allPokemon.getTotalElements());
        pokemonResponse.setTotalPages(allPokemon.getTotalPages());
        pokemonResponse.setLast(allPokemon.isLast());
        return pokemonResponse;
    }

    @Override
    public PokemonDTO getPokemonById(Integer id) {
        Pokemon pokemonFromDB = pokemonRepository.findById(id).orElseThrow(() -> new PokemonNotFoundException("Pokemon Could Not be found"));
        return mapToDTO(pokemonFromDB);
    }

    @Override
    public PokemonDTO updatePokemon(Integer id, PokemonDTO pokemonDTO) {
        Pokemon pokemonFromDB = pokemonRepository.findById(id)
                .orElseThrow(() ->
                        new PokemonNotFoundException("Pokemon Could Not be updated because it could not be found"));
        pokemonFromDB.setName(pokemonDTO.getName());
        pokemonFromDB.setType(pokemonDTO.getType());
        Pokemon updatedPokemon = pokemonRepository.save(pokemonFromDB);
        return mapToDTO(updatedPokemon);
    }

    @Override
    public void deletePokemon(Integer id) {
        Pokemon pokemonToDelete = pokemonRepository.findById(id).orElseThrow(() -> new PokemonNotFoundException("Pokemon could not be deleted"));
        pokemonRepository.delete(pokemonToDelete);
    }


    private PokemonDTO mapToDTO(Pokemon pokemon) {
        PokemonDTO dto = new PokemonDTO();
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
