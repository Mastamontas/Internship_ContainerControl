package com.DEVLOP.Application.Mappers;

import com.DEVLOP.Application.DTOS.TransportMeansDto;
import com.DEVLOP.Entities.TransportMeans;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ITransportMeansMapper {
    @Mapping(source = "id", target = "id")
    @Mapping(source = "comment", target = "comment")
    TransportMeans MapToTransportMeans(TransportMeansDto transportMeansDto);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "comment", target = "comment")
    TransportMeansDto MapToTransportMeansDto(TransportMeans transportMeans);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "id", target = "id")
    @Mapping(source = "comment", target = "comment")
    TransportMeans UpdateTransportMeans(TransportMeansDto transportMeansDto, @MappingTarget TransportMeans transportMeans);

}
