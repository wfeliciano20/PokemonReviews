package com.williamfeliciano.pokemonreviews.dto;

import lombok.Data;

import java.util.List;

@Data
public class PokemonResponse {
    private List<PokemonDTO> content;
    private int pageNo;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;
}
