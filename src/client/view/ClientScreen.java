package client.view;

import client.model.entities.Message;
import client.model.service.Service;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import javax.swing.JOptionPane;
import java.net.Socket;
import java.nio.channels.FileChannel;
import javax.swing.JFrame;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ClientScreen extends JFrame {

    private SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
    private JFileChooser fileChooser;
    private Socket client;
    private Message message;
    private Service clientServices;
    private Thread clientThread;
    
    private String username = "";

    public ClientScreen() {
        initComponents();
        initialSettings();
        getUsername();
        makeConnection();
    }
    
    private void initialSettings() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        textAreaContent.setLineWrap(true);
        textAreaContent.setWrapStyleWord(true);
        scrollArea.setViewportView(textAreaContent);

        inputMessageArea.setLineWrap(true);
        inputMessageArea.setWrapStyleWord(true);
        messageScrollArea.setViewportView(inputMessageArea);
        
        //quando voce aperta o X pra fechar, eu vou conseguir fechar o socket e a conexão
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                try {
                    clientThread.interrupt();
                    clientThread.stop();
                    client.close();
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "Houve um problema ao tentar encerrar a conexão");
                }
            }
        });
    }
    
    private void getUsername() {
        username = JOptionPane.showInputDialog(this, "Insira seu nome de usuario");
        setTitle("Client | Conectado como: " + username);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        clientPanel = new javax.swing.JPanel();
        scrollArea = new javax.swing.JScrollPane();
        textAreaContent = new javax.swing.JTextArea();
        messageScrollArea = new javax.swing.JScrollPane();
        inputMessageArea = new javax.swing.JTextArea();
        insertMessage = new javax.swing.JLabel();
        sendImage = new javax.swing.JLabel();
        file = new javax.swing.JLabel();
        smessage = new javax.swing.JLabel();
        clear = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        textAreaContent.setEditable(false);
        textAreaContent.setColumns(20);
        textAreaContent.setRows(5);
        scrollArea.setViewportView(textAreaContent);

        inputMessageArea.setColumns(20);
        inputMessageArea.setRows(5);
        messageScrollArea.setViewportView(inputMessageArea);

        insertMessage.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        insertMessage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        insertMessage.setText(" Insira sua mensagem abaixo");

        sendImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/client/view/assets/insert_photo.png"))); // NOI18N
        sendImage.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sendImageMouseClicked(evt);
            }
        });

        file.setIcon(new javax.swing.ImageIcon(getClass().getResource("/client/view/assets/file.png"))); // NOI18N
        file.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fileMouseClicked(evt);
            }
        });

        smessage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/client/view/assets/send_message.png"))); // NOI18N
        smessage.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                smessageMouseClicked(evt);
            }
        });

        clear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/client/view/assets/clear.png"))); // NOI18N
        clear.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                clearMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout clientPanelLayout = new javax.swing.GroupLayout(clientPanel);
        clientPanel.setLayout(clientPanelLayout);
        clientPanelLayout.setHorizontalGroup(
            clientPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(clientPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(clientPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollArea)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, clientPanelLayout.createSequentialGroup()
                        .addGroup(clientPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(sendImage)
                            .addComponent(file))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(clientPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(insertMessage, javax.swing.GroupLayout.DEFAULT_SIZE, 384, Short.MAX_VALUE)
                            .addComponent(messageScrollArea))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(clientPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(smessage)
                            .addComponent(clear, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addContainerGap())
        );
        clientPanelLayout.setVerticalGroup(
            clientPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(clientPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollArea, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(insertMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addGroup(clientPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(clientPanelLayout.createSequentialGroup()
                        .addComponent(sendImage)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(file))
                    .addGroup(clientPanelLayout.createSequentialGroup()
                        .addComponent(clear)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(smessage))
                    .addComponent(messageScrollArea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(clientPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(clientPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void clearMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clearMouseClicked
        //limpa aonde escreve a mensagem
        inputMessageArea.setText("");
    }//GEN-LAST:event_clearMouseClicked

    private void smessageMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_smessageMouseClicked
       //cria uma instancia do tipo mensagem para enviar ao servidor
        this.message = new Message(); //crio uma instancia de uma nova mensagem
        this.message.setUserName(username); //insere o usuario
        this.message.setMessage(inputMessageArea.getText()); //insere a mensagem
        
        try {
            //verifica se a mensagem não está vazia
            if("".equals(message.getMessage())) {
                return; //se tiver nd, retorno vazio e não continua
            }
            //verifica se o client está conectado com o servidor antes de mandar a mensagem pro servidor
            if(this.client != null) {
                //envio a mensagem pro servidor
                this.clientServices.sendMessageToAll(message);
            }
        } catch (IOException ex) {
            Logger.getLogger(ClientScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //apenas limpo a area de escrever
        inputMessageArea.setText("");
        
        //adiciono a mensagem que ele enviou no painel dele
        addMessage(message);
    }//GEN-LAST:event_smessageMouseClicked

    private void sendImageMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sendImageMouseClicked
        //chamo o metodo responsavel por abrir uma GUI para escolher a imagem
        getImage();
    }//GEN-LAST:event_sendImageMouseClicked

    private void fileMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fileMouseClicked
        //chamo o metodo responsavel por abrir uma GUI para escolher o arquivo
        getFile();
    }//GEN-LAST:event_fileMouseClicked

    private void addMessage(Message myMessage) {
        //add no painel de mensagens dele mesmo a mensagem que ele escreveu
        this.textAreaContent.append("[" + sdf.format(new Date()) + "] " + myMessage.getUserName() + ": " + myMessage.getMessage() + "\r\n");
    }
    
    private void getImage() {
        fileChooser = new JFileChooser();
        //um filtro para poder aparecer somente imagens com formato png
        fileChooser.setFileFilter(new FileNameExtensionFilter("PNG images (*.png)", "png"));
        //aqui eu mando ele aparecer a GUI pro client escolher qual arquivos ele vai querer mandar
        int returnValue = fileChooser.showOpenDialog(this);
        //aq em cima retorna um valor
        //CANCEL_OPTION = 1;
        //APPROVE_OPTION = 0;
        //ERROR_OPTION = -1;
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            int optionSelected = JOptionPane.showConfirmDialog(this,
                    "Você quer enviar esta imagem: " +
                    fileChooser.getSelectedFile().getName(), "Enviar imagem", JOptionPane.YES_NO_OPTION);
            if (optionSelected != 0) return; //se for diferente de sim, eu nao faço nd, e retorno vazio
        }
        
        //cria uma instancia do tipo mensagem para enviar ao servidor
        this.message = new Message();
        message.setUserName(username); //salva o nome de quem enviou
        message.setImage(fileChooser.getSelectedFile()); //salva a imagem selecionado
        if(message.getImage() != null) {
            try {
                //envia a imagem pro servidor
                this.clientServices.sendMessageToAll(message);
                //add no painel dele que voce conseguiu enviar
                this.textAreaContent.append("[" + sdf.format(new Date()) + "] voce enviou uma imagem." + "\r\n");
            } catch (IOException ex) {
                Logger.getLogger(ClientScreen.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void getFile() {
        fileChooser = new JFileChooser();
        //aqui eu mando ele aparecer a GUI pro client escolher qual arquivos ele vai querer mandar
        int returnValue = fileChooser.showOpenDialog(this);
        //aq em cima retorna um valor
        //CANCEL_OPTION = 1;
        //APPROVE_OPTION = 0;
        //ERROR_OPTION = -1;
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            int optionSelected = JOptionPane.showConfirmDialog(this,
                    "Você quer enviar este arquivo: " +
                    fileChooser.getSelectedFile().getName(), "Enviar arquivo", JOptionPane.YES_NO_OPTION);
            if (optionSelected != 0) return; //se for diferente de sim, eu nao faço nd, e retorno vazio
        }
        
        //cria uma instancia do tipo mensagem para enviar ao servidor
        this.message = new Message();
        message.setUserName(username); //salva o nome de quem enviou
        message.setFile(fileChooser.getSelectedFile()); //salva o arquivo selecionado
        if(message.getFile() != null) {
            try {
                //envia o arquivo pro servidor
                this.clientServices.sendMessageToAll(message);
                //add no painel dele que ele conseguiu enviar
                this.textAreaContent.append("[" + sdf.format(new Date()) + "] voce enviou um arquivo." + "\r\n");
            } catch (IOException ex) {
                Logger.getLogger(ClientScreen.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void makeConnection() {
        //instancia os serviços do client
        this.clientServices = new Service();
        //tenta conectar com o servidor
        try {
            this.client = this.clientServices.connectToServer();
            insertOnlineUsersList();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Não foi possivel realizar uma conexão com o servidor");
            inputMessageArea.setEnabled(false);
            return; //volta vazio, e nao prossegue em baixo
        }
        //instancia e inicia um thread para observar tudo que é mandado e recebido
        clientThread = new Thread(new Observer(this.client));
        clientThread.start();
    }
    
    private void insertOnlineUsersList() {
        //metodo responsavel por inserir o novo usuario na lista de usuarios conectados no servidor
        this.message = new Message(); //instancio uma nova mensagem
        this.message.setUserName(username); //insere o usuario
        this.message.setFirst(true); //aviso que é o primeiro login
        
        try {
            //envio a mensagem pro servidor
            this.clientServices.sendMessageToAll(message);
        } catch (IOException ex) {
            Logger.getLogger(ClientScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private class Observer implements Runnable {
        private ObjectInputStream receive;
        
        public Observer(Socket client) {
            try { 
                //aqui retorna uma instancia de output, onde qualquer coisa que o servidor mandar ele vai capturar
                receive = new ObjectInputStream(client.getInputStream());
            } catch (IOException ex) {
                Logger.getLogger(ClientScreen.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        @Override
        public void run() {
            Message newMessage = null;
            
            try {
                //aqui ele fica num loop verificando se tem alguma nova mensagem que o servidor enviou
                while((newMessage = (Message) receive.readObject()) != null) {
                    //O método readObject é responsável por ler o fluxo e restaurar os campos das classes.
                    //no momento que ele detecta que recebeu algo, ele faz a desserialização e atribui o objeto
                    //original enviado para uma variavel do mesmo tipo
                    
                    //mando para o metodo responsavel por tratar o objeto recebido
                    receiveMessage(newMessage);
                }
            } catch (IOException | ClassNotFoundException ex) {
                Logger.getLogger(ClientScreen.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void receiveMessage(Message newMessage) {
        //antes de verificar se tem alguma mensagem de text, verifica se tem arquivo
        //pois se tiver arquivo, não tem mensagem de texto
        if(newMessage.getFile() != null) {
            //se tiver chego algum arquivo, aparece no chat que tal pessoa enviou um arquivo
            this.textAreaContent.append("[" + sdf.format(new Date()) + "] " + newMessage.getUserName() + " enviou um arquivo." + "\r\n");
            
            //pergunta se o client deseja baixar o arquivo que tal pessoa enviou
            int optionSelected = JOptionPane.showConfirmDialog(this,
                    "Você quer baixar este arquivo? ", "Baixar arquivo", JOptionPane.YES_NO_OPTION);
            if (optionSelected != 0) return;

            //aqui é o metodo que salva o arquivo que recebeu
            saveFile(newMessage.getFile());
        } else if (newMessage.getImage() != null) {
            //se tiver chego alguma imagem, aparece no chat que tal pessoa enviou uma imagem
            this.textAreaContent.append("[" + sdf.format(new Date()) + "] " + newMessage.getUserName() + " enviou uma imagem." + "\r\n");
            
            //pergunta se o client deseja baixar a imagem que tal pessoa enviou
            int optionSelected = JOptionPane.showConfirmDialog(this,
                    "Você quer baixar esta imagem? ", "Baixar imagem", JOptionPane.YES_NO_OPTION);
            if (optionSelected != 0) return;
            
            //aqui é o metodo que salva a imagem que recebeu
            saveImage(newMessage.getImage());
        } else {
            //se não tiver arquivo...então tem texto
            this.textAreaContent.append("[" + sdf.format(new Date()) + "] " + newMessage.getUserName() + ": " + newMessage.getMessage() + "\r\n");
        }
    }
    
    private void saveFile(File receivedFile) {
        //apenas declara variaveis de captura e de escrita para o tratamento de arquivos
        FileInputStream fileReceiveStream = null;
        FileOutputStream fileSendStream = null;
        
        try {
            //pego a instancia do arquivo
            fileReceiveStream = new FileInputStream(receivedFile);
            //crio uma pasta caso ela não exista, se existir nem vai criar
            new File("\\aps-socket-chat").getCanonicalFile().mkdir(); 
            //crio uma pasta caso ela não exista, se existir nem vai criar
            new File("\\aps-socket-chat\\filesRecived").getCanonicalFile().mkdir(); 
            //falo onde o arquivo vai ser salvo
            fileSendStream = new FileOutputStream(new File( "\\aps-socket-chat\\filesRecived" ).getCanonicalPath() + "\\" + receivedFile.getName());
            //cria um canal de transferencia de byte baseado o arquivo recebido
            FileChannel receiveFileMessage = fileReceiveStream.getChannel(); 
            //cria um canal de transferencia de byte para salvar o arquivo recebido
            FileChannel sendFileMessage = fileSendStream.getChannel(); 
            //pego o tamanho do arquivo
            long size = receiveFileMessage.size();
            //Transfere bytes do arquivo deste canal para o canal de bytes gravável fornecido.
            receiveFileMessage.transferTo(0, size, sendFileMessage); 
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ClientScreen.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ClientScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void saveImage(File receivedImage) {
        //apenas declara variaveis de captura e de escrita para o tratamento de imagens
        FileInputStream imageReceiveStream = null;
        FileOutputStream imageSendStream = null;
        
        try {
            //pego a instancia da imagem
            imageReceiveStream = new FileInputStream(receivedImage);
            //crio uma pasta caso ela não exista, se existir nem vai criar
            new File("\\aps-socket-chat").getCanonicalFile().mkdir(); 
            //crio uma pasta caso ela não exista, se existir nem vai criar
            new File("\\aps-socket-chat\\imagesReceived").getCanonicalFile().mkdir(); 
            //falo onde a imagem vai ser salvo
            imageSendStream = new FileOutputStream(new File( "\\aps-socket-chat\\imagesReceived" ).getCanonicalPath() + "\\" + receivedImage.getName());
            //cria um canal de transferencia de byte baseado na imagem recebida
            FileChannel receiveImageMessage = imageReceiveStream.getChannel(); 
            //cria um canal de transferencia de byte para salvar a imagem recebido
            FileChannel sendImageMessage = imageSendStream.getChannel(); 
            //pego o tamanho da imagem
            long size = receiveImageMessage.size();
            //Transfere bytes da imagem deste canal para o canal de bytes gravável fornecido.
            receiveImageMessage.transferTo(0, size, sendImageMessage);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ClientScreen.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ClientScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ClientScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel clear;
    private javax.swing.JPanel clientPanel;
    private javax.swing.JLabel file;
    private javax.swing.JTextArea inputMessageArea;
    private javax.swing.JLabel insertMessage;
    private javax.swing.JScrollPane messageScrollArea;
    private javax.swing.JScrollPane scrollArea;
    private javax.swing.JLabel sendImage;
    private javax.swing.JLabel smessage;
    private javax.swing.JTextArea textAreaContent;
    // End of variables declaration//GEN-END:variables
}
