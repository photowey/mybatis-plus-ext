@echo off
echo mybatis-plus-ext compile and deploy...

call mvn clean -DskipTests source:jar deploy