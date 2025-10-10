package com.skynet;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Robot {

    public Robot(int id, List<Part> parts){
        this.id = id;
        this.parts = parts;
    }

    private int id;
    private List<Part> parts;

}