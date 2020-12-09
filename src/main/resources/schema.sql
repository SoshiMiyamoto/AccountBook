CREATE TABLE IF NOT EXISTS `item` (
  `id`     bigint(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `name`   varchar(255),
  `price`  real,
  `vendor` varchar(255)
);