package com.example.mannagment.mannagment.auth_file;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface Auth_repository extends JpaRepository<user_object,String>{

    @Query("SELECT a FROM mana_db a WHERE a.name = :name")
    user_object findbyname(@Param("name") String name);
}
