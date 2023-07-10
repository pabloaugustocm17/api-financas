CREATE TABLE IF NOT EXISTS `user`(
    `id` BINARY(16) PRIMARY KEY,
    `name` VARCHAR(50) NOT NULL,
    `email` VARCHAR(50) NOT NULL,
    `password` VARCHAR(50) NOT NULL,
    `created_date` DATETIME NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;