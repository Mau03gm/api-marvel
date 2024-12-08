package com.MarvelDemo.demo.Service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Logger;

@Service
public class characterService {

    String hash="68871171626cbe72eddf213702a3e14d";
    String privateKey="";
    String publicKey="f3fa3ffddc4ec6463042dec1d2a8c0a4";
    String apiURL="https://gateway.marvel.com:443/v1/public/characters";

    public ResponseEntity<String> getAllCharacter(Integer page) {
        page= page*10;
        RestTemplate restTemplate = new RestTemplate();
        String url = apiURL + "?ts=1&limit="+page+"&apikey=" + publicKey + "&hash=" + hash;
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        Logger logger = Logger.getLogger(characterService.class.getName());
        logger.info(response.getBody());

        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }

  public String getCharacterByName(String name) {
    RestTemplate restTemplate = new RestTemplate();
    String url = apiURL + "?name=" + name + "&ts=1&apikey=" + publicKey + "&hash=" + hash;
    ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
    Logger logger = Logger.getLogger(characterService.class.getName());
    logger.info(response.getBody());
    return response.getBody();
}


public String getCharacterNameStartWith( String name) {
    RestTemplate restTemplate = new RestTemplate();
    String url = apiURL + "?nameStartsWith="+name+"&ts=1&apikey=" + publicKey + "&hash=" + hash;
    ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
    Logger logger = Logger.getLogger(characterService.class.getName());
    logger.info(response.getBody());
    return response.getBody();
}
}
