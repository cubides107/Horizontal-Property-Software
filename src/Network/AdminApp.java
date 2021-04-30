package Network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class AdminApp {

    public static final int PORT = 8000;

    public static final String REGISTER_USER = "REGISTER_USER";

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

    public void writeUTF(String message){
        try {
            outputChanel.writeUTF(message);
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
        switch(message){
            case REGISTER_USER:
                boolean statusAddUser = inputChanel.readBoolean();
                presenterImp.showAlert(statusAddUser);
                break;
        }
    }
}
