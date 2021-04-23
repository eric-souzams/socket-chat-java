package client.model.service;

import client.model.entities.Message;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Service {
    
    private Socket client;
    private ObjectOutputStream send;
    
    private int door = 9457;

    public Socket connectToServer() throws IOException {
        //responsavel por fazer a conexão do client com o servidor
        this.client = new Socket("127.0.0.1", door);
        //instancia o metodo resposavel por fazer o envio
        this.send = new ObjectOutputStream(client.getOutputStream());
        return client;
    }
    
    public void sendMessageToAll(Message newMessage) throws IOException {
        //responsavel por enviar as mensagens do client para o servidor
        send.writeObject(newMessage);
        //aqui aquele faz a serialização do objeto, convertendo ele para uma sequencia de binario
        //writeObject é responsável por escrever o estado do objeto para sua classe
        //particular para que o método readObject correspondente possa restaurá-lo.
    }
}
