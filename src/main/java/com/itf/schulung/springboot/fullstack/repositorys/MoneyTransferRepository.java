package com.itf.schulung.springboot.fullstack.repositorys;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.itf.schulung.springboot.fullstack.model.MoneyTransferForm;
@Repository
public interface MoneyTransferRepository extends CrudRepository<MoneyTransferForm,Long>{

}
