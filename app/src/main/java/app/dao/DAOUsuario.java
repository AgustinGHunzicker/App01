package app.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import app.model.Usuario;

import java.util.List;

@Dao
public interface DAOUsuario {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insertar(Usuario usuario);

    @Delete
    void borrar(Usuario usuario);

    @Update
    void actualizar(Usuario usuario);

    @Query("SELECT * FROM usuario WHERE id = :id LIMIT 1")
    Usuario buscar(long id);

    @Query("SELECT * FROM usuario")
    List<Usuario> buscarTodos();
}
