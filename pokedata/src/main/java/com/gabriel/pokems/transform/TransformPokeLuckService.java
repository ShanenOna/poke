package com.gabriel.pokems.transform;
import com.gabriel.pokems.entity.PokeLuckData;
import com.gabriel.pokems.model.PokeLuck;
public interface TransformPokeLuckService {
	PokeLuckData transform(PokeLuck pokeLuck);
	PokeLuck transform(PokeLuckData pokeLuckData);
}
