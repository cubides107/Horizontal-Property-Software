package Network;

public interface PresenterImp {
    void showAlert(boolean statusAlert, String emailUser, int idUser);
    void addNewBuilding(String idProperty);
    void addNewHouse(String idProperty);
    void addNewApartment(String idProperty);
    void addUser(String idProperty);
    void addHouseToUser(int user);
    void addApartmentToUser(int user);


}
