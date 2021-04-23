package server.model.service;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import client.model.entities.Message;
import server.view.ServerScreen;

public class Service extends Thread {
    private Map<String, ObjectOutputStream> onlineUsers = new HashMap<>();
    private ServerSocket server;
    private Socket client;
    private Thread serverThread;
    private ServerScreen screen;
    
    private int door = 9457;
    
    //classe responsavel por iniciar o socket
    public Service(ServerScreen screen){
        this.screen = screen;
        try{
            //instancia o servidor
            server = new ServerSocket(door);
        }catch (IOException e) {
            System.out.println("Server error: " + e.getMessage());
        }
    }
    
    public void startServer() {
        while(true) {
            try {
                //responsavel por aceitar todo novo client que tentar se conectar ao servidor
                this.client = server.accept();
                //se aceitar inicia uma thread resposavel por observar todo o trafego que vai ser mandado ou recebido pela rede
                this.serverThread = new Thread(new Observer(this.client));
                //quando eu chamo o start, basicamente estou chamando o run()
                this.serverThread.start();
            } catch (IOException ex) {
                Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void stopServer() {
        try {
            //fecha o servidor
            this.server.close();
        } catch (IOException ex) {
            Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void sendMessageToAll(Message newMessage) {
        //vai enviar a mensagem para todos os usuarios
        for(Map.Entry<String, ObjectOutputStream> user : onlineUsers.entrySet()) {
            //verifica, pra certificar que eu mesmo nao vou receber a mensagem
            //Key = String
            //Value = ObjectOutputStream
            if(!user.getKey().equals(newMessage.getUserName())) {
                try {
                    user.getValue().writeObject(newMessage);
                    //aqui aquele faz a serialização do objeto, convertendo ele para uma sequencia de binario
                    //writeObject é responsável por escrever o estado do objeto para sua classe 
                    //particular para que o método readObject correspondente possa restaurá-lo. 
                } catch (IOException ex) {
                    Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    private class Observer implements Runnable {
        //classe responsavel por enviar e receber mensagens e arquivos
        private ObjectOutputStream send; //metodo que manda
        private ObjectInputStream receive; //metodo que captura
        
        public Observer(Socket client) {
            try {
              //aqui atribuia uma instancia de input e output, onde qualquer coisa que mandar o servidor irá receber ou enviar
              send = new ObjectOutputStream(client.getOutputStream());
              receive = new ObjectInputStream(client.getInputStream());
            } catch (IOException ex) {
                Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
            } 
        }
        
        @Override
        public void run() {
            Message newMessage = null;
            
            try {
                //aqui ele fica num loop verificando se tem alguma nova mensagem que o servidor enviou
                while((newMessage = (Message) receive.readObject()) != null ) {
                    //O método readObject é responsável por ler o fluxo e restaurar os campos da classe(Message)
                    //no momento que ele detecta que recebeu algo, ele faz a desserialização e faz um downcast e
                    //em seguida atribui o objeto original enviado para uma variavel do mesmo tipo
                    
                    //verifica se é a primera vez q logou, se sim add na lista de usuarios
                    if(newMessage.isFirst() == true) {
                        onlineUsers.put(newMessage.getUserName(), send);
                        newMessage.setFirst(false);
                    }
                    
		    //chama o metodo responsavel por enviar a mensagem para todos conectados
                    //verifica se a mensagem ou o arquivo ou imagem não estão vazios, se pelo menos um dos 2 tiver algo, vai mandar pra geral
                    if(newMessage.getMessage() != null || newMessage.getFile() != null || newMessage.getImage() != null) {
                        sendMessageToAll(newMessage);
                    }
                }
            } catch (IOException | ClassNotFoundException ex) {
                Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
