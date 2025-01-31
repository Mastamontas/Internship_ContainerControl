package com.DEVLOP.Interfaces;

import java.util.concurrent.CompletableFuture;
public interface ICommands<T>{
    CompletableFuture<Void> createAsync(T entity);
    CompletableFuture<Void> deleteByIDAsync(int id, T entity);
    CompletableFuture<Void> updateAsync(T entity);
}
