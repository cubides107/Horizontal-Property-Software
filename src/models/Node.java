package models;

public class Node implements INode {
    private TypeFiles typeFile;
    private String name;
    private String ID;

    public Node(TypeFiles typeFile,String name, String ID) {
        this.typeFile = typeFile;
        this.name = name;
        this.ID = ID;
    }

    public Node(TypeFiles typeFile) {
        this.typeFile = typeFile;
    }

    public String getName() {
        return name;
    }

    public String getID() {
        return ID;
    }

    @Override
    public TypeFiles getTypeFile() {
        return typeFile;
    }

    @Override
    public String toString() {
        return name + " ( # " +ID + " )";
    }
}
