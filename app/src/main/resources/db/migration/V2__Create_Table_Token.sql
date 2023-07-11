CREATE TABLE `token`(

    `id` BINARY(16) PRIMARY KEY,
    `email` VARCHAR(50) NOT NULL,
    `last_access` DATETIME

)ENGINE=InnoDB DEFAULT CHARSET=latin1;