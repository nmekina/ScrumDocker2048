FROM mysql/mysql-server:5.7

ENV MYSQL_ALLOW_EMPTY_PASSWORD=true
ENV MYSQL_USER=2048
ENV MYSQL_PASSWORD=2048
ENV MYSQL_DATABASE=database_2048

CREATE TABLE t_user (userid serial primary key, name VARCHAR(255), password VARCHAR(255), timestamp DATETIME);

CREATE TABLE t_statistics (userid serial PRIMARY KEY, highscore INT, gamesPlayed INT, FOREIGN KEY (userid) REFERENCES t_user(userid));
