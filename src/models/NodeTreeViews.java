package models;

public class NodeTreeViews implements INode {
    private TypeFiles typeFile;
    private String name;
    private String ID;
    private boolean isPainted;

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

    public void setPainted(boolean painted) {
        isPainted = painted;
    }

    @Override
    public TypeFiles getTypeFile() {
        return typeFile;
    }

    @Override
    public boolean getIsPainted() {
        return isPainted;
    }

    @Override
    public String toString() {
        switch (typeFile) {
            case HORIZONTAL_PROPERTY_USER:
            case FIELD:
            case POOL:
            case APARTMENT:
            case COMMON_ROOM:
            case HOUSE:
            case BUILDING:
                return name + " ( # " + ID + " )";
            case USER:
            case SERVICE_GAS:
            case BILL_SERVICE:
            case SERVICE_WATER:
            case SERVICE_INTERNET:
            case SERVICE_ELECTRICITY:
            case HORIZONTAL_PROPERTY:
                return name;
        }
        return name;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
}
