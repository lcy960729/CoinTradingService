package com.cy.tradingbot.mapper;

import org.mapstruct.AfterMapping;
import org.mapstruct.MappingTarget;

public interface EntityMapper<D, E> {
    D toDTO(E entity);
}