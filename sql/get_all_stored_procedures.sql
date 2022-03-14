CREATE TABLE "Countries"(
	"Id" serial2 NOT NULL,
	"Name" text NOT NULL,
	PRIMARY KEY("Id"),
	UNIQUE("Name")
);

CREATE TABLE "User_Roles"(
	"Id" serial2 NOT NULL,
	"Role_Name" text NOT NULL,
	PRIMARY KEY("Id")
); 

CREATE TABLE "Users"(
	"Id" bigserial NOT NULL,
	"Username" text NOT NULL,
	"Password" text NOT NULL,
	"Email" text NOT NULL,
	"User_Role" int NOT NULL,
	PRIMARY KEY("Id"),
	FOREIGN KEY("User_Role") REFERENCES "User_Roles"("Id"),
	UNIQUE("Username"), UNIQUE("Email")
);

CREATE TABLE "Administrators"(
	"Id" serial NOT NULL,
	"First_Name" text NOT NULL,
	"Last_Name" text NOT NULL,
	"User_Id" bigint NOT NULL,
	PRIMARY KEY("Id"),
	UNIQUE("User_Id"),
	FOREIGN KEY("User_Id") REFERENCES "Users"("Id")
);

CREATE TABLE "Airline_Companies"(
	"Id" bigserial NOT NULL,
	"Name" text NOT NULL,
	"Country_Id" smallint NOT NULL,
	"User_Id" bigint NOT NULL,
	PRIMARY KEY("Id"),
	UNIQUE("Name"), UNIQUE("User_Id"),
	FOREIGN KEY("Country_Id") REFERENCES "Countries"("Id"),
	FOREIGN KEY("User_Id") REFERENCES "Users"("Id")
);

CREATE TABLE "Customers"(
	"Id" bigserial NOT NULL,
	"First_Name" text NOT NULL,
	"Last_Name" text NOT NULL,
	"Address" text NOT NULL,
	"Phone_No" text NOT NULL,
	"Credit_Card_No" text NOT NULL,
	"User_Id" bigint NOT NULL,
	PRIMARY KEY("Id"),
	UNIQUE("Phone_No"), UNIQUE("Credit_Card_No"), UNIQUE("User_Id"),
	FOREIGN KEY("User_Id") REFERENCES "Users"("Id")
);

CREATE TABLE "Tickets"(
	"Id" bigserial NOT NULL,
	"Flight_Id" bigint NOT NULL,
	"Customer_Id" bigint NOT NUll,
	PRIMARY KEY("Id"),
	FOREIGN KEY("Flight_Id") REFERENCES "Flights"("Id"),
	FOREIGN KEY("Customer_Id") REFERENCES "Customers"("Id"),
	UNIQUE("Flight_Id","Customer_Id")
);



CREATE TABLE "Flights"(
	"Id" bigserial NOT NULL,
	"Airline_Company_Id" bigint NOT NULL,
	"Orgin_Country_Id" int NOT NULL,
	"Destination_Country_Id" int NOT NULL,
	"Depature_Time" timestamp NOT NULL,
	"Landing_Time" timestamp NOT NULL,
	"Remaining_Tickets" int NOT NULL,
	PRIMARY KEY("Id"),
	FOREIGN KEY("Airline_Company_Id") REFERENCES "Airline_Companies"("Id"),
	FOREIGN KEY("Orgin_Country_Id") REFERENCES "Countries"("Id"),
	FOREIGN KEY("Destination_Country_Id") REFERENCES "Countries"("Id")
);