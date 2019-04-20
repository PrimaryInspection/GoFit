-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema fitness
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema fitness
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `fitness` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `fitness` ;

-- -----------------------------------------------------
-- Table `fitness`.`activity_item`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fitness`.`activity_item` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `calories` INT(11) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 10
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `fitness`.`lifestyle`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fitness`.`lifestyle` (
  `id` INT(11) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `fitness`.`role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fitness`.`role` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 11
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `fitness`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fitness`.`users` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(45) NOT NULL,
  `second_name` VARCHAR(45) NOT NULL,
  `login` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `birthday` DATE NOT NULL,
  `weight` INT(11) NOT NULL,
  `weight_goal` INT(11) NOT NULL,
  `height` INT(11) NOT NULL,
  `calories_norm` INT(11) NOT NULL,
  `lifestyle_id` INT(11) NOT NULL,
  `status` INT(11) NOT NULL DEFAULT '1',
  `role_id` INT(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `login_UNIQUE` (`login` ASC) VISIBLE,
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE,
  INDEX `role_id_idx` (`role_id` ASC) VISIBLE,
  INDEX `lifestyle_fk_idx` (`lifestyle_id` ASC) VISIBLE,
  CONSTRAINT `lifestyle_fk`
    FOREIGN KEY (`lifestyle_id`)
    REFERENCES `fitness`.`lifestyle` (`id`),
  CONSTRAINT `role_fk`
    FOREIGN KEY (`role_id`)
    REFERENCES `fitness`.`role` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 27
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `fitness`.`activity`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fitness`.`activity` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `time_spent` INT(11) NOT NULL,
  `date` DATE NOT NULL,
  `user_id` INT(11) NOT NULL,
  `activity_item_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_activity_users1_idx` (`user_id` ASC) VISIBLE,
  INDEX `fd_activity_item_idx` (`activity_item_id` ASC) VISIBLE,
  CONSTRAINT `fd_activity_item`
    FOREIGN KEY (`activity_item_id`)
    REFERENCES `fitness`.`activity_item` (`id`),
  CONSTRAINT `fk_activity_users1`
    FOREIGN KEY (`user_id`)
    REFERENCES `fitness`.`users` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 54
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `fitness`.`meal_item`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fitness`.`meal_item` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `fats` INT(11) NOT NULL,
  `calories` INT(11) NOT NULL,
  `proteins` INT(11) NOT NULL,
  `carbs` INT(11) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 28
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `fitness`.`meal_type`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fitness`.`meal_type` (
  `id` INT(11) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `fitness`.`meal`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fitness`.`meal` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `weight` INT(11) NOT NULL,
  `date` DATE NOT NULL,
  `user_id` INT(11) NOT NULL,
  `meal_type_id` INT(11) NOT NULL,
  `meal_item_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_meal_users_idx` (`user_id` ASC) VISIBLE,
  INDEX `fk_meal_meal_type1_idx` (`meal_type_id` ASC) VISIBLE,
  INDEX `fk_meal_meal_item1_idx` (`meal_item_id` ASC) VISIBLE,
  CONSTRAINT `fk_meal_meal_item1`
    FOREIGN KEY (`meal_item_id`)
    REFERENCES `fitness`.`meal_item` (`id`),
  CONSTRAINT `fk_meal_meal_type1`
    FOREIGN KEY (`meal_type_id`)
    REFERENCES `fitness`.`meal_type` (`id`),
  CONSTRAINT `fk_meal_users`
    FOREIGN KEY (`user_id`)
    REFERENCES `fitness`.`users` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 170
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
