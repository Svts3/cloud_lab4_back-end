use lab6;
drop trigger if exists create_country_trigger;
drop trigger if exists create_continent_trigger;
drop trigger if exists WATCH_VALIDATION;
drop trigger if exists min_cardinality_for_user_name;
drop trigger if exists OWNER_FIRST_NAME_INSERTION_VALIDATION;
drop trigger if exists log_continent_deletion;
drop trigger if exists delete_continent_trigger;
drop trigger if exists update_country_trigger;


delimiter //
CREATE TRIGGER create_country_trigger
    BEFORE INSERT
    ON country
    FOR EACH ROW
begin
    IF (new.continent_name NOT IN (SELECT name from continent)) THEN
        SIGNAL SQLSTATE "45000" SET MESSAGE_TEXT = "Such continent name does not exist!";
    end if;
end//
DELIMITER //
CREATE TRIGGER delete_continent_trigger
    BEFORE delete
    ON continent
    FOR EACH ROW
BEGIN
    delete from country where continent_name = old.name;
end //
DELIMITER //
CREATE TRIGGER create_continent_trigger
    BEFORE INSERT
    ON continent
    FOR EACH ROW
BEGIN
    IF (new.name in (SELECT name from continent)) THEN
        SIGNAL SQLSTATE "45000" SET MESSAGE_TEXT = "The Name is already in the table";

    END IF;
end //
DELIMITER //

delimiter //
CREATE TRIGGER update_country_trigger
    BEFORE update
    ON country
    FOR EACH ROW
begin
    IF (new.continent_name NOT IN (SELECT name from continent)) THEN
        SIGNAL SQLSTATE "45000" SET MESSAGE_TEXT = "Such continent name does not exist!";
    end if;
end//

-- 3(A)
CREATE TRIGGER WATCH_VALIDATION
    BEFORE INSERT
    ON Watch
    FOR EACH ROW
begin
    IF (right(new.serial_number, 2) = "00") then
        SIGNAL SQLSTATE "45000" SET MESSAGE_TEXT = "serial number can't contain 00 in the end";
    end if;
end //
Delimiter //
-- 3(E)
CREATE TRIGGER log_continent_deletion
    BEFORE Delete
    ON continent
    FOR EACH ROW
begin
    insert into continent_delete_logger(continent, deletion_time) values (old.name, now());
end //
Delimiter //
-- 3(J)
CREATE TRIGGER OWNER_FIRST_NAME_INSERTION_VALIDATION
    BEFORE INSERT
    ON user
    FOR EACH ROW
begin
    IF (not (new.first_name = "Svitlana" or new.first_name = "Olha" or new.first_name = "Taras" or
             new.first_name = "Petro")) then
        SIGNAL SQLSTATE "45000" SET MESSAGE_TEXT = "You can only insert Svitlana, Petro, Olha or Taras";
    end if;
end //