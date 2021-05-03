package models;

import javax.swing.*;

public enum TypeFiles {

    BUILDING(new ImageIcon(TypeFiles.class.getResource("/ImagesTree/building.png")), "Building"),
    HOUSE(new ImageIcon(TypeFiles.class.getResource("/ImagesTree/house.png")), "House"),
    APARTMENT(new ImageIcon(TypeFiles.class.getResource("/ImagesTree/apartment.png")), "Apartment"),
    HORIZONTAL_PROPERTY(new ImageIcon(TypeFiles.class.getResource("/ImagesTree/root.png")), "horizontalProperty"),
    HORIZONTAL_PROPERTY_USER(new ImageIcon(TypeFiles.class.getResource("/ImagesTree/root.png")), "horizontalPropertyUser"),
    USER(new ImageIcon(TypeFiles.class.getResource("/super_user.png")),"User");

    private Icon icon;
    private String type;

    TypeFiles(Icon icon, String type) {
        this.icon = icon;
        this.type = type;
    }

    public Icon getIcon() {
        return icon;
    }

    public String getType() {
        return type;
    }
}
