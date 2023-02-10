package com.korshak.customerrewards.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RewardRepository extends CrudRepository<Reward, String> {
   // todo for report: List<Reward> findByCustomerID(@NonNull String customerID);
}
