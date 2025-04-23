package com.DEVLOP.Interfaces;

import com.DEVLOP.Entities.TransportMeans;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ITransportMeansRepo extends IGenericRepository<TransportMeans, Integer>, JpaSpecificationExecutor<TransportMeans> {
}
