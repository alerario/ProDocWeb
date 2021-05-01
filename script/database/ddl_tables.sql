DROP SCHEMA public CASCADE;
CREATE SCHEMA public;
     
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO public;

CREATE TABLE public.swuser
(
    id SERIAL,
    name character varying(60) COLLATE pg_catalog."default" NOT NULL,
    email character varying(100) COLLATE pg_catalog."default" NOT NULL,
    password character varying(33) COLLATE pg_catalog."default" NOT NULL,
    created  timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    photo bytea,
    enable boolean NOT NULL DEFAULT false,
	obs character varying(200),
	city character varying(60), 
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

CREATE TABLE public.swprocess
(
    id serial NOT NULL,
    name character varying(50) COLLATE pg_catalog."default",
    description character varying(200) COLLATE pg_catalog."default",
    modified date,
    organization integer NOT NULL,
    created timestamp without time zone NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated timestamp without time zone NOT NULL,
    createdby integer NOT NULL,
    CONSTRAINT swprocess_pk PRIMARY KEY (id),
    CONSTRAINT fg_created FOREIGN KEY (createdby)
        REFERENCES public.swuser (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT fg_orgp FOREIGN KEY (organization)
        REFERENCES public.sworganization (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
);

CREATE TABLE public.swphase (
	id serial PRIMARY KEY NOT NULL,
	name character varying(50),
	description character varying(400),
	swprocess integer,
	execorder integer NOT NULL UNIQUE,
    CONSTRAINT swphase_fk0 FOREIGN KEY (swprocess)
           REFERENCES public.swprocess (id) MATCH SIMPLE
           ON UPDATE NO ACTION
           ON DELETE NO ACTION
	);

create table public.swguidance(id serial primary key,
				        name varchar(50),
				        description varchar (400),
				        guidefile bytea
);

CREATE TABLE public.swrole (
    id serial NOT NULL,
    name character varying(100) NOT NULL,
    description character varying(100) NOT NULL,
    swprocess integer NOT NULL,
    CONSTRAINT swrole_pk PRIMARY KEY (id),
    CONSTRAINT swrole_fk0 FOREIGN KEY (swprocess) REFERENCES public.swprocess(id)
);


CREATE TABLE public.swactivity (
	id serial NOT NULL,
	name character varying(50),
	description character varying(400),
	swphase integer,
	mandatory BOOLEAN NOT NULL DEFAULT false,
	swguidance integer,
	swrole integer,
	execorder integer NOT NULL UNIQUE,
	start bool NOT NULL,
	CONSTRAINT swactivity_pk PRIMARY KEY (id),
    CONSTRAINT swactivity_fk0 FOREIGN KEY (swphase)
           REFERENCES public.swphase (id) MATCH SIMPLE
           ON UPDATE NO ACTION
           ON DELETE NO ACTION,
       CONSTRAINT swactivity_fk1 FOREIGN KEY (swguidance)
           REFERENCES public.swguidance (id) MATCH SIMPLE
           ON UPDATE NO ACTION
           ON DELETE NO ACTION,
       CONSTRAINT swactivity_fk2 FOREIGN KEY (swrole)
           REFERENCES public.swrole (id) MATCH SIMPLE
           ON UPDATE NO ACTION
           ON DELETE NO ACTION
);

CREATE TABLE public.swactivflow
(
    swactivity integer NOT NULL,
    nextactivity integer NOT NULL,
    CONSTRAINT swactivflow_pk PRIMARY KEY (swactivity, nextactivity),
    CONSTRAINT swactivflow_fk0 FOREIGN KEY (swactivity)
        REFERENCES public.swactivity (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT swactivflow_fk1 FOREIGN KEY (nextactivity)
        REFERENCES public.swactivity (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE TABLE public.swgateway( 
        id serial NOT NULL,
        annotation varchar(40),
        type character,
        CONSTRAINT swgateway_pk PRIMARY KEY (id) 
);

CREATE TABLE public.swimages (
	id serial NOT NULL,
	swprocess integer NOT NULL,
	image bytea NOT NULL,
	name character varying(40) NOT NULL,
	description character varying(200) NOT NULL,
	CONSTRAINT swimages_pk PRIMARY KEY (id)
);


CREATE TABLE public.swtemplate
(
    id serial NOT NULL,
    name character varying(50) COLLATE pg_catalog."default",
    description character varying(400) COLLATE pg_catalog."default",
    templatefile bytea,
    CONSTRAINT swtemplate_pk PRIMARY KEY (id)
);

CREATE TABLE public.swarctifact
(
    id serial NOT NULL,
    name character varying(50) COLLATE pg_catalog."default",
    description character varying(400) COLLATE pg_catalog."default",
    swguidance integer,
    swtemplate integer,
    CONSTRAINT swarctifact_pk PRIMARY KEY (id),
    CONSTRAINT swarctifact_fk0 FOREIGN KEY (swguidance)
        REFERENCES public.swguidance (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT swarctifact_fk1 FOREIGN KEY (swtemplate)
        REFERENCES public.swtemplate (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE TABLE public.swactivityartifact
(
    swactivity integer NOT NULL,
    swartifact integer NOT NULL,
    input boolean NOT NULL DEFAULT true,
    CONSTRAINT swactivityartifact_pk PRIMARY KEY (swactivity, swartifact, input),
    CONSTRAINT swactivityartifact_fk0 FOREIGN KEY (swactivity)
        REFERENCES public.swactivity (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT swactivityartifact_fk1 FOREIGN KEY (swartifact)
        REFERENCES public.swarctifact (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);


CREATE TABLE public.swuserprocess
(
    swuser integer NOT NULL,
    swprocess integer NOT NULL,
    write boolean NOT NULL DEFAULT true,
    CONSTRAINT userswprocess_pk PRIMARY KEY (swuser, swprocess, write),
    CONSTRAINT userswprocess_fk0 FOREIGN KEY (swuser)
        REFERENCES public.swuser (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT userswprocess_fk1 FOREIGN KEY (swprocess)
        REFERENCES public.swprocess (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE TABLE public.swuserorganization
(
    swuser integer NOT NULL,
    organization integer NOT NULL,
    CONSTRAINT userorganization_pk PRIMARY KEY (swuser, organization),
    CONSTRAINT fg2 FOREIGN KEY (organization)
        REFERENCES public.sworganization (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT userorganization_fk0 FOREIGN KEY (swuser)
        REFERENCES public.swuser (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE TABLE public.swsequence
(
    sourceactivity integer,
    targetactivity integer,
    annotation character varying(40),
    id serial NOT NULL,
    targetgateway integer,
    sourcegateway integer,
    CONSTRAINT swsequence_pk PRIMARY KEY (id),
    CONSTRAINT swsequence_fk0 FOREIGN KEY (sourceactivity)
        REFERENCES public.swactivity (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT swsequence_fk1 FOREIGN KEY (targetactivity)
        REFERENCES public.swactivity (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT swsequence_fk2 FOREIGN KEY (targetgateway)
        REFERENCES public.swgateway (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT swsequence_fk3 FOREIGN KEY (sourcegateway)
        REFERENCES public.swgateway (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

