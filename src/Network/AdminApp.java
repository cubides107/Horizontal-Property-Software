package Network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class AdminApp {

    public static final int PORT = 8000;

    public static final String REGISTER_USER = "REGISTER_USER";
    public static final String NEW_BUILDING = "NEW_BUILDING";
    public static final String NEW_HOUSE = "NEW_HOUSE";
    public static final String NEW_APARTMENT = "NEW_APARTMENT";
    public static final String ADD_HOUSE_USER = "ADD_HOUSE_USER";

    private PresenterImp presenterImp;
    private Socket socket;

    private DataOutputStream outputChanel;
    private DataInputStream inputChanel;

    public AdminApp(PresenterImp presenterImp) throws IOException {
        this.presenterImp = presenterImp;
        socket = new Socket("localHost", PORT);
        outputChanel = new DataOutputStream(socket.getOutputStream());
        inputChanel = new DataInputStream(socket.getInputStream());
        readResponseToServer();
    }

    public void writeUTF(String message) {
        try {
            outputChanel.writeUTF(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeInt(int number) {
        try {
            outputChanel.writeInt(number);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void readResponseToServer() {
        new Thread(() -> {
            while (!socket.isClosed()) {
                try {
                    if (inputChanel.available() > 0) {
                        String message = inputChanel.readUTF();
                        manageRequest(message);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void manageRequest(String message) throws IOException {
        switch (message) {
            case REGISTER_USER:
                boolean statusAddUser = inputChanel.readBoolean();
                presenterImp.showAlert(statusAddUser, inputChanel.readUTF(), inputChanel.readInt());
                break;
            case NEW_BUILDING:
                presenterImp.addNewBuilding(String.valueOf(inputChanel.readInt()));
                break;
            case NEW_HOUSE:
                presenterImp.addNewHouse(String.valueOf(inputChanel.readInt()));
                break;
            case NEW_APARTMENT:
                presenterImp.addNewApartment(String.valueOf(inputChanel.readInt()));
                break;
            case ADD_HOUSE_USER:
                presenterImp.addHouseToUser(inputChanel.readInt());
                break;
            case "ADD_APARTMENT_USER":
                presenterImp.addApartmentToUser(inputChanel.readInt());
                break;
            case "NEW_POOL":
                presenterImp.addNewPool(String.valueOf(inputChanel.readInt()));
                break;
            case "NEW_FIELD":
                presenterImp.addNewField(String.valueOf(inputChanel.readInt()));
                break;
            case "NEW_ADD_COMMON_ROOM":
                presenterImp.addNewCommonRoom(String.valueOf(inputChanel.readInt()));
                break;

        }
    }
}
