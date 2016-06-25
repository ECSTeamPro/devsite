-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema devsite
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema devsite
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `devsite` DEFAULT CHARACTER SET utf8 ;
USE `devsite` ;

-- -----------------------------------------------------
-- Table `devsite`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `devsite`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(200) NOT NULL,
  `password` VARCHAR(200) NOT NULL,
  `password_hash` VARCHAR(200) CHARACTER SET 'utf8' NULL COMMENT 'password chua ma hoa\n',
  `email` VARCHAR(200) NOT NULL,
  `phone` VARCHAR(200) NULL,
  `address` TEXT NULL,
  `avatar` TEXT NULL,
  `fullname` VARCHAR(200) NULL,
  `sex` INT NULL COMMENT '1- la nam\n2- la nu\n',
  `created_at` DATETIME NULL,
  `updated_at` DATETIME NULL,
  `expired_at` INT NULL COMMENT 'ngay het han tai khoan',
  `status` INT NOT NULL COMMENT '0- inactive\n5-draft\n10-active',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `devsite`.`role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `devsite`.`role` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `display_name` VARCHAR(200) NOT NULL,
  `created_at` DATETIME NULL,
  `updated_at` DATETIME NULL,
  `status` INT NOT NULL COMMENT '0-inactive\n10-active\n5-draft\n',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `display_name_UNIQUE` (`display_name` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `devsite`.`user_role_asm`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `devsite`.`user_role_asm` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL COMMENT 'map voi id cua bang user',
  `role_id` INT NOT NULL COMMENT 'map voi id cua chuc danh',
  `created_at` DATETIME NULL,
  `updated_at` DATETIME NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `devsite`.`category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `devsite`.`category` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `display_name` VARCHAR(200) NOT NULL,
  `parent_id` INT NULL COMMENT 'null la cha con lai la con cua cha theo id\n',
  `level` INT NOT NULL COMMENT '1-la menu cao nhat\n2- la menu cao thu 2\n3- la menu cao thu 3',
  `created_at` INT NULL,
  `updated_at` INT NULL,
  `status` INT NOT NULL COMMENT '0-inactive\n10-active\n5-draft\n',
  `type` INT NOT NULL COMMENT '1- he thong\n2- trang FE\n',
  `weight` INT NULL COMMENT 'thu tu sap xep\n',
  `user_id` INT NOT NULL,
  `description` TEXT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `devsite`.`role_category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `devsite`.`role_category` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `role_id` INT NOT NULL,
  `category_id` INT NOT NULL,
  `created_at` DATETIME NULL,
  `updated_at` DATETIME NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `devsite`.`content`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `devsite`.`content` (
  `id` INT NOT NULL,
  `display_name` VARCHAR(200) NOT NULL,
  `status` INT NOT NULL,
  `created_at` DATETIME NULL,
  `updated_at` DATETIME NULL,
  `url_video` VARCHAR(200) NULL,
  `content` TEXT NOT NULL,
  `description` TEXT NULL,
  `type` INT NULL,
  `url_image` VARCHAR(200) NULL,
  `tags` VARCHAR(200) NULL,
  `short_description` TEXT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `devsite`.`user_activity`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `devsite`.`user_activity` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `action` VARCHAR(200) NULL,
  `user_id` INT NOT NULL,
  `ip_address` VARCHAR(45) NULL,
  `description` TEXT NULL,
  `created_at` DATETIME NULL,
  `updated_at` DATETIME NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
