-- public.espacio definition

-- Drop table

-- DROP TABLE public.espacio;

CREATE TABLE public.espacio (
	geom public.geometry(multipolygon, 4326) NULL,
	id varchar NOT NULL,
	"Link" varchar NULL,
	"ID_EDIF" varchar NULL,
	"Centro" varchar NULL,
	"SUPERFICIE" float8 NULL,
	"Nombre" varchar NULL,
	"ID_UXXI" int4 NULL,
	"FOTO_360" varchar NULL,
	"FOTO_PLANA" varchar NULL,
	"USO" varchar NULL,
	"EDIFICIO" varchar NULL,
	"Altura" varchar NULL,
	"Dummy" varchar NULL,
	"Shape__Area" float8 NULL,
	"Shape__Length" float8 NULL,
	categoria_reserva varchar(255) NULL,
	hora_apertura int4 NULL,
	hora_cierre int4 NULL,
	tipo_espacio varchar(255) NULL,
	num_max_ocupantes int4 NULL,
	planta int4 NULL,
	porcentaje_uso_maximo int4 NULL,
	reservable bool NULL,
	tamano float4 NULL,
	departamento varchar(255) NULL,
	tipo_entidad varchar(255) NULL,
	CONSTRAINT espacio_categoria_reserva_check CHECK (((categoria_reserva)::text = ANY (ARRAY[('AULA'::character varying)::text, ('SEMINARIO'::character varying)::text, ('LABORATORIO'::character varying)::text, ('DESPACHO'::character varying)::text, ('SALA_COMUN'::character varying)::text]))),
	CONSTRAINT espacio_departamento_check CHECK (((departamento)::text = ANY ((ARRAY['Informatica_e_Ingenieria_de_sistemas'::character varying, 'Ingenieria_electronica_y_comunicaciones'::character varying])::text[]))),
	CONSTRAINT espacio_pk PRIMARY KEY (id),
	CONSTRAINT espacio_tipo_entidad_check CHECK (((tipo_entidad)::text = ANY ((ARRAY['EINA'::character varying, 'DEPARTAMENTO'::character varying, 'PERSONAS'::character varying])::text[]))),
	CONSTRAINT espacio_tipo_espacio_check CHECK (((tipo_espacio)::text = ANY (ARRAY[('AULA'::character varying)::text, ('SEMINARIO'::character varying)::text, ('LABORATORIO'::character varying)::text, ('DESPACHO'::character varying)::text, ('SALA_COMUN'::character varying)::text])))
);
