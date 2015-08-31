INSERT INTO Category(ID, MODIFICATIONDATE, NAME, CODIGO, DESCRIPCION) VALUES(180, DATE '2015-08-18', 'GR.DIESEL >7,5 <=75KVA', '8502118123', 'GR.DIESEL >7,5 <=75KVA');
INSERT INTO Category(ID, MODIFICATIONDATE, NAME, CODIGO, DESCRIPCION) VALUES(212, DATE '2015-07-23', 'CUADROS', '85371099', 'CUADROS');
INSERT INTO Category(ID, MODIFICATIONDATE, NAME, CODIGO, DESCRIPCION) VALUES(213, DATE '2015-07-23', 'ALTERNADORES', '85016180', 'ALTERNADORES');
INSERT INTO Category(ID, MODIFICATIONDATE, NAME, CODIGO, DESCRIPCION) VALUES(244, NULL, 'GR.GASOLINA >375 <=750KVA', '85022060', 'GR.GASOLINA >375 <=750KVA');
INSERT INTO Category(ID, MODIFICATIONDATE, NAME, CODIGO, DESCRIPCION) VALUES(245, NULL, 'PARTES MOTORES', '84099900', 'PARTES MOTORES');
INSERT INTO Category(ID, MODIFICATIONDATE, NAME, CODIGO, DESCRIPCION) VALUES(246, NULL, 'PARTES BOMBAS', '84139100', 'PARTES BOMBAS');
INSERT INTO Category(ID, MODIFICATIONDATE, NAME, CODIGO, DESCRIPCION) VALUES(247, NULL, 'BOMBAS SUMERGIDAS', '84138100', 'BOMBAS SUMERGIDAS');

INSERT INTO Periodo(ID, MONTH, YEAR) VALUES(277, 7, 2015);
INSERT INTO Periodo(ID, MONTH, YEAR) VALUES(310, 6, 2015);
INSERT INTO Periodo(ID, MONTH, YEAR) VALUES(373, 6, 2014);

INSERT INTO Proveedor(ID, CPOSTAL, DOCUMENTO, DOMICILIO, NAME, POBLACION, CODIGO, PROVINCIA) VALUES(108, NULL, 'PT-DDU-CAMION', '', 'MAQUILOENDRO', '', '91286', '');
INSERT INTO Proveedor(ID, CPOSTAL, DOCUMENTO, DOMICILIO, NAME, POBLACION, CODIGO, PROVINCIA) VALUES(249, NULL, 'FR-DDU-CAMION', NULL, 'HYDROSUD', NULL, '91123', NULL);
INSERT INTO Proveedor(ID, CPOSTAL, DOCUMENTO, DOMICILIO, NAME, POBLACION, CODIGO, PROVINCIA) VALUES(154, NULL, 'PT-DDU-CAMION', NULL, 'SOLREGAS', '', '91309', NULL);
INSERT INTO Proveedor(ID, CPOSTAL, DOCUMENTO, DOMICILIO, NAME, POBLACION, CODIGO, PROVINCIA) VALUES(250, NULL, 'PT-DDU-CAMION', NULL, 'SCALBOMBAS', NULL, '91220', NULL);
INSERT INTO Proveedor(ID, CPOSTAL, DOCUMENTO, DOMICILIO, NAME, POBLACION, CODIGO, PROVINCIA) VALUES(248, '08900', 'PT-DDU-CAMION', 'domi111', 'IMPER REGAS', 'pob111', '91219', 'prov111');

INSERT INTO Provincia(CODIGO, NAME) VALUES(1, 'ALAVA/ARABA');
INSERT INTO Provincia(CODIGO, NAME) VALUES(2, 'ALBACETE');
INSERT INTO Provincia(CODIGO, NAME) VALUES(3, 'ALICANTE/ALACANT');
INSERT INTO Provincia(CODIGO, NAME) VALUES(4, 'ALMERIA');
INSERT INTO Provincia(CODIGO, NAME) VALUES(5, 'AVILA');
INSERT INTO Provincia(CODIGO, NAME) VALUES(6, 'BADAJOZ');
INSERT INTO Provincia(CODIGO, NAME) VALUES(7, 'BALEARES/BALEARS');
INSERT INTO Provincia(CODIGO, NAME) VALUES(8, 'BARCELONA');
INSERT INTO Provincia(CODIGO, NAME) VALUES(9, 'BURGOS');
INSERT INTO Provincia(CODIGO, NAME) VALUES(10, 'CACERES');
INSERT INTO Provincia(CODIGO, NAME) VALUES(11, 'CADIZ');
INSERT INTO Provincia(CODIGO, NAME) VALUES(12, 'CASTELLON/CASTELLO');
INSERT INTO Provincia(CODIGO, NAME) VALUES(13, 'C. REAL');
INSERT INTO Provincia(CODIGO, NAME) VALUES(14, 'CORDOBA');
INSERT INTO Provincia(CODIGO, NAME) VALUES(15, 'A CORUÑA');
INSERT INTO Provincia(CODIGO, NAME) VALUES(16, 'CUENCA ');
INSERT INTO Provincia(CODIGO, NAME) VALUES(17, 'GIRONA');
INSERT INTO Provincia(CODIGO, NAME) VALUES(18, 'GRANADA');
INSERT INTO Provincia(CODIGO, NAME) VALUES(19, 'GUADALAJARA');
INSERT INTO Provincia(CODIGO, NAME) VALUES(20, 'GUIPÚCOA/GIPUZKOA');
INSERT INTO Provincia(CODIGO, NAME) VALUES(21, 'HUELVA');
INSERT INTO Provincia(CODIGO, NAME) VALUES(22, 'HUESCA');
INSERT INTO Provincia(CODIGO, NAME) VALUES(23, 'JAEN');
INSERT INTO Provincia(CODIGO, NAME) VALUES(24, 'LEON');
INSERT INTO Provincia(CODIGO, NAME) VALUES(25, 'LLEIDA');
INSERT INTO Provincia(CODIGO, NAME) VALUES(26, 'LA RIOJA');
INSERT INTO Provincia(CODIGO, NAME) VALUES(27, 'LUGO');
INSERT INTO Provincia(CODIGO, NAME) VALUES(28, 'MADRID');
INSERT INTO Provincia(CODIGO, NAME) VALUES(29, 'MALAGA');
INSERT INTO Provincia(CODIGO, NAME) VALUES(30, 'MURCIA');
INSERT INTO Provincia(CODIGO, NAME) VALUES(31, 'NAVARRA');
INSERT INTO Provincia(CODIGO, NAME) VALUES(32, 'OURENSE ');
INSERT INTO Provincia(CODIGO, NAME) VALUES(33, 'ASTURIAS');
INSERT INTO Provincia(CODIGO, NAME) VALUES(34, 'PALENCIA');
INSERT INTO Provincia(CODIGO, NAME) VALUES(36, 'PONTEVEDRA');
INSERT INTO Provincia(CODIGO, NAME) VALUES(37, 'SALAMANCA');
INSERT INTO Provincia(CODIGO, NAME) VALUES(39, 'CANTABRIA');
INSERT INTO Provincia(CODIGO, NAME) VALUES(40, 'SEGOVIA');
INSERT INTO Provincia(CODIGO, NAME) VALUES(41, 'SEVILLA');
INSERT INTO Provincia(CODIGO, NAME) VALUES(42, 'SORIA');
INSERT INTO Provincia(CODIGO, NAME) VALUES(43, 'TARRAGONA');
INSERT INTO Provincia(CODIGO, NAME) VALUES(44, 'TERUEL');
INSERT INTO Provincia(CODIGO, NAME) VALUES(45, 'TOLEDO');
INSERT INTO Provincia(CODIGO, NAME) VALUES(46, 'VALENCIA');
INSERT INTO Provincia(CODIGO, NAME) VALUES(47, 'VALLADOLID');
INSERT INTO Provincia(CODIGO, NAME) VALUES(48, 'VIZCAYA/BIZKAIA');
INSERT INTO Provincia(CODIGO, NAME) VALUES(49, 'ZAMORA');
INSERT INTO Provincia(CODIGO, NAME) VALUES(50, 'ZARAGOZA ');


INSERT INTO User(ID, EMAIL, NAME, PASSWORD, TOKEN, USERNAME, PERIODO_ID) VALUES(10000, 'lnieto@tecnoplus.es', 'Lucia Nieto', 'lnieto', 'c3fd7641-bcf4-4fe2-92f8-55ad7ed3dcbd', 'lnieto', 310);
INSERT INTO User(ID, EMAIL, NAME, PASSWORD, TOKEN, USERNAME, PERIODO_ID) VALUES(10001, 'xavier.cortada@yahoo.es', 'Xavier Cortada', 'xcortada', '', 'xcortada', 310);



INSERT INTO Transporte(CODIGO, NAME) VALUES('01', 'Transporte marítimo');
INSERT INTO Transporte(CODIGO, NAME) VALUES('02', 'Transporte por carretera');
INSERT INTO Transporte(CODIGO, NAME) VALUES('03', 'Transporte por ferrocarril');
INSERT INTO Transporte(CODIGO, NAME) VALUES('04', 'Transporte aéreo');
INSERT INTO Transporte(CODIGO, NAME) VALUES('05', 'Env\u00edos Postales');
INSERT INTO Transporte(CODIGO, NAME) VALUES('07', 'Instalaciones Fijas de Transporte');
INSERT INTO Transporte(CODIGO, NAME) VALUES('08', 'Transporte de navegación interior');
INSERT INTO Transporte(CODIGO, NAME) VALUES('09', 'Autopropulsión');


INSERT INTO Pais(CODIGO, NAME, SIGLA) VALUES(1, 'FRANCIA', 'FR');
INSERT INTO Pais(CODIGO, NAME, SIGLA) VALUES(3, 'PaisES BAJOS (HOLANDA)', 'NL');
INSERT INTO Pais(CODIGO, NAME, SIGLA) VALUES(4, 'ALEMANIA', 'DE');
INSERT INTO Pais(CODIGO, NAME, SIGLA) VALUES(5, 'ITALIA', 'IT');
INSERT INTO Pais(CODIGO, NAME, SIGLA) VALUES(6, 'REINO UNIDO', 'GB');
INSERT INTO Pais(CODIGO, NAME, SIGLA) VALUES(7, 'IRLANDA', 'IE');
INSERT INTO Pais(CODIGO, NAME, SIGLA) VALUES(8, 'DINAMARCA', 'DK');
INSERT INTO Pais(CODIGO, NAME, SIGLA) VALUES(9, 'GRECIA', 'GR');
INSERT INTO Pais(CODIGO, NAME, SIGLA) VALUES(10, 'PORTUGAL', 'PT');
INSERT INTO Pais(CODIGO, NAME, SIGLA) VALUES(11, 'ESPAÑA', 'ES');
INSERT INTO Pais(CODIGO, NAME, SIGLA) VALUES(17, 'BELGICA', 'BE');
INSERT INTO Pais(CODIGO, NAME, SIGLA) VALUES(18, 'LUXEMBURGO', 'LU');
INSERT INTO Pais(CODIGO, NAME, SIGLA) VALUES(30, 'SUECIA', 'SE');
INSERT INTO Pais(CODIGO, NAME, SIGLA) VALUES(32, 'FINLANDIA', 'FI');
INSERT INTO Pais(CODIGO, NAME, SIGLA) VALUES(38, 'AUSTRIA', 'AT');
INSERT INTO Pais(CODIGO, NAME, SIGLA) VALUES(46, 'MALTA', 'MT');
INSERT INTO Pais(CODIGO, NAME, SIGLA) VALUES(53, 'ESTONIA', 'EE');
INSERT INTO Pais(CODIGO, NAME, SIGLA) VALUES(54, 'LETONIA', 'LV');
INSERT INTO Pais(CODIGO, NAME, SIGLA) VALUES(55, 'LITUANIA', 'LT');
INSERT INTO Pais(CODIGO, NAME, SIGLA) VALUES(60, 'POLONIA', 'PL');
INSERT INTO Pais(CODIGO, NAME, SIGLA) VALUES(61, 'REPUBLICA CHECA', 'CZ');
INSERT INTO Pais(CODIGO, NAME, SIGLA) VALUES(63, 'ESLOVAQUIA', 'SK');
INSERT INTO Pais(CODIGO, NAME, SIGLA) VALUES(64, 'HUNGRIA', 'HU');
INSERT INTO Pais(CODIGO, NAME, SIGLA) VALUES(66, 'RUMANIA', 'RO');
INSERT INTO Pais(CODIGO, NAME, SIGLA) VALUES(68, 'BULGARIA', 'BG');
INSERT INTO Pais(CODIGO, NAME, SIGLA) VALUES(91, 'ESLOVENIA', 'SI');
INSERT INTO Pais(CODIGO, NAME, SIGLA) VALUES(92, 'CROACIA', 'HR');
INSERT INTO Pais(CODIGO, NAME, SIGLA) VALUES(600, 'CHIPRE', 'CY');





