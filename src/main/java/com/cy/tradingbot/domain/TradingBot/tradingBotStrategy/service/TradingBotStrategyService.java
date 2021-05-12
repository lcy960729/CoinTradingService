package com.cy.tradingbot.domain.tradingBot.tradingBotStrategy.service;

import com.cy.tradingbot.domain.tradingBot.TradingBot;
import com.cy.tradingbot.domain.tradingBot.service.TradingBotService;
import com.cy.tradingbot.domain.tradingBot.tradingBotStrategy.TradingBotStrategy;
import com.cy.tradingbot.domain.user.User;
import com.cy.tradingbot.dto.tradingBotStrategy.RequestTradingBotStrategyDTO;
import com.cy.tradingbot.dto.tradingBotStrategy.ResponseTradingBotStrategyDTO;
import com.cy.tradingbot.exception.AccessDeniedException;
import com.cy.tradingbot.exception.NotFoundEntityException;
import com.cy.tradingbot.mapper.EntityMapper;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract class TradingBotStrategyService<Entity extends TradingBotStrategy, Repository extends JpaRepository<? super Entity, Long>, Mapper extends EntityMapper<ResponseDTO, Entity>, RequestDTO extends RequestTradingBotStrategyDTO, ResponseDTO extends ResponseTradingBotStrategyDTO> {

    private final TradingBotService tradingBotService;
    protected final Repository repository;
    private final Mapper mapper;

    protected TradingBotStrategyService(TradingBotService tradingBotService, Repository repository, Mapper mapper) {
        this.tradingBotService = tradingBotService;
        this.repository = repository;
        this.mapper = mapper;
    }

    protected abstract Entity createEntity(TradingBot tradingBot, RequestDTO requestDTO);

    protected abstract void updateEntity(Entity entity, RequestDTO requestDTO);

    public ResponseDTO create(User user, long tradingBotId, RequestDTO requestDTO) {
        TradingBot tradingBot = tradingBotService.getEntity(user, tradingBotId);

        Entity entity = createEntity(tradingBot, requestDTO);

        entity = repository.save(entity);

        return mapper.toDTO(entity);
    }

    public ResponseDTO update(User user, long tradingBotId, long strategyId, RequestDTO requestDTO) {
        Entity entity = getEntity(user, tradingBotId, strategyId);
        updateEntity(entity, requestDTO);

        entity = repository.save(entity);

        return mapper.toDTO(entity);
    }

    public void delete(User user, long tradingBotId, long strategyId) {
        Entity entity = getEntity(user, tradingBotId, strategyId);

        repository.delete(entity);
    }

    public ResponseDTO get(User user, long tradingBotId, long strategyId) {
        return mapper.toDTO(getEntity(user, tradingBotId, strategyId));
    }

    public Entity getEntity(User user, long tradingBotId, long strategyId) {
        @SuppressWarnings("unchecked")
        Entity entity = (Entity) repository.findById(strategyId).orElseThrow(NotFoundEntityException::new);
        TradingBot tradingBot = entity.getTradingBot();

        if (tradingBot.getId() != tradingBotId || !tradingBot.getUser().equals(user))
            throw new AccessDeniedException();

        return entity;
    }
}
