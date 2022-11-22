package com.williamfeliciano.pokemonreviews.controllers;

import com.williamfeliciano.pokemonreviews.dto.PokemonDTO;
import com.williamfeliciano.pokemonreviews.dto.PokemonResponse;
import com.williamfeliciano.pokemonreviews.services.PokemonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
public class PokemonController {

    private final PokemonService pokemonService;

    @GetMapping("pokemon")
    public ResponseEntity<PokemonResponse> getPokemon(
            @RequestParam(value = "pageNo", defaultValue = "0") int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize
    ){
        PokemonResponse pokemons = pokemonService.getAllPokemon(pageNo, pageSize);
        return new ResponseEntity<>(pokemons, HttpStatus.OK);
    }

    @GetMapping("pokemon/{id}")
    public ResponseEntity<PokemonDTO> getSpecificPokemon(@PathVariable("id") int id){
        return ResponseEntity.ok().body(pokemonService.getPokemonById(id));
    }

    @PostMapping("pokemon/create")
    public ResponseEntity<PokemonDTO> createPokemon(@RequestBody PokemonDTO pokemonDTO){
        return new ResponseEntity<>(pokemonService.createPokemon(pokemonDTO),HttpStatus.CREATED);
    }

    @PutMapping("pokemon/{id}/update")
    public ResponseEntity<PokemonDTO> updatePokemon(@PathVariable("id") int id, @RequestBody PokemonDTO updatedPokemonDTO){
        PokemonDTO updatedPokemon = pokemonService.updatePokemon(id,updatedPokemonDTO);
        return ResponseEntity.ok().body(updatedPokemon);
    }

    @DeleteMapping("pokemon/{id}/delete")
    public ResponseEntity<String> deletePokemon(@PathVariable("id") int id){
        pokemonService.deletePokemon(id);
        return new ResponseEntity<>("Pokemon Deleted Successfully" ,HttpStatus.NO_CONTENT);
    }

}
