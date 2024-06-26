package fiap.ddd.Repositories;

import fiap.ddd.Entities._BaseEntity;

import java.util.List;

public interface _BaseRepository<T extends _BaseEntity> {
    public void create(T entity);

    public List<T> readAll();

    public void update(int id, T entity);

    public void delete(int id);


}
