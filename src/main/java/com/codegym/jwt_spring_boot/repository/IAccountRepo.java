package com.codegym.jwt_spring_boot.repository;

import com.codegym.jwt_spring_boot.model.Account;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IAccountRepo extends CrudRepository<Account, Integer> {
    List<Account> findAllByUsernameContaining(String username);

    @Query(value = "select a from Account a where a.username like concat('%',:name,'%')")
    List<Account> findAllByUsernameHQL(@Param("name") String username);

    Account findByUsername(String username);

}
