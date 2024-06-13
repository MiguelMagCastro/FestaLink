-- MySQL Workbench Synchronization
-- Generated: 2024-05-28 22:31
-- Model: New Model
-- Version: 1.0
-- Project: Name of the project
-- Author: migue

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

ALTER TABLE `mydb`.`eventos` 
DROP FOREIGN KEY `ID_Fornecedor`;

ALTER TABLE `mydb`.`salões de festas` 
DROP FOREIGN KEY `ID_Proprietário`;

ALTER TABLE `mydb`.`eventos` 
DROP COLUMN `ID_Fornecedor`,
DROP INDEX `ID_Fornecedor_idx` ;
;

ALTER TABLE `mydb`.`reservas` 
DROP COLUMN `Detalhes_Pagamento`;

ALTER TABLE `mydb`.`salões de festas` 
DROP COLUMN `ID_Proprietario`,
DROP INDEX `ID_Proprietário_idx` ;
;

CREATE TABLE IF NOT EXISTS `mydb`.`feedback` (
  `ID_Feedack` INT(10) UNSIGNED NOT NULL,
  `ID_Salao` INT(10) UNSIGNED NOT NULL,
  `ID_Usuario` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `Nota` DOUBLE UNSIGNED NOT NULL,
  `Data` DATE NOT NULL,
  `Descricao_Comentario` VARCHAR(250) NULL DEFAULT NULL,
  PRIMARY KEY (`ID_Feedack`),
  INDEX `ID_Salao_idx` (`ID_Salao` ASC) VISIBLE,
  INDEX `ID_Usuario_idx` (`ID_Usuario` ASC) VISIBLE,
  CONSTRAINT `ID_Salao`
    FOREIGN KEY (`ID_Salao`)
    REFERENCES `mydb`.`salões de festas` (`ID_Salao`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `ID_Usuario`
    FOREIGN KEY (`ID_Usuario`)
    REFERENCES `mydb`.`usuários` (`ID_Usuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;

DROP TABLE IF EXISTS `mydb`.`pagamentos` ;

DROP TABLE IF EXISTS `mydb`.`fornecedores` ;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
