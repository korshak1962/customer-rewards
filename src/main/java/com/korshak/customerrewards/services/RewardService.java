package com.korshak.customerrewards.services;

import com.korshak.customerrewards.controllers.CustomerTransaction;


/**
 * Service to calculate and store customer rewards.
 */
public interface RewardService {
    /**
     * @param transaction
     * @return
     */
    int computeAndSaveReward(CustomerTransaction transaction);
    // todo implement computeAndSaveReward(List<CustomerTransaction> transactions)
}
