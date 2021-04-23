package client.model.entities;

import java.io.File;
import java.io.Serializable;

public class Message implements Serializable {

    //Implementar a interface Serializable significa que o/os objetos dessa classe podem ser trafegados
    //a partir de um canal de comunicação, tipo uma conexão de rede, ou estrutura de um arquivo
    //Basicamente permite eu converter os objetos da classe Message para uma sequencia binaria

    private static final long serialVersionUID = 1L;

    private String userName;
    private String message;
    private File file;
    private File image;
    private boolean first = false;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public boolean isFirst() {
        return first;
    }

    public void setFirst(boolean first) {
        this.first = first;
    }

    public File getImage() {
        return image;
    }

    public void setImage(File image) {
        this.image = image;
    }
}
