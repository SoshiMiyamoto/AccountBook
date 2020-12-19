CREATE TABLE IF NOT EXISTS `item` (
  `id`     bigint(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `category`   varchar(255),
  `date` date,
  `expenses`  real,
  `memo` varchar(255)
);