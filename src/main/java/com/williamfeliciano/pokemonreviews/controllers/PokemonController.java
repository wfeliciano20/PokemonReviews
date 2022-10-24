package com.williamfeliciano.pokemonreviews.controllers;

import com.williamfeliciano.pokemonreviews.dto.PokemonDTO;
import com.williamfeliciano.pokemonreviews.models.Pokemon;
import com.williamfeliciano.pokemonreviews.services.PokemonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
public class PokemonController {

    private final PokemonService pokemonService;

    @GetMapping("pokemon")
    public ResponseEntity<List<Pokemon>> getPokemon(){
        List<Pokemon> pokemons = new ArrayList<>();
        pokemons.add(new Pokemon(1,"bulbasaur","grass"));
        pokemons.add(new Pokemon(4,"squirtle","water"));
        pokemons.add(new Pokemon(7,"charmander","fire"));
        return ResponseEntity.ok().body(pokemons);
    }

    @GetMapping("pokemon/{id}")
    public ResponseEntity<Pokemon> getSpecificPokemon(@PathVariable("id") int id){
        List<Pokemon> pokemons = new ArrayList<>();
        pokemons.add(new Pokemon(1,"bulbasaur","grass"));
        pokemons.add(new Pokemon(4,"squirtle","water"));
        pokemons.add(new Pokemon(7,"charmander","fire"));

        Pokemon pokemonToReturn = pokemons.stream().filter(pokemon -> pokemon.getId()== id).findFirst().get();
        return ResponseEntity.ok().body(pokemonToReturn);
    }

    @PostMapping("pokemon/create")
    public ResponseEntity<PokemonDTO> createPokemon(@RequestBody PokemonDTO pokemonDTO){
        return new ResponseEntity<>(pokemonService.createPokemon(pokemonDTO),HttpStatus.CREATED);
    }

    @PutMapping("pokemon/{id}/update")
    public ResponseEntity<Pokemon> updatePokemon(@PathVariable("id") int id, @RequestBody Pokemon updatedPokemon){
        List<Pokemon> pokemons = new ArrayList<>();
        pokemons.add(new Pokemon(1,"bulbasaur","grass"));
        pokemons.add(new Pokemon(4,"squirtle","water"));
        pokemons.add(new Pokemon(7,"charmander","fire"));

        Pokemon pokemonToReturn = pokemons.stream().filter(pokemon -> pokemon.getId()== id).findFirst().get();
        pokemonToReturn.setName(updatedPokemon.getName());
        pokemonToReturn.setType(updatedPokemon.getType());
        return ResponseEntity.ok().body(pokemonToReturn);
    }

    @DeleteMapping("pokemon/{id}/delete")
    public ResponseEntity<?> deletePokemon(@PathVariable("id") int id){
        System.out.println(id);
        return new ResponseEntity<>(id, HttpStatus.NO_CONTENT);
    }

}
