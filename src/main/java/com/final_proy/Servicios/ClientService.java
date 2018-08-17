package com.final_proy.Servicios;

import com.final_proy.Clases.Post;
import com.final_proy.Clases.Usuario;
import org.jasypt.util.password.StrongPasswordEncryptor;

import java.time.LocalDate;
import java.util.List;

public class ClientService extends GestionDB<Usuario>{

    public ClientService(){
       super(Usuario.class);
    }

    public List<Post> getAllPost(){
        return MantenimientoPost.getInstancia().findAll();
    }

    public List<Usuario> getAllUsers(){
        return  MantenimientoUsuario.getInstancia().findAll();
    }

    public  List<Post> getPostsByUser(String username){
        Usuario usuario = MantenimientoUsuario.getInstancia().find(username);
        return usuario.getPosts();
    }

    public Post setPost(String username, String imagen, String cuerpo){
        Usuario usuario = MantenimientoUsuario.getInstancia().find(username);
        Post post = new Post();
        post.setUsuario(usuario);
        post.setImagen(imagen);
        post.setCuerpo(cuerpo);
        post.setFecha(LocalDate.now());
        MantenimientoPost.getInstancia().crear(post);
        return post;
    }

    public boolean crearClient(Usuario usuario){

        //Guardar contraseña cifrada
        StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();
        String encryptedPassword = passwordEncryptor.encryptPassword(usuario.getPassword());
        usuario.setPassword(encryptedPassword);

        //creo el cliente o usuario.
        crear( usuario );

        return true;
    }


}
