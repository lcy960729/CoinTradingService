package com.cy.tradingbot.mapper;

import com.cy.tradingbot.domain.user.User;
import com.cy.tradingbot.dto.user.UserDTO;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper extends EntityMapper<UserDTO, User>  {
    @AfterMapping
    default void makeLinks(@MappingTarget UserDTO userDTO){
        userDTO.makeLinks();
    }
}
