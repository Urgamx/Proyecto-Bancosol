Usuario y contraseña a establecer por todos

CREATE USER 'admin'@'localhost' IDENTIFIED BY 'admin';
GRANT ALL PRIVILEGES ON bancosol_db.* TO 'admin'@'localhost';
GRANT ALL PRIVILEGES ON *.* TO 'admin'@'localhost'; 
FLUSH PRIVILEGES;
