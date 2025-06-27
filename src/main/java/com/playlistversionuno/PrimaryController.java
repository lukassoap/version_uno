package com.playlistversionuno;

// import com.mycompany.proyectoplaylist.controladores.PlayListController;
// import com.mycompany.proyectoplaylist.modelos.Cancion;
// import com.mycompany.proyectoplaylist.modelos.DoublyLinkedList;
// import com.mycompany.proyectoplaylist.modelos.SimpleAudioPlayer;

// TODO: Uncomment and fix the import paths below if these classes exist in your project structure
// import your.correct.package.PlayListController;
// import your.correct.package.Cancion;
// import your.correct.package.DoublyLinkedList;
// import your.correct.package.SimpleAudioPlayer;
import java.io.File;
import java.io.IOException;
import java.util.ListIterator;

import com.playlistversionuno.controllers.PlayListController;
import com.playlistversionuno.modelos.Cancion;
import com.playlistversionuno.modelos.DoublyLinkedList;
import com.playlistversionuno.modelos.SimpleAudioPlayer;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PrimaryController {
    
    @FXML
    Button btnpausa;
    @FXML
    Button btnplay;
    @FXML
    Button btnreverse;
    @FXML
    Button btnforward;
    @FXML
    ImageView fotoAlbum;
    @FXML
    TextArea textArea; // this is not used but could be used to show the current song or something
    
    PlayListController Playlist = new PlayListController();
    DoublyLinkedList<Cancion> lista = Playlist.Read(); // might have to do this outside
    //could use iterators for adding without having to redo the iterator but need to ask
    // does adding items to the queue also updates an iterator made before or not
    //ListIterator<Cancion> inter = lista.listIterator(); // beed to work on this, maybe adding a controller to separate
    SimpleAudioPlayer player = new SimpleAudioPlayer(lista); // i think it plays the first song
    ListIterator<Cancion> rotafoto = lista.listIterator();
    //REVISAR SI ESTO NO ES UN ITERADOR DOBLE DE LA MISMA COSA
    //need to find current song
    // could change the iterator in the music playlist
    // acutually nvm CHANGE THE MEDIA PLAYER TO TAKE ITERATOR WITH MUSIC CLASS OBJECTS TSO IT WORKS CORRECTLY AND VOILA
    // could also say fuck it and make a photo controller or
    //makre a dedicated thiung in the class for audio player but it would feel pointless
    
    @FXML
    private void initialize() throws IOException{
        
    }
    
    

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
    @FXML
    private void siguiente() throws IOException {
 
        player.playNextSong();
        File file = new File(rotafoto.next().getFoto()); // should get the path for the photo
        Image img = new Image(file.toURI().toString()); //  esto lo vuelve imagen
        fotoAlbum.setImage(img); // creo que hare nomas 3 iteradores y lo dejo ahi pero no quiero la verdad
        // tal vez si diseno mejor el circular podria dejarme lo de iteradores y usar el queue con su current y 
        //pasarmelo por los huevos pero me da pereza        
    }
    
    @FXML
    private void reversa() throws IOException {
 
        player.playPreviousSong();
        File file = new File(rotafoto.previous().getFoto()); // should get the path for the photo
        Image img = new Image(file.toURI().toString()); //  esto lo vuelve imagen
        fotoAlbum.setImage(img); // creo que hare nomas 3 iteradores y lo dejo ahi pero no quiero la verdad
        // tal vez si diseno mejor el circular podria dejarme lo de iteradores y usar el queue con su current y 
        //pasarmelo por los huevos pero me da pereza
        
    }
    @FXML
    private void pause() throws IOException{
        player.pause();
    }
    @FXML
    private void resume() throws IOException{
        player.resumeAudio();
    }
}
