CREATE TABLE `wallet`(

    `id` BINARY(16) PRIMARY KEY,
    `amount` DOUBLE,
    `user_id` BINARY(16)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;