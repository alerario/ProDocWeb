CREATE TABLE public.swuser
(
    id SERIAL,
    name character varying(60) COLLATE pg_catalog."default" NOT NULL,
    email character varying(100) COLLATE pg_catalog."default" NOT NULL,
    password character varying(33) COLLATE pg_catalog."default" NOT NULL,
    created  timestamp without time zone NOT NULL DEFAULT CURRENT_TIMESTAMP,
    photo bytea,
    enable boolean NOT NULL DEFAULT false,
    CONSTRAINT user_pk PRIMARY KEY (id),
    CONSTRAINT user_email_key UNIQUE (email)
);

CREATE TABLE public.sworganization
(
    id SERIAL,
    admin integer NOT NULL,
    name character varying(80) COLLATE pg_catalog."default" NOT NULL,
    description character varying(500) COLLATE pg_catalog."default",
    created timestamp without time zone NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT organization_pk PRIMARY KEY (id),
    CONSTRAINT organization_fk0 FOREIGN KEY (admin)
        REFERENCES public.swuser (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE TABLE public.swprocess (
	id SERIAL NOT NULL,
	name character varying(50),
	description character varying(200),
	images bytea,
	modified DATE,
	organization integer NOT NULL,
	created timestamp without time zone NOT NULL DEFAULT CURRENT_TIMESTAMP,
	update TIMESTAMP NOT NULL,
	CONSTRAINT swprocess_pk PRIMARY KEY (id)
) WITH (
  OIDS=FALSE
);
