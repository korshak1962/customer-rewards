package com.korshak.customerrewards.services;

import com.korshak.customerrewards.controllers.CustomerTransaction;
import com.korshak.customerrewards.dao.Reward;
import com.korshak.customerrewards.dao.RewardRepository;
import com.korshak.customerrewards.serviceImpl.RewardServiceImp;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RewardServiceTest {

    @Mock
    RewardRepository rewardRepository;
    @InjectMocks
    RewardServiceImp rewardServiceInjected;

    @Test
    public void computeAndSaveRewards_Correct() {
        Reward reward = new Reward();
        when(rewardRepository.save(any())).thenReturn(reward);
        CustomerTransaction customerTransaction = new CustomerTransaction("tr_id", "custID", 12000, System.currentTimeMillis());
        int points = rewardServiceInjected.computeAndSaveReward(customerTransaction);
        assertThat(points).isEqualTo(90);
        verify(rewardRepository).save(any());
    }

    @Test
    public void computeAndSaveRewards_ZeroPoints() {
        CustomerTransaction customerTransaction = new CustomerTransaction("tr_id", "custID", 4900, System.currentTimeMillis());
        int points = rewardServiceInjected.computeAndSaveReward(customerTransaction);
        assertThat(points).isEqualTo(0);
    }

    @Test
    public void computeAndSaveRewards_RoundToFloor() {
        CustomerTransaction customerTransaction = new CustomerTransaction("tr_id", "custID", 5199, System.currentTimeMillis());
        int points = rewardServiceInjected.computeAndSaveReward(customerTransaction);
        assertThat(points).isEqualTo(1);
    }

    @Test
    public void computePointsAndSave_invalidArgumentCode() {
        CustomerTransaction customerTransaction = new CustomerTransaction(null, "custID", 25000, System.currentTimeMillis());
        int points = rewardServiceInjected.computeAndSaveReward(customerTransaction);
        assertThat(points).isEqualTo(-1);
    }

    @Test
    public void computeAndSaveRewards_DATABASE_ERROR() {
        when(rewardRepository.save(any())).thenThrow(new RuntimeException(""));
        CustomerTransaction customerTransaction = new CustomerTransaction("tr_id", "custID", 25000, System.currentTimeMillis());
        int points = rewardServiceInjected.computeAndSaveReward(customerTransaction);
        assertThat(points).isEqualTo(-2);
    }
}
