package com.MarvelDemo.demo.Controller;

import com.MarvelDemo.demo.Config.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.MarvelDemo.demo.Service.characterService;

@RestController
@RequestMapping(Application.API_BASE_PATH +"/characters")
public class characterController {

    @Autowired
    private characterService characterService;

    @GetMapping("/getAll")
    public ResponseEntity<String> getAllCharacters(){
        return ResponseEntity.ok().body(characterService.getAllCharacter());
    }

    @GetMapping("/getByName")
    public ResponseEntity<String> getCharacterByName(String name){
        return ResponseEntity.ok().body(characterService.getCharacterByName(name));
    }

    @GetMapping("/getStartWith")
    public ResponseEntity<String> getCharacterNameStartWith(){
        return ResponseEntity.ok().body(characterService.getCharacterNameStartWith());
    }

}
