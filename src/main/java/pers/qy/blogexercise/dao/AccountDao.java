package pers.qy.blogexercise.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pers.qy.blogexercise.model.entity.Account;

import java.util.List;

@Repository
public interface AccountDao extends JpaRepository<Account, Long> {

    @Query(value = "SELECT * FROM account WHERE user_email = :email", nativeQuery = true)
    Account findByUserEmail(@Param("email") String email);

    Account findByUserEmailAndPassword(String email, String password);
    Account save(Account account);

    List<Account> findAll();
}
