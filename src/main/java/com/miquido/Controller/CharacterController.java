package com.miquido.Controller;

import com.miquido.Model.CharacterModel;
import com.miquido.Service.CharacterService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping(path = "/character")
public class CharacterController {

    private final CharacterService characterService;

    @Autowired
    public CharacterController(CharacterService characterService) {
        this.characterService = characterService;
    }

    @ApiOperation(value = "Import a character by id", notes = "Imports a character from https://swapi.dev by its id " +
            "into database, keeping the id from swapi.dev")
    @PostMapping(path = "/import/{id}")
    public ResponseEntity<Object> importCharacter(@PathVariable Long id) {
        return characterService.importCharacter(id);
    }

    @ApiOperation(value = "Returns character by id", notes = "Returns character by its id in database")
    @GetMapping(path = "/get/{id}")
    public ResponseEntity<CharacterModel> getCharacter(@PathVariable Long id) {
        return characterService.getCharacter(id);
    }

    @ApiOperation(value = "Search character by name", notes = "Returns characters whose names contain the given" +
            " sequence of symbols case insensitive")
    @GetMapping(path = "/search")
    public ResponseEntity<ArrayList<CharacterModel>> searchCharacter(@RequestParam String name) {
        return characterService.searchCharacter(name);
    }
}

