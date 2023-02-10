package com.korshak.customerrewards.serviceImpl;

import com.korshak.customerrewards.controllers.CustomerTransaction;
import com.korshak.customerrewards.controllers.RETURN_CODES;
import com.korshak.customerrewards.dao.Reward;
import com.korshak.customerrewards.dao.RewardRepository;
import com.korshak.customerrewards.services.RewardService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * implementation of @RewardService {@link RewardService}
 */
@Service
public class RewardServiceImp implements RewardService {


    Logger logger = LogManager.getLogger(RewardServiceImp.class);

    @Autowired
    RewardRepository rewardRepository;

    @Override
    public int computeAndSaveReward(CustomerTransaction transaction) {
        if (isTransactionInvalid(transaction)) {
            logger.atError().log("Inavalid transaction %s", transaction);
            return RETURN_CODES.ILLEGAL_ARGUMENTS.getCode();
        }
        if (transaction.getAmount() < 5100) {
            return 0;
        }
        int rewardsPoints = calcPoints(transaction.getAmount());
        Reward reward = new Reward(transaction.getTransactionID(), transaction.getCustomerID(), rewardsPoints, transaction.getTime());
        try {
            rewardRepository.save(reward);
        } catch (Exception e) {
            logger.atError().log(e);
            return RETURN_CODES.DATABASE_ERROR.getCode();
        }
        return rewardsPoints;
    }

    private static boolean isTransactionInvalid(CustomerTransaction transaction) {
        return transaction == null
                || transaction.getTransactionID() == null
                || transaction.getAmount() < 0
                || transaction.getCustomerID() == null
                || transaction.getTime() < 0;
    }

    private static int calcPoints(int amount) {
        final double over50 = (amount - 5000);
        int rewardsPoint = (int) Math.floor(over50 / 100);
        if (amount < 10100) {
            return rewardsPoint;
        }
        final double over100 = (amount - 10000);
        rewardsPoint += (int) Math.floor(over100 / 100);
        return rewardsPoint;
    }
}
