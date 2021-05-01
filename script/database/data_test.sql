INSERT INTO public.swuser(
	id, name, email, password, created, photo, enable, obs, city)
	VALUES (DEFAULT, 'Alexandre', 'a@a.com', '0CC175B9C0F1B6A831C399E269772661', now(), NULL, TRUE, NULL, NULL);

	INSERT INTO public.sworganization(
	id, admin, name, description, created)
	VALUES (DEFAULT, currval('swuser_id_seq'), 'UTFPR - LabInov', 'Laboratorio de Inovacao', now());

  INSERT INTO public.swprocess(
	id, name, description, modified, organization, created, updated, createdby)
	VALUES (DEFAULT, 'Evolucao agil', 'Processo de software focado em evolucao do produto', now(),  currval('sworganization_id_seq'), now(), now(),  currval('swuser_id_seq'));
