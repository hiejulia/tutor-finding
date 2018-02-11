#!/usr/bin/env bash

mysql -u root -ppassword < dbsetup.sql
mvn spring-boot:run
