package com.gabriel.pokems.model;

import lombok.Data;

@Data
public class CatchEvent {
    private int playerId;
    private int pokemonId;
    private String pokemonName;
}