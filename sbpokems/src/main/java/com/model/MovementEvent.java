package com.gabriel.pokems.model;

import lombok.Data;

@Data
public class MovementEvent {
    private int playerId;
    private int positionX;
    private int positionY;
}