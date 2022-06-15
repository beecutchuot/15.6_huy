package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.entity.Student;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findByFirstName(String firstName);

//   Student findByFirstNameAndLastName(String firstName, String lastName);

   List<Student> findByFirstNameOrLastName(String firstName, String lastName);

   List<Student> findByFirstNameIn(List<String> firstNames);

   List<Student> findByFirstNameContaining(String firstName);

    List<Student> findByFirstNameStartsWith(String firstName);

    @Query("From Student where firstName= :firstName and lastName= :lastName")
    Student getByFirstNameAndLastName(String lastName, @Param("firstName") String firstName);


    @Transactional
    @Modifying
    @Query("Update Student set firstName= :firstName where id= :id")
    Integer updateFirstNameWithJpsql(Long id, String firstName);


    @Transactional
    @Modifying
    @Query("Delete  from Student where firstName= :firstName" )
    Integer deleteWithJpsql(String firstName);
}
