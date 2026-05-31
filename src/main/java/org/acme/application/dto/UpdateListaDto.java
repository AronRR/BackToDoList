package org.acme.application.dto;

public class UpdateListaDto {
    private String name;
    private String color;

    public UpdateListaDto() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
