package com.gabriel.pokems.repository;
import com.gabriel.pokems.entity.PlayerData;
import org.springframework.data.repository.CrudRepository;
public interface PlayerDataRepository extends CrudRepository<PlayerData,Integer> {}