CREATE TABLE `currencies_db`.`currency` (
  `currency` VARCHAR(3) NOT NULL,
  `timestamp` DATE NOT NULL,
  `bid` DOUBLE NOT NULL,
  `ask` DOUBLE NOT NULL,
  PRIMARY KEY (`currency`));