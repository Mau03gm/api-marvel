package com.MarvelDemo.demo.DTO;

import lombok.Data;

@Data
public class CharacterDTO {

    String name;
    Integer page;

  public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPage() {
        return page;
    }
}
