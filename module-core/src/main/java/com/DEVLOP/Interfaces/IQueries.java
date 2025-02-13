package com.DEVLOP.Interfaces;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface IQueries<T> {
    CompletableFuture<List<T>> findAllAsync();
    /*
    only general queries that serve all entities
    find by ID async
     */
    
}
