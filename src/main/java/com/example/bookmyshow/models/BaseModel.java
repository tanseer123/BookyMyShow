package com.example.bookmyshow.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    private Date createdAt;

    @LastModifiedDate
    private Date lastModifiedAt;
    //EntityListeners - says that what ever changes happen to this calss attributes
    //just keep on listening to them, listen to all these events or
    //@Temporal(temporalType.TIME,TIMESTAMP,

}
/*
ORM -> Object Relational Mapping
lib or framework which provides way to map your objects with table. for every model corresponding table in DB
Auto generation of SQL Queries
Converting classes into corresponding tables in to DB
easy way to interact with DB

Hibernate  orm--> Most popular JPA uses internally

--------------------------------------------------------------
MappedSuperClass
class BaseModel{
id
...
}
class user extends BaseModel{
name,email,passowrd
}

user table
id createdat lastmodfiedat name email password

property 1 :No table for parent class
property 2: All the attr of parent class should be present in the child class in table

 */

