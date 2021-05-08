package models;

public class NodeTreeViews implements INode {
    private TypeFiles typeFile;
    private String name;
    private String ID;

    public NodeTreeViews(TypeFiles typeFile, String name, String ID) {
        this.typeFile = typeFile;
        this.name = name;
        this.ID = ID;
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

    public void setID(String ID) {
        this.ID = ID;
    }
}
