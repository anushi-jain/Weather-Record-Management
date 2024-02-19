create database if not exists WeatherData;
Use WeatherData;

show tables;
desc weather;

Select * from weather;

drop table weather;


SELECT city
FROM Weather
WHERE city IN (
        SELECT city
        FROM Weather
        WHERE date LIKE '___11_____' OR date LIKE '___12_____' OR date LIKE '___01_____'
        GROUP BY city
        HAVING AVG(temp) BETWEEN 25 AND 32
    )
    AND city IN (
        SELECT city
        FROM Weather
        WHERE date LIKE '___06_____' OR date LIKE '___07_____'
        GROUP BY city
        HAVING AVG(humidity) BETWEEN 60 AND 95
    );
