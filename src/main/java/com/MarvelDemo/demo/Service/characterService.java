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

public String getAllCharacter() {
// Make a request to the Marvel API
RestTemplate restTemplate = new RestTemplate();
String url = apiURL + "?ts=1&apikey=" + publicKey + "&hash=" + hash;
ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
Logger logger = Logger.getLogger(characterService.class.getName());
logger.info(response.getBody());
return response.getBody();
}

  public String getCharacterByName(String name) {
    RestTemplate restTemplate = new RestTemplate();
    String url = apiURL + "?name=" + name + "&ts=1&apikey=" + publicKey + "&hash=" + hash;
    ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
    Logger logger = Logger.getLogger(characterService.class.getName());
    logger.info(response.getBody());
    return response.getBody();
}


public String getCharacterNameStartWith() {
    RestTemplate restTemplate = new RestTemplate();
    String url = apiURL + "?nameStartsWith=Spider&ts=1&apikey=" + publicKey + "&hash=" + hash;
    ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
    Logger logger = Logger.getLogger(characterService.class.getName());
    logger.info(response.getBody());
    return response.getBody();
}
}
