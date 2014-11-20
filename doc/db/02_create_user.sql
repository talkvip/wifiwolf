CREATE USER wifiwolf IDENTIFIED BY 'wifiwolf';
GRANT ALL PRIVILEGES ON wifiwolf.* TO wifiwolf@"%" Identified by "wifiwolf";
flush privileges;