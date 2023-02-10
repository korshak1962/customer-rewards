package com.korshak.customerrewards.controllers;

import com.korshak.customerrewards.services.RewardService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST API for customer reward calculation and store.
 */
@RestController
public class RewardController {

    Logger logger = LogManager.getLogger(RewardController.class);

    @Autowired
    RewardService rewardService;

    /**
     * calc and store rewards point.
     * @param transaction
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/reward")
    public ResponseEntity<String> computeAndSaveReward(@RequestBody CustomerTransaction transaction) {
        logger.atDebug().log("income transaction: ",transaction.toString());
        int points = rewardService.computeAndSaveReward(transaction);
        if (points < 0) {
            return new ResponseEntity<String>(RETURN_CODES.getDescriptionByCode(points), HttpStatusCode.valueOf(500));
        }
        return new ResponseEntity<String>(String.valueOf(points), HttpStatusCode.valueOf(200));
    }

//todo implement method to calculate rewards for List<CustomerTransaction>

}
