CREATE FUNCTION get_airline_by_username(_username text)
RETURNS TABLE("airline_id" bigint,
			 "airline_name" text,
			 "country_id" smallint,
			 "country_name" text,
			 "user_id" bigint,
			 "username" text,
			 "password" text,
			 "email" text,
			 "role_id" integer,
			 "role_name" text)
LANGUAGE plpgsql AS
$$
	BEGIN
		RETURN QUERY
		SELECT * FROM get_airline_companies()
		AS "airlines"
		WHERE "airlines"."username"=_username;
	END;
$$

CREATE FUNCTION get_customer_by_username(_username text)
RETURNS TABLE("customer_id" bigint,
			 "first_name" text,
			 "last_name" text,
			 "address" text,
			 "phone_no" text,
			 "credit_card_no" text,
			 "user_id" bigint,
			  "username" text,
			  "password" text,
			  "email" text,
			 "role_id" integer,
			 "role_name" text)
LANGUAGE plpgsql AS
$$
	BEGIN
		RETURN QUERY
		SELECT * FROM get_customers()
		AS "customers"
		WHERE "customers"."username"=_username;
	END;
$$

CREATE FUNCTION get_user_by_username(_username text)
RETURNS TABLE(
			 "user_id" bigint,
			  "username" text,
			  "password" text,
			  "email" text,
			 "role_id" integer,
			 "role_name" text)
LANGUAGE plpgsql AS
$$
	BEGIN
		RETURN QUERY
		SELECT * FROM get_users()
		AS "users"
		WHERE "users"."username"=_username;
	END;
$$

CREATE OR REPLACE FUNCTION get_flights_by_parameters(_origin_counry_id int, _detination_country_id int, _date date)
RETURNS TABLE("flight_id" bigint,
			 "airline_id" bigint,
			 "airline_name" text,
			 "country_id" smallint,
			 "country_name" text,
			 "user_id" bigint,
			 "username" text,
			 "password" text,
			 "email" text,
			 "role_id" integer,
			 "role_name" text,
			 "orgin_country_id" integer,
			 "orgin_country_name" text,
			 "destination_country_id" integer,
			 "destination_country_name" text,
			 "departure_time" timestamp,
			 "landing_time" timestamp,
			 "remaining_tickets" integer)
LANGUAGE plpgsql AS
$$
	BEGIN
		RETURN QUERY
		SELECT * FROM get_flights() AS "flights" 
		WHERE "flights"."orgin_country_id" = _origin_counry_id
		AND "flights"."destination_country_id" = _detination_country_id
		AND DATE("flights"."departure_time") = _date;
	END;
$$

CREATE OR REPLACE FUNCTION get_flights_by_airline_id(_airline_id bigint)
RETURNS TABLE("flight_id" bigint,
			 "airline_id" bigint,
			 "airline_name" text,
			 "country_id" smallint,
			 "country_name" text,
			 "user_id" bigint,
			 "username" text,
			 "password" text,
			 "email" text,
			 "role_id" integer,
			 "role_name" text,
			 "orgin_country_id" integer,
			 "orgin_country_name" text,
			 "destination_country_id" integer,
			 "destination_country_name" text,
			 "departure_time" timestamp,
			 "landing_time" timestamp,
			 "remaining_tickets" integer)
LANGUAGE plpgsql AS
$$
	BEGIN
		RETURN QUERY
		SELECT * FROM get_flights() AS "flights" 
		WHERE "flights"."airline_id" = _airline_id;
	END;
$$

CREATE OR REPLACE FUNCTION get_arrival_flights(_country_id int)
RETURNS TABLE("flight_id" bigint,
			 "airline_id" bigint,
			 "airline_name" text,
			 "country_id" smallint,
			 "country_name" text,
			 "user_id" bigint,
			 "username" text,
			 "password" text,
			 "email" text,
			 "role_id" integer,
			 "role_name" text,
			 "orgin_country_id" integer,
			 "orgin_country_name" text,
			 "destination_country_id" integer,
			 "destination_country_name" text,
			 "departure_time" timestamp,
			 "landing_time" timestamp,
			 "remaining_tickets" integer)
LANGUAGE plpgsql AS
$$
	DECLARE
		cur_time timestamp := CURRENT_TIMESTAMP;
	BEGIN
		RETURN QUERY
		SELECT * FROM get_flights() AS "flights" 
		WHERE "flights"."destination_country_id" = _country_id
		AND  ("flights"."landing_time" BETWEEN cur_time AND cur_time + interval '12 hours');
	END;
$$

CREATE OR REPLACE FUNCTION get_departure_flights(_country_id int)
RETURNS TABLE("flight_id" bigint,
			 "airline_id" bigint,
			 "airline_name" text,
			 "country_id" smallint,
			 "country_name" text,
			 "user_id" bigint,
			 "username" text,
			 "password" text,
			 "email" text,
			 "role_id" integer,
			 "role_name" text,
			 "orgin_country_id" integer,
			 "orgin_country_name" text,
			 "destination_country_id" integer,
			 "destination_country_name" text,
			 "departure_time" timestamp,
			 "landing_time" timestamp,
			 "remaining_tickets" integer)
LANGUAGE plpgsql AS
$$
	DECLARE
		cur_time timestamp := CURRENT_TIMESTAMP;
	BEGIN
		RETURN QUERY
		SELECT * FROM get_flights() AS "flights" 
		WHERE "flights"."orgin_country_id" = _country_id
		AND  ("flights"."landing_time" BETWEEN cur_time AND cur_time + interval '12 hours');
	END;
$$

CREATE FUNCTION get_tickets_by_customer(_customer_id bigint)
RETURNS TABLE(
  "ticket_id" bigint, 
	"flight_id" bigint, 
    "airline_id" bigint,
	"airline_name" text, 
    "country_id" smallint, 
	"country_name" text, 
  	"airline_user_id" bigint, 
	"airline_username" text, 
  	"airline_password" text, 
	"airline_email" text, 
  	"airline_role_id" integer, 
	"airline_role_name" text, 
  	"orgin_country_id" integer, 
	"orgin_country_name" text, 
  	"destination_country_id" integer, 
  	"destination_country_name" text, 
  	"departure_time" timestamp, 
	"landing_time" timestamp, 
    "remaining_tickets" integer, 
	"customer_id" bigint, 
    "first_name" text, 
	"last_name" text, 
    "address" text, "phone_no" text, 
	"credit_card_no" text, 
    "customer_user_id" bigint, 
	"customer_username" text, 
    "customer_password" text, 
	"customer_email" text, 
    "customer_role_id" integer, 
	"customer_role_name" text)
LANGUAGE plpgsql AS
$$ 
	BEGIN 
		RETURN QUERY 
		SELECT * FROM get_tickets() AS "tickets"
		WHERE "tickets"."customer_id" = _customer_id;
	END;
$$

CREATE PROCEDURE add_ticket(_flight_id bigint, _customer_id bigint)
LANGUAGE plpgsql AS
$$
	BEGIN
		-- reduce the remining tickes by 1
		UPDATE "Flights"
		SET "Remaining_Tickets" = "Remaining_Tickets" - 1
		WHERE "Id" = _flight_id;
		
		-- add new ticket
		INSERT INTO "Tickets" ("Flight_Id", "Customer_Id")
		VALUES (_flight_id, _customer_id);
	END;
$$

CREATE PROCEDURE remove_ticket(_flight_id bigint, _ticket_id bigint)
LANGUAGE plpgsql AS
$$
	BEGIN
		-- increse the remining tickes by 1
		UPDATE "Flights"
		SET "Remaining_Tickets" = "Remaining_Tickets" + 1
		WHERE "Id" = _flight_id;
		
		-- delte the ticket
		DELETE FROM "Tickets"
		WHERE "Id" = _ticket_id;
		
	END;
$$
