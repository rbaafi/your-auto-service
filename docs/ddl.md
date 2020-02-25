# SQL data definition language (DDL)

```sqlite
CREATE TABLE IF NOT EXISTS `Car`
(
    `car_id`      INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    `make`        TEXT,
    `model`       TEXT,
    `year`        INTEGER                           NOT NULL,
    `acquisition` INTEGER
);
CREATE INDEX IF NOT EXISTS `index_Car_make` ON `Car` (`make`);
CREATE INDEX IF NOT EXISTS `index_Car_model` ON `Car` (`model`);
CREATE INDEX IF NOT EXISTS `index_Car_year` ON `Car` (`year`);
CREATE INDEX IF NOT EXISTS `index_Car_acquisition` ON `Car` (`acquisition`);

CREATE TABLE IF NOT EXISTS `Action`
(
    `action_id`   INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    `service_id`  INTEGER                           NOT NULL,
    `summary`     TEXT COLLATE NOCASE,
    `description` TEXT COLLATE NOCASE,
    `serviceType` TEXT COLLATE NOCASE,
    FOREIGN KEY (`service_id`) REFERENCES `Service` (`service_id`) ON UPDATE NO ACTION ON DELETE CASCADE
);
CREATE INDEX IF NOT EXISTS `index_Action_service_id` ON `Action` (`service_id`);
CREATE INDEX IF NOT EXISTS `index_Action_summary` ON `Action` (`summary`);
CREATE INDEX IF NOT EXISTS `index_Action_serviceType` ON `Action` (`serviceType`);

CREATE TABLE IF NOT EXISTS `AvailableCar`
(
    `available_car_id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    `make`             TEXT,
    `model`            TEXT,
    `year`             INTEGER                           NOT NULL
);
CREATE INDEX IF NOT EXISTS `index_AvailableCar_make` ON `AvailableCar` (`make`);
CREATE INDEX IF NOT EXISTS `index_AvailableCar_model` ON `AvailableCar` (`model`);
CREATE INDEX IF NOT EXISTS `index_AvailableCar_year` ON `AvailableCar` (`year`);

CREATE TABLE IF NOT EXISTS `Service`
(
    `service_id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    `car_id`     INTEGER                           NOT NULL,
    `date`       INTEGER,
    `mileage`    INTEGER                           NOT NULL,
    FOREIGN KEY (`car_id`) REFERENCES `Car` (`car_id`) ON UPDATE NO ACTION ON DELETE CASCADE
);
CREATE INDEX IF NOT EXISTS `index_Service_car_id` ON `Service` (`car_id`);
CREATE INDEX IF NOT EXISTS `index_Service_date` ON `Service` (`date`);
CREATE INDEX IF NOT EXISTS `index_Service_mileage` ON `Service` (`mileage`);

``` 

[Download](ddl.sql)