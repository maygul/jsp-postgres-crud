CREATE TABLE public.users (
	id bigserial NOT NULL,
	country varchar(255) NOT NULL,
	email varchar(255) NOT NULL,
	"name" varchar(255) NOT NULL,
	CONSTRAINT users_pkey PRIMARY KEY (id)
);