package com.williamfeliciano.pokemonreviews.controllers;

import com.williamfeliciano.pokemonreviews.models.Pokemon;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class PokemonController {

    @GetMapping("pokemon")
    public ResponseEntity<List<Pokemon>> getPokemon(){
        List<Pokemon> pokemons = new ArrayList<>();
        pokemons.add(new Pokemon(1,"bulbasaur","grass"));
        pokemons.add(new Pokemon(4,"squirtle","water"));
        pokemons.add(new Pokemon(7,"charmander","fire"));
        return ResponseEntity.ok().body(pokemons);
    }
}
