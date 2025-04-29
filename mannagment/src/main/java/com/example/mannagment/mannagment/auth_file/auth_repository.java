package com.example.mannagment.mannagment.auth_file;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface auth_repository extends JpaRepository<user_object,String>{

}
