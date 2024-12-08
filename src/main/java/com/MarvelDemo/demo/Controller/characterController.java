package com.MarvelDemo.demo.Controller;

import com.MarvelDemo.demo.Config.Application;
import com.MarvelDemo.demo.DTO.CharacterDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.MarvelDemo.demo.Service.characterService;

@RestController
@RequestMapping(Application.API_BASE_PATH +"/characters")
public class characterController {

    @Autowired
    private characterService characterService;

    @GetMapping("/getAll")
    public ResponseEntity<String> getAllCharacters(@RequestBody CharacterDTO page){
        return characterService.getAllCharacter(page.getPage());
    }

    @GetMapping("/getByName")
    public ResponseEntity<String> getCharacterByName(@RequestBody CharacterDTO characterDTO){
        return ResponseEntity.ok().body(characterService.getCharacterByName(characterDTO.getName()));
    }

    @GetMapping("/getStartWith")
    public ResponseEntity<String> getCharacterNameStartWith(@RequestBody CharacterDTO characterDTO){
        return ResponseEntity.ok().body(characterService.getCharacterNameStartWith(characterDTO.getName()));
    }

}
