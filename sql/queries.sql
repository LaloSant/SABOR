SELECT * FROM USUARIOS;

SELECT * FROM INGREDIENTES;

SELECT * FROM DETALLES;


-------------------------------------------------------------------------------
-- INSERTS
-------------------------------------------------------------------------------


-- ROL
INSERT INTO ROL (
	ID_ROL
	,NOMBRE
	,DESCRIPCION
) VALUES ( 'ADMIN'
          ,'Admin'
          ,'Administrador' );
INSERT INTO ROL (
	ID_ROL
	,NOMBRE
	,DESCRIPCION
) VALUES ( 'MOD'
          ,'Moderador'
          ,'Mod. peticiones IA' );
INSERT INTO ROL (
	ID_ROL
	,NOMBRE
	,DESCRIPCION
) VALUES ( 'USER'
          ,'Usuario'
          ,'Usuario regular' );
COMMIT;

INSERT INTO USUARIOS (ID_ROL, NOMBRE, EMAIL, CONTRASENIA) VALUES(
    'USER',
    'yael',
    'yael@mail.com',
    '$2a$10$ZvRsNwfnOsqWt2L5B5ddk.AEo6CPYNCD.oVjLl3B3aSY8Jd9els0i'
);

INSERT INTO USUARIOS (ID_ROL, NOMBRE, EMAIL, CONTRASENIA) VALUES(
    'ADMIN',
    'eduardo',
    'eduardo@mail.com',
    '$2a$10$bTFF6XbbAbwG6r6o376/jeYjbaefS8mPFREsQkO5aLF1W.kFX9ebW'
);

commit;

-- INGREDIENTES
INSERT INTO INGREDIENTES (
	NOMBRE
	,COSTO_UNITARIO
	,MAGNITUD
	,IMAGEN
) VALUES ( 'Pechuga de Pollo'
          ,8.50
          ,'kg'
          ,NULL );
INSERT INTO INGREDIENTES (
	NOMBRE
	,COSTO_UNITARIO
	,MAGNITUD
	,IMAGEN
) VALUES ( 'Tomate Saladet'
          ,2.50
          ,'kg'
          ,NULL );
INSERT INTO INGREDIENTES (
	NOMBRE
	,COSTO_UNITARIO
	,MAGNITUD
	,IMAGEN
) VALUES ( 'Cebolla Blanca'
          ,1.80
          ,'kg'
          ,NULL );
INSERT INTO INGREDIENTES (
	NOMBRE
	,COSTO_UNITARIO
	,MAGNITUD
	,IMAGEN
) VALUES ( 'Arroz Blanco'
          ,3.20
          ,'kg'
          ,NULL );
INSERT INTO INGREDIENTES (
	NOMBRE
	,COSTO_UNITARIO
	,MAGNITUD
	,IMAGEN
) VALUES ( 'Aceite de Oliva'
          ,12.00
          ,'litro'
          ,NULL );
INSERT INTO INGREDIENTES (
	NOMBRE
	,COSTO_UNITARIO
	,MAGNITUD
	,IMAGEN
) VALUES ( 'Sal'
          ,0.50
          ,'kg'
          ,NULL );
INSERT INTO INGREDIENTES (
	NOMBRE
	,COSTO_UNITARIO
	,MAGNITUD
	,IMAGEN
) VALUES ( 'Pimienta Negra'
          ,1.50
          ,'100g'
          ,NULL );
INSERT INTO INGREDIENTES (
	NOMBRE
	,COSTO_UNITARIO
	,MAGNITUD
	,IMAGEN
) VALUES ( 'Ajo'
          ,4.00
          ,'cabeza'
          ,NULL );

INSERT INTO INGREDIENTES (
	NOMBRE
	,COSTO_UNITARIO
	,MAGNITUD
	,IMAGEN
) VALUES ( 'Huevo'
          ,0.20
          ,'unidad'
          ,NULL );
INSERT INTO INGREDIENTES (
	NOMBRE
	,COSTO_UNITARIO
	,MAGNITUD
	,IMAGEN
) VALUES ( 'Leche'
          ,1.10
          ,'litro'
          ,NULL );
INSERT INTO INGREDIENTES (
	NOMBRE
	,COSTO_UNITARIO
	,MAGNITUD
	,IMAGEN
) VALUES ( 'Harina de Trigo'
          ,1.50
          ,'kg'
          ,NULL );
INSERT INTO INGREDIENTES (
	NOMBRE
	,COSTO_UNITARIO
	,MAGNITUD
	,IMAGEN
) VALUES ( 'Azúcar'
          ,1.20
          ,'kg'
          ,NULL );
INSERT INTO INGREDIENTES (
	NOMBRE
	,COSTO_UNITARIO
	,MAGNITUD
	,IMAGEN
) VALUES ( 'Mantequilla'
          ,2.50
          ,'250g'
          ,NULL );
INSERT INTO INGREDIENTES (
	NOMBRE
	,COSTO_UNITARIO
	,MAGNITUD
	,IMAGEN
) VALUES ( 'Papa'
          ,1.00
          ,'kg'
          ,NULL );
INSERT INTO INGREDIENTES (
	NOMBRE
	,COSTO_UNITARIO
	,MAGNITUD
	,IMAGEN
) VALUES ( 'Zanahoria'
          ,0.80
          ,'kg'
          ,NULL );
INSERT INTO INGREDIENTES (
	NOMBRE
	,COSTO_UNITARIO
	,MAGNITUD
	,IMAGEN
) VALUES ( 'Limón'
          ,0.30
          ,'unidad'
          ,NULL );
INSERT INTO INGREDIENTES (
	NOMBRE
	,COSTO_UNITARIO
	,MAGNITUD
	,IMAGEN
) VALUES ( 'Queso Mozzarella'
          ,3.00
          ,'200g'
          ,NULL );
INSERT INTO INGREDIENTES (
	NOMBRE
	,COSTO_UNITARIO
	,MAGNITUD
	,IMAGEN
) VALUES ( 'Jamón'
          ,2.80
          ,'150g'
          ,NULL );
INSERT INTO INGREDIENTES (
	NOMBRE
	,COSTO_UNITARIO
	,MAGNITUD
	,IMAGEN
) VALUES ( 'Pan de Molde'
          ,2.00
          ,'paquete'
          ,NULL );
INSERT INTO INGREDIENTES (
	NOMBRE
	,COSTO_UNITARIO
	,MAGNITUD
	,IMAGEN
) VALUES ( 'Carne Molida de Res'
          ,9.00
          ,'kg'
          ,NULL );
INSERT INTO INGREDIENTES (
	NOMBRE
	,COSTO_UNITARIO
	,MAGNITUD
	,IMAGEN
) VALUES ( 'Pimiento Verde'
          ,0.60
          ,'unidad'
          ,NULL );
INSERT INTO INGREDIENTES (
	NOMBRE
	,COSTO_UNITARIO
	,MAGNITUD
	,IMAGEN
) VALUES ( 'Champiñones'
          ,2.20
          ,'250g'
          ,NULL );
INSERT INTO INGREDIENTES (
	NOMBRE
	,COSTO_UNITARIO
	,MAGNITUD
	,IMAGEN
) VALUES ( 'Vainilla'
          ,0.50
          ,'cucharadita'
          ,NULL );
          
          
UPDATE ingredientes SET COSTO_UNITARIO = 120 WHERE NOMBRE = 'Pechuga de Pollo';      -- kg
UPDATE ingredientes SET COSTO_UNITARIO = 28 WHERE NOMBRE = 'Tomate Saladet';         -- kg
UPDATE ingredientes SET COSTO_UNITARIO = 8 WHERE NOMBRE = 'Cebolla Blanca';          -- pza
UPDATE ingredientes SET COSTO_UNITARIO = 28 WHERE NOMBRE = 'Arroz Blanco';           -- kg
UPDATE ingredientes SET COSTO_UNITARIO = 6 WHERE NOMBRE = 'Aceite de Oliva';         -- cucharada
UPDATE ingredientes SET COSTO_UNITARIO = 12 WHERE NOMBRE = 'Sal';                    -- kg
UPDATE ingredientes SET COSTO_UNITARIO = 0.15 WHERE NOMBRE = 'Pimienta Negra';       -- g
UPDATE ingredientes SET COSTO_UNITARIO = 1.5 WHERE NOMBRE = 'Ajo';                   -- diente
UPDATE ingredientes SET COSTO_UNITARIO = 3.5 WHERE NOMBRE = 'Huevo';                 -- pza
UPDATE ingredientes SET COSTO_UNITARIO = 25 WHERE NOMBRE = 'Leche';                  -- litro
UPDATE ingredientes SET COSTO_UNITARIO = 22 WHERE NOMBRE = 'Harina de Trigo';        -- kg
UPDATE ingredientes SET COSTO_UNITARIO = 28 WHERE NOMBRE = 'Azúcar';                 -- kg
UPDATE ingredientes SET COSTO_UNITARIO = 0.12 WHERE NOMBRE = 'Mantequilla';          -- g
UPDATE ingredientes SET COSTO_UNITARIO = 20 WHERE NOMBRE = 'Papa';                   -- kg
UPDATE ingredientes SET COSTO_UNITARIO = 18 WHERE NOMBRE = 'Zanahoria';              -- kg
UPDATE ingredientes SET COSTO_UNITARIO = 5 WHERE NOMBRE = 'Limón';                   -- pza
UPDATE ingredientes SET COSTO_UNITARIO = 25 WHERE NOMBRE = 'Queso Mozzarella';       -- 100g
UPDATE ingredientes SET COSTO_UNITARIO = 0.09 WHERE NOMBRE = 'Jamón';                -- g
UPDATE ingredientes SET COSTO_UNITARIO = 40 WHERE NOMBRE = 'Pan de Molde';           -- paquete
UPDATE ingredientes SET COSTO_UNITARIO = 150 WHERE NOMBRE = 'Carne Molida de Res';   -- kg
UPDATE ingredientes SET COSTO_UNITARIO = 12 WHERE NOMBRE = 'Pimiento Verde';         -- pza
UPDATE ingredientes SET COSTO_UNITARIO = 0.08 WHERE NOMBRE = 'Champiñones';          -- g
UPDATE ingredientes SET COSTO_UNITARIO = 3 WHERE NOMBRE = 'Vainilla';                -- cucharadita
UPDATE ingredientes SET COSTO_UNITARIO = 5 WHERE NOMBRE = 'Epazote';                 -- rama
UPDATE ingredientes SET COSTO_UNITARIO = 6 WHERE NOMBRE = 'Chile';                   -- pza
UPDATE ingredientes SET COSTO_UNITARIO = 8 WHERE NOMBRE = 'Cilantro';                -- ramo
UPDATE ingredientes SET COSTO_UNITARIO = 280 WHERE NOMBRE = 'Mole';                  -- kg
UPDATE ingredientes SET COSTO_UNITARIO = 28 WHERE NOMBRE = 'Tortilla';               -- kg
UPDATE ingredientes SET COSTO_UNITARIO = 45 WHERE NOMBRE = 'Crema';                  -- litro


INSERT INTO ingredientes (NOMBRE, COSTO_UNITARIO, MAGNITUD)
VALUES ('Harina de Maíz Nixtamalizada', 20, 'kg');

INSERT INTO ingredientes (NOMBRE, COSTO_UNITARIO, MAGNITUD)
VALUES ('Agua', 0.02, 'litro');

INSERT INTO ingredientes (NOMBRE, COSTO_UNITARIO, MAGNITUD)
VALUES ('Lechuga', 15, 'pza');

INSERT INTO ingredientes (NOMBRE, COSTO_UNITARIO, MAGNITUD)
VALUES ('Frijoles Refritos', 32, 'kg');

INSERT INTO ingredientes (NOMBRE, COSTO_UNITARIO, MAGNITUD)
VALUES ('Queso Oaxaca', 28, '100g');

INSERT INTO ingredientes (NOMBRE, COSTO_UNITARIO, MAGNITUD)
VALUES ('Tomate Verde', 32, 'kg');

INSERT INTO ingredientes (NOMBRE, COSTO_UNITARIO, MAGNITUD)
VALUES ('Poro', 25, 'pza');

INSERT INTO ingredientes (NOMBRE, COSTO_UNITARIO, MAGNITUD)
VALUES ('Bistec', 160, 'kg');

INSERT INTO ingredientes (NOMBRE, COSTO_UNITARIO, MAGNITUD)
VALUES ('Nopales', 25, 'kg');

INSERT INTO ingredientes (NOMBRE, COSTO_UNITARIO, MAGNITUD)
VALUES ('Knorr Suiza', 2.5, 'cubito');

INSERT INTO ingredientes (NOMBRE, COSTO_UNITARIO, MAGNITUD)
VALUES ('Jugo Maggi', 35, 'botella 100ml');


COMMIT;

INSERT INTO PATROCINADOR(NOMBRE,ENLACE) VALUES('BACHOCO','https://bachoco.com/');

INSERT INTO PATROCINADOR(NOMBRE,ENLACE) VALUES('KNORR','https://www.knorr.com/es/home.html');

INSERT INTO PATROCINADOR(NOMBRE,ENLACE) VALUES('MAGGI','https://www.recetasnestle.com.mx/marcas/maggi');

UPDATE INGREDIENTES
SET ID_PATROCINADOR=1
WHERE ID_INGREDIENTE = 1;

-- RECETAS (Asignadas a usuarios 1, 2 y 3)
INSERT INTO RECETAS (
	ID_USUARIO
	,NOMBRE
	,IMAGEN
	,VISITAS_TOTALES
	,VISITAS_SEMANALES
	,CALIFICACION
) VALUES ( 1
          ,'Pollo en Salsa de Tomate'
          ,NULL
          ,150
          ,25
          ,4 );
INSERT INTO RECETAS (
	ID_USUARIO
	,NOMBRE
	,IMAGEN
	,VISITAS_TOTALES
	,VISITAS_SEMANALES
	,CALIFICACION
) VALUES ( 2
          ,'Arroz Blanco Perfecto'
          ,NULL
          ,200
          ,50
          ,5 );
INSERT INTO RECETAS (
	ID_USUARIO
	,NOMBRE
	,IMAGEN
	,VISITAS_TOTALES
	,VISITAS_SEMANALES
	,CALIFICACION
) VALUES ( 3
          ,'Sopa de Cebolla Gratinada'
          ,NULL
          ,80
          ,10
          ,3 );
INSERT INTO RECETAS (
	ID_USUARIO
	,NOMBRE
	,IMAGEN
	,VISITAS_TOTALES
	,VISITAS_SEMANALES
	,CALIFICACION
) VALUES ( 1
          ,'Ensalada Fresca de Verano'
          ,NULL
          ,120
          ,30
          ,4 );
INSERT INTO RECETAS (
	ID_USUARIO
	,NOMBRE
	,IMAGEN
	,VISITAS_TOTALES
	,VISITAS_SEMANALES
	,CALIFICACION
) VALUES ( 2
          ,'Pechuga de Pollo a la Plancha'
          ,NULL
          ,300
          ,75
          ,5 );
COMMIT;

-- DETALLES
-- Para Receta 1: Pollo en Salsa de Tomate
INSERT INTO DETALLES (
	ID_RECETA
	,ID_INGREDIENTE
	,CANTIDAD
	,COSTO
) VALUES ( 1
          ,1
          ,0.5
          ,4.25 ); -- 0.5 kg de Pollo
INSERT INTO DETALLES (
	ID_RECETA
	,ID_INGREDIENTE
	,CANTIDAD
	,COSTO
) VALUES ( 1
          ,2
          ,0.3
          ,0.75 ); -- 0.3 kg de Tomate
INSERT INTO DETALLES (
	ID_RECETA
	,ID_INGREDIENTE
	,CANTIDAD
	,COSTO
) VALUES ( 1
          ,3
          ,0.1
          ,0.18 ); -- 0.1 kg de Cebolla
INSERT INTO DETALLES (
	ID_RECETA
	,ID_INGREDIENTE
	,CANTIDAD
	,COSTO
) VALUES ( 1
          ,8
          ,0.05
          ,0.20 ); -- Ajo

-- Para Receta 2: Arroz Blanco Perfecto
INSERT INTO DETALLES (
	ID_RECETA
	,ID_INGREDIENTE
	,CANTIDAD
	,COSTO
) VALUES ( 2
          ,4
          ,0.25
          ,0.80 ); -- 0.25 kg de Arroz
INSERT INTO DETALLES (
	ID_RECETA
	,ID_INGREDIENTE
	,CANTIDAD
	,COSTO
) VALUES ( 2
          ,5
          ,0.02
          ,0.24 ); -- 20 ml de Aceite
INSERT INTO DETALLES (
	ID_RECETA
	,ID_INGREDIENTE
	,CANTIDAD
	,COSTO
) VALUES ( 2
          ,6
          ,0.01
          ,0.01 ); -- Sal al gusto

-- Para Receta 3: Sopa de Cebolla Gratinada
INSERT INTO DETALLES (
	ID_RECETA
	,ID_INGREDIENTE
	,CANTIDAD
	,COSTO
) VALUES ( 3
          ,3
          ,0.5
          ,0.90 ); -- 0.5 kg de Cebolla

-- Para Receta 4: Ensalada Fresca de Verano
INSERT INTO DETALLES (
	ID_RECETA
	,ID_INGREDIENTE
	,CANTIDAD
	,COSTO
) VALUES ( 4
          ,2
          ,0.2
          ,0.50 ); -- 0.2 kg de Tomate
INSERT INTO DETALLES (
	ID_RECETA
	,ID_INGREDIENTE
	,CANTIDAD
	,COSTO
) VALUES ( 4
          ,3
          ,0.1
          ,0.18 ); -- 0.1 kg de Cebolla
INSERT INTO DETALLES (
	ID_RECETA
	,ID_INGREDIENTE
	,CANTIDAD
	,COSTO
) VALUES ( 4
          ,5
          ,0.03
          ,0.36 ); -- 30 ml de Aceite

-- Para Receta 5: Pechuga de Pollo a la Plancha
INSERT INTO DETALLES (
	ID_RECETA
	,ID_INGREDIENTE
	,CANTIDAD
	,COSTO
) VALUES ( 5
          ,1
          ,0.2
          ,1.70 ); -- 0.2 kg de Pollo
INSERT INTO DETALLES (
	ID_RECETA
	,ID_INGREDIENTE
	,CANTIDAD
	,COSTO
) VALUES ( 5
          ,6
          ,0.01
          ,0.01 ); -- Sal
INSERT INTO DETALLES (
	ID_RECETA
	,ID_INGREDIENTE
	,CANTIDAD
	,COSTO
) VALUES ( 5
          ,7
          ,0.005
          ,0.07 ); -- Pimienta
COMMIT;

-- PETICION_IA
INSERT INTO PETICION_IA (
	ESTATUS
	,DESCRIPCION
) VALUES ( 'PEN'
          ,'Generar imagen para pollo con tomate' );

-- PASOS
-- Para Receta 1: Pollo en Salsa de Tomate
INSERT INTO PASOS (
	ID_RECETA
	,INDICE_PASO
	,NOTAS
	,IMAGEN
	,ID_PET_IA
) VALUES ( 1
          ,1
          ,'Cortar el pollo en cubos y sazonar con sal y pimienta.'
          ,NULL
          ,NULL );
INSERT INTO PASOS (
	ID_RECETA
	,INDICE_PASO
	,NOTAS
	,IMAGEN
	,ID_PET_IA
) VALUES ( 1
          ,2
          ,'Sofreír la cebolla y el ajo picados. Añadir el tomate triturado y cocinar por 10 minutos.'
          ,NULL
          ,1 );
INSERT INTO PASOS (
	ID_RECETA
	,INDICE_PASO
	,NOTAS
	,IMAGEN
	,ID_PET_IA
) VALUES ( 1
          ,3
          ,'Agregar el pollo a la salsa y cocinar a fuego lento hasta que esté bien cocido.'
          ,NULL
          ,NULL );

-- Para Receta 2: Arroz Blanco Perfecto
INSERT INTO PASOS (
	ID_RECETA
	,INDICE_PASO
	,NOTAS
	,IMAGEN
	,ID_PET_IA
) VALUES ( 2
          ,1
          ,'Lavar el arroz hasta que el agua salga clara.'
          ,NULL
          ,NULL );
INSERT INTO PASOS (
	ID_RECETA
	,INDICE_PASO
	,NOTAS
	,IMAGEN
	,ID_PET_IA
) VALUES ( 2
          ,2
          ,'En una olla, calentar el aceite y sofreír el arroz por 2 minutos.'
          ,NULL
          ,NULL );
INSERT INTO PASOS (
	ID_RECETA
	,INDICE_PASO
	,NOTAS
	,IMAGEN
	,ID_PET_IA
) VALUES ( 2
          ,3
          ,'Añadir el doble de agua que de arroz, sal al gusto, y llevar a ebullición. Bajar el fuego, tapar y cocinar por 15 minutos.'
          ,NULL
          ,NULL );

-- Para Receta 5: Pechuga de Pollo a la Plancha
INSERT INTO PASOS (
	ID_RECETA
	,INDICE_PASO
	,NOTAS
	,IMAGEN
	,ID_PET_IA
) VALUES ( 5
          ,1
          ,'Sazonar la pechuga de pollo con sal y pimienta por ambos lados.'
          ,NULL
          ,NULL );
INSERT INTO PASOS (
	ID_RECETA
	,INDICE_PASO
	,NOTAS
	,IMAGEN
	,ID_PET_IA
) VALUES ( 5
          ,2
          ,'Calentar una plancha o sartén a fuego medio-alto con un poco de aceite.'
          ,NULL
          ,NULL );
INSERT INTO PASOS (
	ID_RECETA
	,INDICE_PASO
	,NOTAS
	,IMAGEN
	,ID_PET_IA
) VALUES ( 5
          ,3
          ,'Cocinar la pechuga por 4-5 minutos por cada lado, hasta que esté dorada y cocida por dentro.'
          ,NULL
          ,NULL );

commit;