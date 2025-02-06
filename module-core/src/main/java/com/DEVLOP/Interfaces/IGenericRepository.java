package com.DEVLOP.Interfaces;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
/*
TODO
deixar comentario no significado de @NoRepositoryBean
 */
@NoRepositoryBean
public interface IGenericRepository <T,ID> extends JpaRepository<T, ID> {
}
