# java-cli-sbt-ssl-postgres-hibernate-trigger

## Description
Creates a small database table
called `dog` and populates with hql.

Added an insert trigger to `dog`.

Uses self-sign ssl.

## Tech stack
- java
- sbt
  - hibernate
  - hql
  - log4j
  - postgres driver

## Docker stack
- alpine:edge
- hseeberger/scala-sbt:11.0.2-oraclelinux7_1.3.5_2.12.10
- postgres:alpine

## To run
`sudo ./install.sh -u`

## To stop
`sudo ./install.sh -d`

## For help
`sudo ./install.sh -h`
