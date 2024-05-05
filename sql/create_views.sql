-- public.planta0 source

CREATE OR REPLACE VIEW public.planta0
AS SELECT geom,
    id,
    "Link",
    "ID_EDIF",
    "Centro",
    "SUPERFICIE",
    "Nombre",
    "ID_UXXI",
    "FOTO_360",
    "FOTO_PLANA",
    "USO",
    "EDIFICIO",
    "Altura",
    "Dummy",
    "Shape__Area",
    "Shape__Length",
    categoria_reserva,
    hora_apertura,
    hora_cierre,
    tipo_espacio,
    num_max_ocupantes,
    planta,
    porcentaje_uso_maximo,
    reservable,
    tamano,
    tipo_entidad,
    departamento,
    ( SELECT ARRAY( SELECT ep.personas_email
                   FROM espacio_personas ep
                  WHERE e.id::text = ep.espacio_id::text) AS personas) AS personas
   FROM espacio e
  WHERE planta = 0;


-- public.planta1 source

CREATE OR REPLACE VIEW public.planta1
AS SELECT geom,
    id,
    "Link",
    "ID_EDIF",
    "Centro",
    "SUPERFICIE",
    "Nombre",
    "ID_UXXI",
    "FOTO_360",
    "FOTO_PLANA",
    "USO",
    "EDIFICIO",
    "Altura",
    "Dummy",
    "Shape__Area",
    "Shape__Length",
    categoria_reserva,
    hora_apertura,
    hora_cierre,
    tipo_espacio,
    num_max_ocupantes,
    planta,
    porcentaje_uso_maximo,
    reservable,
    tamano,
    tipo_entidad,
    departamento,
    ( SELECT ARRAY( SELECT ep.personas_email
                   FROM espacio_personas ep
                  WHERE e.id::text = ep.espacio_id::text) AS personas) AS personas
   FROM espacio e
  WHERE planta = 1;


-- public.planta2 source

CREATE OR REPLACE VIEW public.planta2
AS SELECT geom,
    id,
    "Link",
    "ID_EDIF",
    "Centro",
    "SUPERFICIE",
    "Nombre",
    "ID_UXXI",
    "FOTO_360",
    "FOTO_PLANA",
    "USO",
    "EDIFICIO",
    "Altura",
    "Dummy",
    "Shape__Area",
    "Shape__Length",
    categoria_reserva,
    hora_apertura,
    hora_cierre,
    tipo_espacio,
    num_max_ocupantes,
    planta,
    porcentaje_uso_maximo,
    reservable,
    tamano,
    tipo_entidad,
    departamento,
    ( SELECT ARRAY( SELECT ep.personas_email
                   FROM espacio_personas ep
                  WHERE e.id::text = ep.espacio_id::text) AS personas) AS personas
   FROM espacio e
  WHERE planta = 2;


-- public.planta3 source

CREATE OR REPLACE VIEW public.planta3
AS SELECT geom,
    id,
    "Link",
    "ID_EDIF",
    "Centro",
    "SUPERFICIE",
    "Nombre",
    "ID_UXXI",
    "FOTO_360",
    "FOTO_PLANA",
    "USO",
    "EDIFICIO",
    "Altura",
    "Dummy",
    "Shape__Area",
    "Shape__Length",
    categoria_reserva,
    hora_apertura,
    hora_cierre,
    tipo_espacio,
    num_max_ocupantes,
    planta,
    porcentaje_uso_maximo,
    reservable,
    tamano,
    tipo_entidad,
    departamento,
    ( SELECT ARRAY( SELECT ep.personas_email
                   FROM espacio_personas ep
                  WHERE e.id::text = ep.espacio_id::text) AS personas) AS personas
   FROM espacio e
  WHERE planta = 3;


-- public.planta4 source

CREATE OR REPLACE VIEW public.planta4
AS SELECT geom,
    id,
    "Link",
    "ID_EDIF",
    "Centro",
    "SUPERFICIE",
    "Nombre",
    "ID_UXXI",
    "FOTO_360",
    "FOTO_PLANA",
    "USO",
    "EDIFICIO",
    "Altura",
    "Dummy",
    "Shape__Area",
    "Shape__Length",
    categoria_reserva,
    hora_apertura,
    hora_cierre,
    tipo_espacio,
    num_max_ocupantes,
    planta,
    porcentaje_uso_maximo,
    reservable,
    tamano,
    tipo_entidad,
    departamento,
    ( SELECT ARRAY( SELECT ep.personas_email
                   FROM espacio_personas ep
                  WHERE e.id::text = ep.espacio_id::text) AS personas) AS personas
   FROM espacio e
  WHERE planta = 4;
