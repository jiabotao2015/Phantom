CREATE EXTENSION postgis;
CREATE EXTENSION fuzzystrmatch;
CREATE EXTENSION address_standardizer;
CREATE EXTENSION pointcloud;
CREATE EXTENSION pointcloud_postgis;
CREATE EXTENSION ogr_fdw;
CREATE EXTENSION pgrouting;
CREATE EXTENSION postgis_tiger_geocoder;
CREATE EXTENSION postgis_sfcgal;
CREATE EXTENSION postgis_topology;

CREATE TABLE public.tb_point
(
    point_id SERIAL PRIMARY KEY,
    point_name character varying(20),
    point_type integer,
    the_geom GEOGRAPHY(MULTILINESTRING,4326)
)
WITH (
    OIDS = FALSE
);
ALTER TABLE public.tb_point OWNER to postgres;

CREATE TABLE public.tb_road
(
    road_id SERIAL PRIMARY KEY,
    road_name character varying(20),
    road_type integer,
    the_geom GEOGRAPHY(MULTILINESTRING,4326)
)
WITH (
    OIDS = FALSE
);
ALTER TABLE public.tb_road OWNER to postgres;

CREATE TABLE public.tb_area
(
    area_id SERIAL PRIMARY KEY,
    area_name character varying(20),
    area_type integer,
    the_geom GEOGRAPHY(POLYGON,4326)
)
WITH (
    OIDS = FALSE
);
ALTER TABLE public.tb_road OWNER to postgres;

-- 添加几何字段
SELECT AddGeometryColumn('public','tb_point','the_geom',4326,'POINT',2);
