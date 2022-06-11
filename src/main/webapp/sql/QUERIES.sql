--! PREGUNTA

--? SELECT ALL
SELECT * FROM pregunta ORDER BY id;
--? SELECT BY CONTENIDO
SELECT * FROM pregunta WHERE contenido LIKE (LOWER('comida')) ORDER BY id;
--? SELECT BY ID
SELECT * FROM pregunta WHERE id = 1;
--? INSERT
INSERT INTO pregunta (contenido) VALUES (LOWER('Es de metal?'));
--? UPDATE 
UPDATE pregunta SET contenido = LOWER('Se alimenta de peces?') WHERE id = 20;
--? DELETE
DELETE FROM pregunta WHERE id = 21;

--! CONCEPTO

--? SELECT ALL
SELECT * FROM concepto ORDER BY id;
--? SELECT BY CONTENIDO
SELECT * FROM concepto WHERE contenido LIKE (LOWER('Limon')) ORDER BY id;
--? SELECT BY ID
SELECT * FROM concepto WHERE id = 1; 
--? INSERT
INSERT INTO concepto (contenido) VALUES (LOWER('Leon'));
--? UPDATE 
UPDATE concepto SET contenido = LOWER('Pez León') WHERE id = 20;
--? DELETE
DELETE FROM concepto WHERE id = 21;

--! RESPUESTA
--? SELECT ALL
SELECT c.contenido, p.contenido, r.contenido
FROM respuesta r
JOIN pregunta p
ON (r.id_pregunta = p.id)
JOIN concepto c
ON (r.id_concepto = c.id)
ORDER BY (C.contenido);
--? SELECT BY CONTENIDO
SELECT * FROM respuesta WHERE id_concepto = 1 AND id_pregunta = 3 AND contenido = TRUE;
--? SELECT BY ID
SELECT * FROM respuesta WHERE id_concepto = 1 AND id_pregunta = 3;
--? INSERT
INSERT INTO respuesta VALUES (1,1,true);
--? UPDATE
UPDATE respuesta SET contenido = false WHERE id_concepto = 1 AND id_pregunta = 2;
--? DELETE
DELETE FROM respuesta WHERE id_concepto = 1 AND id_pregunta = 2 AND contenido = true;

SELECT c.id, p.id, c.nombre, p.contenido, r.contenido
FROM respuesta r
JOIN pregunta p
ON (r.id_pregunta = p.id)
JOIN concepto c
ON (r.id_concepto = c.id)
ORDER BY RANDOM();

SELECT c.id, c.nombre, p.id, p.contenido, r.contenido
FROM respuesta r
JOIN pregunta p
ON (r.id_pregunta = p.id)
JOIN concepto c
ON (r.id_concepto = c.id)
WHERE c.id = 5
ORDER BY p.id;
