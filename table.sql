-- -----------------------------------------------------
-- Table `scheduledb`.`author`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `scheduledb`.`author` (
                                                     `id` BIGINT NOT NULL AUTO_INCREMENT,
                                                     `name` VARCHAR(45) NOT NULL,
    `email` VARCHAR(45) NULL,
    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP,
    `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`))
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `scheduledb`.`schedule`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `scheduledb`.`schedule` (
                                                       `id` BIGINT NOT NULL AUTO_INCREMENT,
                                                       `task` VARCHAR(256) NULL,
    `password` VARCHAR(200) NOT NULL,
    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP,
    `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `author_id` BIGINT NOT NULL,
    PRIMARY KEY (`id`),
    INDEX `fk_schedule_Author_idx` (`author_id` ASC) VISIBLE,
    CONSTRAINT `fk_schedule_Author`
    FOREIGN KEY (`author_id`)
    REFERENCES `scheduledb`.`author` (`id`)
                                                    ON DELETE NO ACTION
                                                    ON UPDATE NO ACTION)
    ENGINE = InnoDB;
