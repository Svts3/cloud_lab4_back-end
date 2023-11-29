use lab6;

drop procedure if exists continentInsertion;
drop procedure if exists propertyInfoInsetion;
drop procedure if exists ownerInsertion;
drop function if exists FIND_AVERAGE_HEARTBEAT_RATE;
drop procedure if exists PRINT_AVERAGE_HEARTBEAT_RATE;
drop PROCEDURE if exists CREATE_DATABASES_FOR_EACH_OWNER;


Delimiter //
CREATE PROCEDURE continentInsertion(
    in name varchar(25)
)
begin
    insert into continent values (name);
end//

Delimiter //
CREATE PROCEDURE propertyInfoInsetion(
    in owner_id int,
    in watch_serial_number varchar(255)
)
begin
    insert into property_info(owner_id, watch_serial_number) values (owner_id, watch_serial_number);
end //
Delimiter //

CREATE PROCEDURE ownerInsertion(
)
begin
    declare x int;
    declare owner_first_name varchar(255);
    declare owner_last_name varchar(255);
    set x = 1;
    label1:
    while x <= 10
        do
            set owner_first_name = concat("Noname", x);
            set owner_last_name = concat("Noname", x);
            insert into owner(first_name, last_name, date_of_birth, gender)
            values (owner_first_name, owner_last_name, now(), gender);
            set x = x + 1;
        end while;
end //
Delimiter //

CREATE FUNCTION FIND_AVERAGE_HEARTBEAT_RATE(
) RETURNS int
    deterministic
begin
    RETURN (SELECT AVG(heartbeat_rate) FROM health_info);

end //

Delimiter //

CREATE PROCEDURE PRINT_AVERAGE_HEARTBEAT_RATE(
    out average_heartbeat_rate int
)
begin
    set average_heartbeat_rate = FIND_AVERAGE_HEARTBEAT_RATE();
    select average_heartbeat_rate;
end //

DELIMITER //
CREATE PROCEDURE CREATE_DATABASES_FOR_EACH_OWNER()
BEGIN

    DECLARE done int DEFAULT false;
    DECLARE first_name_var, last_name_var varchar(25);
    declare random_number int;
    declare database_name varchar(255);
    declare x int;


    DECLARE St_Cursor10 CURSOR
        FOR SELECT first_name, last_name FROM owner;

    DECLARE CONTINUE HANDLER
        FOR NOT FOUND SET done = true;

    OPEN St_Cursor10;

    myLoop:
    LOOP

        FETCH St_Cursor10 INTO first_name_var, last_name_var;
        IF done = true THEN
            LEAVE myLoop;
        END IF;
        set database_name = concat(first_name_var, last_name_var);
        SET @drop_query = CONCAT('drop database if exists ', database_name, ';');

        PREPARE drop_database FROM @drop_query;
        EXECUTE drop_database;
        DEALLOCATE PREPARE drop_database;

        SET @temp_query = CONCAT('CREATE DATABASE ', database_name, ';');

        PREPARE myquery FROM @temp_query;
        EXECUTE myquery;
        DEALLOCATE PREPARE myquery;

        set x = 1;
        set random_number = (select floor(rand() * 9 + 1));

        mywhile:
        loop
            set @table_name_var = concat(database_name, x);
            SET @create_table = CONCAT('CREATE TABLE ', database_name, ".", @table_name_var, " (name varchar(25));");

            PREPARE table_creation FROM @create_table;
            EXECUTE table_creation;
            DEALLOCATE PREPARE table_creation;
            if x = random_number then
                leave mywhile;
            end if;
            set x = x + 1;
        end loop;

    END LOOP;
    CLOSE St_Cursor10;
END //
DELIMITER ;

