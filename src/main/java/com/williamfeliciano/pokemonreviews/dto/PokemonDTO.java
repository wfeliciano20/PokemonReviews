package com.williamfeliciano.pokemonreviews.dto;

import com.williamfeliciano.pokemonreviews.models.Pokemon;
import lombok.Data;

@Data
public class PokemonDTO {

    private int id;
    private String name;
    private String type;

    public PokemonDTO fromPokemon(Pokemon pokemon) {
        PokemonDTO dto= new PokemonDTO();
        dto.setId(pokemon.getId());
        dto.setName(pokemon.getName());
        dto.setType(pokemon.getType());
        return dto;
    }

    public Pokemon toPokemon(PokemonDTO dto) {
        Pokemon pokemon = new Pokemon();
        pokemon.setName(dto.getName());
        pokemon.setType(dto.getType());
        return pokemon;
    }
}
