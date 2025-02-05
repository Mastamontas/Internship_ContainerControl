package com.DEVLOP.Interfaces;

import java.util.List;
import java.util.concurrent.CompletableFuture;
/*
just for generic query methods
find all
get by id
 */
public interface IQueries<T> {
    CompletableFuture<List<T>> findAllAsync();
    /*
    only general queries that serve all entities
    find by ID async
     */
    //CompletableFuture<T> returnByIDAsync(int id);
    
}
