CREATE FUNCTION get_users()
RETURNS TABLE
("user_id" bigint,
 "username" text,
 "password" text,
 "email" text,
 "role_id" integer,
 "role_name" text)
LANGUAGE plpgsql AS
$$
	BEGIN
		RETURN QUERY
		SELECT "Users"."Id","Users"."Username","Users"."Password",
		       "Users"."Email", "Users"."User_Role", "User_Roles"."Role_Name"
		FROM "Users"
		JOIN "User_Roles" ON "Users"."Id"="User_Roles"."Id";
	END;
$$

CREATE OR REPLACE FUNCTION get_airline_companies()
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
		SELECT "airlines"."Id", "airlines"."Name","airlines"."Country_Id","Countries"."Name" AS "country_name",
		"airlines"."User_Id","airlines"."username", "airlines"."password", "airlines"."email", "airlines"."role_id", "airlines"."role_name"
		FROM (
			SELECT "Airline_Companies"."Id", "Airline_Companies"."Name", "Airline_Companies"."Country_Id", "Airline_Companies"."User_Id",
			"users"."username","users"."password", "users"."email","users"."role_id","users"."role_name"
			FROM "Airline_Companies"
			JOIN (SELECT * FROM get_users()) AS "users" ON "User_Id" = "users"."user_id") AS "airlines"
			JOIN "Countries" ON "Countries"."Id"="airlines"."Country_Id";
	END;
$$

CREATE OR REPLACE FUNCTION get_customers()
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
		SELECT "Customers".*, "users"."username", "users"."password", "users"."email", "users"."role_id","users"."role_name"
		FROM "Customers"
		JOIN (SELECT * FROM get_users()) AS "users" ON "Customers"."User_Id" = "users"."user_id";
	END;
$$

CREATE FUNCTION get_administrators()
RETURNS TABLE("admin_id" integer,
			 "first_name" text,
			 "last_name" text,
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
		SELECT "Administrators".*, "users"."username","users"."password","users"."email","users"."role_id","users"."role_name"
 		FROM "Administrators"
		JOIN (SELECT * FROM get_users()) AS "users" ON "users"."user_id"= "Administrators"."User_Id";
	END;
$$

CREATE FUNCTION get_flights() RETURNS TABLE(
  "flight_id" bigint, "airline_id" bigint, 
  "airline_name" text, "country_id" smallint, 
  "country_name" text, "user_id" bigint, 
  "username" text, "password" text, 
  "email" text, "role_id" integer, "role_name" text, 
  "orgin_country_id" integer, "orgin_country_name" text, 
  "destination_country_id" integer, 
  "destination_country_name" text, 
  "departure_time" timestamp, "landing_time" timestamp, 
  "remaining_tickets" integer
) LANGUAGE plpgsql AS $$ BEGIN RETURN QUERY 
SELECT 
  "flights_orgin_dest"."Id", 
  "airlines".*, 
  "flights_orgin_dest"."Orgin_Country_Id", 
  "flights_orgin_dest"."orgin_country_name", 
  "flights_orgin_dest"."Destination_Country_Id", 
  "flights_orgin_dest"."destination_country_name", 
  "flights_orgin_dest"."Depature_Time", 
  "flights_orgin_dest"."Landing_Time", 
  "flights_orgin_dest"."Remaining_Tickets" 
FROM 
  (
    SELECT 
      "flights_orgin_country"."Id", 
      "flights_orgin_country"."Airline_Company_Id", 
      "flights_orgin_country"."Orgin_Country_Id", 
      "flights_orgin_country"."orgin_country_name", 
      "flights_orgin_country"."Destination_Country_Id", 
      "Countries"."Name" AS "destination_country_name", 
      "flights_orgin_country"."Depature_Time", 
      "flights_orgin_country"."Landing_Time", 
      "flights_orgin_country"."Remaining_Tickets" 
    FROM 
      (
        SELECT 
          "Flights"."Id", 
          "Flights"."Airline_Company_Id", 
          "Flights"."Orgin_Country_Id", 
          "Countries"."Name" AS "orgin_country_name", 
          "Flights"."Destination_Country_Id", 
          "Flights"."Depature_Time", 
          "Flights"."Landing_Time", 
          "Flights"."Remaining_Tickets" 
        FROM 
          "Flights" 
          JOIN "Countries" ON "Flights"."Orgin_Country_Id" = "Countries"."Id"
      ) AS "flights_orgin_country" 
      JOIN "Countries" ON "Countries"."Id" = "flights_orgin_country"."Destination_Country_Id"
  ) AS "flights_orgin_dest" 
  JOIN (
    SELECT 
      * 
    FROM 
      get_airline_companies()
  ) AS "airlines" ON "flights_orgin_dest"."Id" = "airlines"."airline_id";
END;
$$

CREATE FUNCTION get_tickets() RETURNS TABLE(
  "ticket_id" bigint, "flight_id" bigint, 
  "airline_id" bigint, "airline_name" text, 
  "country_id" smallint, "country_name" text, 
  "airline_user_id" bigint, "airline_username" text, 
  "airline_password" text, "airline_email" text, 
  "airline_role_id" integer, "airline_role_name" text, 
  "orgin_country_id" integer, "orgin_country_name" text, 
  "destination_country_id" integer, 
  "destination_country_name" text, 
  "departure_time" timestamp, "landing_time" timestamp, 
  "remaining_tickets" integer, "customer_id" bigint, 
  "first_name" text, "last_name" text, 
  "address" text, "phone_no" text, "credit_card_no" text, 
  "customer_user_id" bigint, "customer_username" text, 
  "customer_password" text, "customer_email" text, 
  "customer_role_id" integer, "customer_role_name" text
) LANGUAGE plpgsql AS $$ BEGIN RETURN QUERY 
SELECT 
  "tickets_flights".*, 
  "customers"."first_name", 
  "customers"."last_name", 
  "customers"."address", 
  "customers"."phone_no", 
  "customers"."credit_card_no", 
  "customers"."user_id", 
  "customers"."username", 
  "customers"."password", 
  "customers"."email", 
  "customers"."role_id", 
  "customers"."role_name" 
FROM 
  (
    SELECT 
      "Tickets"."Id", 
      "flights".*, 
      "Tickets"."Customer_Id" 
    FROM 
      "Tickets" 
      JOIN (
        SELECT 
          * 
        FROM 
          get_flights()
      ) AS "flights" ON "flights"."flight_id" = "Tickets"."Flight_Id"
  ) AS "tickets_flights" 
  JOIN (
    SELECT 
      * 
    FROM 
      get_customers()
  ) AS "customers" ON "tickets_flights"."Customer_Id" = "customers"."customer_id";
END;
$$
