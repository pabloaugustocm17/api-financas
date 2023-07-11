CREATE TABLE `wallet`(

    `id` BINARY(16) PRIMARY KEY,
    `amount` DOUBLE,
    `user` BINARY(16),
    FOREIGN KEY (`id`) REFERENCES user(`id`)

)ENGINE=InnoDB DEFAULT CHARSET=latin1;