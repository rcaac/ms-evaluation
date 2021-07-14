package com.atachagua.ms.evaluation.profile.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.atachagua.ms.evaluation.profile.entity.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {

    @Query("select p from Person p where p.state='1'")
    List<Person> findAllCustom();

    @Query("select p from Person p where upper(p.firstName) like :name and p.state='1'")
    List<Person> findByLikeName(@Param("name") String name);

}
