package com.miquido.Repository;

import com.miquido.Model.CharacterModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface CharactersRepository extends JpaRepository<CharacterModel, Long> {

    @Query(value = "select * from characters where upper(name) like %?1% ;", nativeQuery = true)
    ArrayList<CharacterModel> searchCharacterByName(String name);
}
