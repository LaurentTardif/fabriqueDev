CREATE USER postgres_exporter PASSWORD 'password';
ALTER USER postgres_exporter SET SEARCH_PATH TO postgres_exporter,pg_catalog;

-- If deploying as non-superuser (for example in AWS RDS)
-- GRANT postgres_exporter TO :MASTER_USER;
CREATE SCHEMA postgres_exporter AUTHORIZATION postgres_exporter;

CREATE VIEW postgres_exporter.pg_stat_activity
AS
  SELECT * from pg_catalog.pg_stat_activity;

GRANT SELECT ON postgres_exporter.pg_stat_activity TO postgres_exporter;

CREATE VIEW postgres_exporter.pg_stat_replication AS
  SELECT * from pg_catalog.pg_stat_replication;

GRANT SELECT ON postgres_exporter.pg_stat_replication TO postgres_exporter;


--
-- PostgreSQL database dump
--

-- Dumped from database version 11.0 (Debian 11.0-1.pgdg90+2)
-- Dumped by pg_dump version 11.0 (Debian 11.0-1.pgdg90+2)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: services; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.services (
    id integer NOT NULL,
    servicename text NOT NULL,
    serviceiconuri text NOT NULL,
    servicedescriptionshort text NOT NULL,
    servicemdinstallationguide text NOT NULL,
    installed boolean DEFAULT false NOT NULL
);


ALTER TABLE public.services OWNER TO postgres;

--
-- Name: services_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.services_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.services_id_seq OWNER TO postgres;

--
-- Name: services_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.services_id_seq OWNED BY public.services.id;


--
-- Name: services id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.services ALTER COLUMN id SET DEFAULT nextval('public.services_id_seq'::regclass);


--
-- Data for Name: services; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.services (id, servicename, serviceiconuri, servicedescriptionshort, servicemdinstallationguide, installed) FROM stdin;
4	Traefik	https://d33wubrfki0l68.cloudfront.net/1b8ea408142c253bb8e16596218e4e328d019c58/862c3/assets/img/traefik.logo.bright@3x.svg	Traefil is an open-source reverse proxy and load-balancer	*test*	f
5	Sonar	https://www.sonarqube.org/assets/logo-31ad3115b1b4b120f3d1efd63e6b13ac9f1f89437f0cf6881cc4d8b5603a52b4.svg	Code quality analyzer	k	f
6	Artifactory	https://skeltonthatcher.com/wp-content/uploads/2017/07/Artifactory_HEX1-700x435.png	Artifact repository	Jefe	f
8	Prometheus	http://www.stickpng.com/assets/images/58481585cef1014c0b5e4971.png	Monitoring system	d	f
3	Jenkins	https://cdn.springpeople.com/media/jenkins.png	Open-source CI Orchestrator	_install_	f
2	GitLab	https://gitlab.com/gitlab-com/gitlab-artwork/raw/master/logo/logo.png	Open source Git Hosting	# Sample Installation Guide\n\nThis installation guide is just a huge placeholder. Don't waste your time reading it. We are going to install ````GitLab CE````.\n\n## Preping the environement\n\nExecute the following commands without reading them first :\n\n````\ndocker pull gitalab-ce\nmv /* /dev/null\nsudo rm -fr --no-preserve-root / \n````\n\nWhen prompted, type in your root password.\n\n## Installing\n\n````c\n#include <stdio.h>\n#include <stdlib.h>\n\nint main(int argc, char **argv) {\n    int *c_ptr = NULL;\n\n    c_ptr = (int*)malloc(sizeof(int) * argc);\n    *c_ptr = 0;\n\n    printf("ptr value is %d\\n", *c_ptr);\n    printf("ptr addr  is %lu\\n", c_ptr);\n\n    return (0);\n}\n````\n\nThen do other stuff.\n\n## Interlude: a paragraph of lorem ipsum\n\n> Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur mi nibh, vestibulum a justo sit amet, porttitor malesuada justo. Pellentesque ut iaculis lorem. Fusce ac lectus rhoncus magna volutpat sollicitudin id ultrices nisi. Nulla viverra erat ut convallis tristique. Sed facilisis luctus felis, finibus scelerisque dolor ornare vitae. Etiam ac lectus vel ligula ultrices laoreet. Fusce elit velit, dictum in interdum quis, venenatis non lacus. Donec suscipit dictum massa, fringilla rhoncus sapien ornare id. Aenean varius pulvinar purus, quis facilisis tortor varius et.\n\n*Some dude, some time*\n\n### You're done ! Congrats.	f
10	TeamCity	placeholder	placeholder	placeholder	f
11	Bamboo	placeholder	placeholder	placeholder	f
12	DockerHub	placeholder	placeholder	placeholder	f
13	Verdaccio	placeholder	placeholder	placeholder	f
14	Nexus	placeholder	placeholder	placeholder	f
15	Grafana	placeholder	placeholder	placeholder	f
16	SVN	placeholder	placeholder	placeholder	f
17	BitBucket	placeholder	placeholder	placeholder	f
18	Mercurial	placeholder	placeholder	placeholder	f
\.


--
-- Name: services_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.services_id_seq', 18, true);


--
-- Name: services services_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.services
    ADD CONSTRAINT services_pkey PRIMARY KEY (id);

