package com.cy.tradingbot.controller;

import com.cy.tradingbot.util.TimeCalculator;
import com.cy.tradingbot.configure.security.CustomUserDetail;
import com.cy.tradingbot.domain.user.User;
import com.cy.tradingbot.repository.TradingInfoRepository;
import com.cy.tradingbot.domain.user.service.StartTradingService;
import com.cy.tradingbot.domain.user.service.StopTradingService;
import com.cy.tradingbot.domain.log.service.LogService;
import com.cy.tradingbot.domain.record.service.RecordService;
import com.cy.tradingbot.domain.user.service.GetUserService;
import com.cy.tradingbot.domain.user.service.UpdateUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Optional;

@Controller
public class HomeController {

    @Autowired
    private RecordService recordService;
    @Autowired
    private LogService logService;

    @Autowired
    private StartTradingService startTradingService;
    @Autowired
    private StopTradingService stopTradingService;

    @Autowired
    private UpdateUserService updateUserService;
    @Autowired
    private GetUserService getUserService;

    @Autowired
    private TradingInfoRepository tradingInfoRepository;

    @GetMapping("/")
    public ModelAndView home(@AuthenticationPrincipal CustomUserDetail userDetail, @RequestParam("stop") Optional<String> stop) {
        User user = getUserService.get(userDetail.getUserId());

        ModelAndView modelAndView = new ModelAndView();
        if (stop.isPresent()) {
            modelAndView.addObject("stop", "거래 중인 코인이 있습니다. 잠시 후 중단 시도 해 주세요");
        }

        modelAndView.setViewName("home");

        modelAndView.addObject("maxOfCandle", user.getTradingSettings().getMaxOfCandles());
        modelAndView.addObject("numOfMovingAverageWindow", user.getTradingSettings().getNumOfMovingAverageWindow());
        modelAndView.addObject("coinList", user.getTradingSettings().getCoins());
        modelAndView.addObject("numOfCoinsForPurchase", user.getTradingSettings().getNumOfCoinsForPurchase());

        modelAndView.addObject("records", recordService.getAll(userDetail.getUserId()));
        modelAndView.addObject("logs", logService.getAll(userDetail.getUserId()));

        if (!tradingInfoRepository.isExistUser(user)) {
            modelAndView.addObject("state", "ready");
            return modelAndView;
        }

        modelAndView.addObject("state", "running");

        modelAndView.addObject("closingTime", TimeCalculator.closingTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        modelAndView.addObject("currentTime", TimeCalculator.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        modelAndView.addObject("coins", tradingInfoRepository.getCoinTradingInfoDTOList(user));

        return modelAndView;
    }

    @PostMapping("/start")
    public RedirectView start(@AuthenticationPrincipal CustomUserDetail userDetail, HttpServletRequest httpServletRequest) {
        User user = getUserService.get(userDetail.getUserId());

        if (tradingInfoRepository.isExistUser(user)) {
            if (!stopTradingService.stopTrading(user)) {
                return new RedirectView("/?stop=fail");
            }
        } else {
            Map<String, String[]> params = httpServletRequest.getParameterMap();
            updateUserService.update(userDetail.getUserId(),
                    Integer.parseInt(params.get("maxOfCandle")[0]),
                    Integer.parseInt(params.get("numOfMovingAverageWindow")[0]),
                    params.get("coinList")[0],
                    Integer.parseInt(params.get("numOfCoinsForPurchase")[0]));

            startTradingService.createTradingInfos(user);
        }

        return new RedirectView("/");
    }
}
