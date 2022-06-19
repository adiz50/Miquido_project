package com.miquido.Service;

import com.miquido.Exception.CharacterRequestException;
import com.miquido.Model.CharacterModel;
import com.miquido.Repository.CharactersRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class CharacterService {

    private final CharactersRepository charactersRepository;
    private final RestTemplate restTemplate;
    private final Logger logger = LoggerFactory.getLogger(CharacterService.class);

    @Autowired
    public CharacterService(CharactersRepository charactersRepository, RestTemplate restTemplate) {
        this.charactersRepository = charactersRepository;
        this.restTemplate = restTemplate;
    }

    public void addCharacter(@Valid CharacterModel characterModel) {
        charactersRepository.save(characterModel);
    }

    public ResponseEntity<Object> importCharacter(Long id) {

        CharacterModel characterModel;
        try {
            String url = "https://swapi.dev/api/people/" + id;
            characterModel = restTemplate.getForObject(url, CharacterModel.class);
        } catch (HttpStatusCodeException e) {
            throw new CharacterRequestException(e.getMessage(), e.getStatusCode());
        }

        characterModel.setId(id);
        try {
            addCharacter(characterModel);
        } catch (TransactionSystemException e) {
            Throwable t = e.getCause();
            while ((t != null) && !(t instanceof ConstraintViolationException)) {
                t = t.getCause();
            }
            if (t != null) {
                throw new CharacterRequestException(((ConstraintViolationException) t).getConstraintViolations()
                        .stream().findFirst().get().getMessage(), HttpStatus.BAD_REQUEST);
            }
        }

        return new ResponseEntity<>(characterModel, HttpStatus.CREATED);
    }

    public ResponseEntity<CharacterModel> getCharacter(Long id) {

        Optional<CharacterModel> characterModel = charactersRepository.findById(id);
        if (characterModel.isEmpty()) throw new CharacterRequestException("Couldn't find character with that id"
                , HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(characterModel.get(), HttpStatus.OK);
    }

    public ResponseEntity<ArrayList<CharacterModel>> searchCharacter(String name) {
        name = name.toUpperCase();
        return new ResponseEntity<>(charactersRepository.searchCharacterByName(name), HttpStatus.OK);
    }
}
