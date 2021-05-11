package Network;

public interface PresenterImp {
    void showAlert(boolean statusAlert, String emailUser, int idUser);

    void addNewBuilding(String idProperty);

    void addNewHouse(String idProperty);

    void addNewApartment(String idProperty);

    void addUser(String idProperty);

    void addHouseToUser(int user);

    void addApartmentToUser(int user);

    void addNewPool(String idProperty);

    void addNewField(String idProperty);

    void addNewCommonRoom(String idProperty);

    void loadPropertiesTree();

    void loadDataUsers();

    void showReportUsers();

    void repaintNodesPropertiesTre(String idNodes);
}
