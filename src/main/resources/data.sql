SELECT 1;
INSERT INTO addresses(id, city, estate, logradouro, neighborhood, number, zip_code, created)
	VALUES
		('1', 'São Paulo', 	'SP', 'Rua João Carlos Ferreira', 	'Parque Boa Esperança', '144', '08341-385', '2022-03-09 10:17:22'),
		('2', 'Goiânia',	'GO', 'Rua EF36',					'Residencial Eli Forte','828', '74371-030', '2022-03-09 10:17:22');
INSERT INTO users (id, profession, academic_education, academic_education_status, birth_date, cell_phone, deleted, emergency_contact, enabled, father, gender, landline, main_email, mother, name, rg, cpf, secondary_email, social_name, address_id, created)
VALUES
	(1,'Sensei de Karate', 1, 1,'1965-10-01','(61) 3925-9355', FALSE,'(11)96345-1198', TRUE, 'Theo Márcio Martins', 'F', '(61) 99412-6943 ','mariahrenatamartins76@gmail.com','Alana Fabiana ','Mariah Renata Martins','34.892.237-1','600.590.480-93', 'mariahrenatamartins76@hotmail.com','mamari',1, '2022-03-09 10:17:22'),
	(2,'Sensei de Karate', 1, 2,'1956-03-02','(62) 995845450', FALSE,'(11)12345-6789', TRUE, 'Miguel Bruno Moreira','M', NULL,'vicente_moreira@orthoi.com.br','Jennifer Stefany','Vicente Erick Moreira','281119727','763.139.570-59' ,NULL,NULL,2, '2022-03-09 10:17:22');
INSERT INTO ceos (id) VALUES ('1');
INSERT INTO login (username, enabled, password, user_login_id) VALUES ('admin', TRUE, '$2a$10$2ZpoQnKlRlF0RHxPVXgOtuqIDfK3I9niZ1cMayhla55ZOaYTRm7RG', '1');
INSERT INTO authorities VALUES (1, 'USER');

INSERT INTO gyms (id, created, name, address_id, ceo_id) VALUES ('1', '2022-03-09 10:17:22', 'Akaji Dojo', '1', '1');
INSERT INTO units (id, created, address_id, head_office_id) VALUES ('1', '2022-03-09 10:17:22', '1', '1');
INSERT INTO managers (id, unit_id) VALUES ('2', '1');


INSERT INTO academic_education (id,	designation, value) VALUES (1,	'CHILD_EDUCATION',				'EDUCAÇÃO INFANTIL');
INSERT INTO academic_education (id,	designation, value) VALUES (2,	'ELEMENTARY_SCHOOL', 			'ESCOLA PRIMÁRIA');
INSERT INTO academic_education (id,	designation, value) VALUES (3,	'HIGH_SCHOOL', 					'ENSINO MÉDIO');
INSERT INTO academic_education (id,	designation, value) VALUES (4,	'YOUTH_AND_ADULT_EDUCATION',	'EDUCAÇÃO DE JOVENS E ADULTOS (EJA)');
INSERT INTO academic_education (id,	designation, value) VALUES (5,	'EDUCATION_IN_THE_COUNTRYSIDE', 'EDUCAÇÃO NO CAMPO');
INSERT INTO academic_education (id,	designation, value) VALUES (6,	'TECHNICAL_EDUCATION', 			'EDUCAÇÃO TÉCNICA');
INSERT INTO academic_education (id,	designation, value) VALUES (7,	'UNIVERSITY_EDUCATION', 		'FORMAÇÃO UNIVERSITÁRIA');
INSERT INTO academic_education (id,	designation, value) VALUES (8,	'GRADUATE_SPECIALIZATION',		'ESPECIALIZAÇÃO DE PÓS-GRADUAÇÃO');
INSERT INTO academic_education (id,	designation, value) VALUES (9,	'MASTERS_DEGREE',				'MESTRADO');
INSERT INTO academic_education (id,	designation, value) VALUES (10,	'DOCTORATE_DEGREE',				'DOUTORADO');
INSERT INTO academic_education (id,	designation, value) VALUES (11,	'POST_DOCTORAL',				'PÓS DOUTORADO');

INSERT INTO addresses(id, city, estate, logradouro, neighborhood, number, zip_code, created) VALUES
	(3,'Campo Grande', 	'MS','Rua Júlio Dittmar',			'Centro',						920,'79002320','2022-03-09 10:17:22'),
	(4,'Salvador', 		'BA','Travessa Maroto',				'Engenho Velho da Federação',	506,'40225050','2022-03-09 10:17:22'),
	(5,'Manaus', 		'AM','Rua Águas Marinhas',			'Tancredo Neves',				164,'69087053','2022-03-09 10:17:22'),
	(6,'Rio Grande', 	'RS','Rua João Meirelles Leite',	'Quinta',						530,'96222788','2022-03-09 10:17:22');

INSERT INTO users (id, profession, academic_education, academic_education_status, birth_date, cell_phone, deleted, emergency_contact, enabled, father, gender, landline, main_email, mother, name, rg, cpf, secondary_email, social_name, address_id, created)
VALUES
	(3,'TEST',2,1,'1970-03-02','67995921322', FALSE,'11123456789', TRUE, NULL,'M',NULL,'manuelthiagodamata@facilitycom.com.br','Julia Ester','Manuel Thiago Elias da Mata','170642604', '409.943.090-15', NULL,NULL,3, '2022-03-09 10:17:22'),
	(4,'TEST',9,0,'1945-05-16','71995518583', FALSE,'11123456789', TRUE, NULL,'M',NULL,'emanuel.vicente.pereira@gruposimoes.com.br','Stefany SophiE','Emanuel Vicente Pereira','407327836', '623.514.170-03', NULL,NULL,4, '2022-03-09 10:17:22'),
	(5,'TEST',6,2,'1981-02-16','92988411478', FALSE,'11987654321', TRUE, 'Pietro Diogo Sebastião da Rosa','F',NULL,'sabrinalouisedarosa@brunofaria.com','Vera Laís','Sabrina Louise da Rosa','199220037', '498.655.170-19', NULL,NULL,5, '2022-03-09 10:17:22'),
	(6,'TEST',7,1,'2004-03-03','53988576752', FALSE,'11654987321', TRUE, 'Pietro Osvaldo das Neves','F',NULL,'sueli-dasneves74@phocus.com.br','Adriana Marlene Giovanna','Sueli Analu Mariana das Neves','389317299', '412.362.910-50', NULL,NULL,6, '2022-03-09 10:17:22');

INSERT INTO students
VALUES
	(0,0,20223500019562,'2022-03-03',2),
	(0,0,20223500019703,'2022-03-03',3),
	(0,0,20223500019454,'2022-03-03',4),
	(0,0,20223500019815,'2022-03-03',5),
	(0,0,20223500020046,'2022-02-27',6);


--INSERT INTO authorities VALUES (1, 'ROLE_ADM');
--INSERT INTO login_authorities ('admin', 1);

--
--INSERT INTO graduations (id, name, level) VALUES (1,'WHITE',	'7º KYU');
--INSERT INTO graduations (id, name, level) VALUES (2,'YELLOW', '6º KYU');
--INSERT INTO graduations (id, name, level) VALUES (3,'ORANGE', '5º KYU');
--INSERT INTO graduations (id, name, level) VALUES (4,'BLUE',	'4º KYU');
--INSERT INTO graduations (id, name, level) VALUES (5,'GREEN', 	'3º KYU');
--INSERT INTO graduations (id, name, level) VALUES (6,'PURPLE', '2º KYU');
--INSERT INTO graduations (id, name, level) VALUES (7,'BROWN', 	'1º KYU');
--INSERT INTO graduations (id, name, level) VALUES (8,'BLACK', 	'1º DAN');
--INSERT INTO graduations (id, name, level) VALUES (9,'BLACK', 	'2º DAN');
--INSERT INTO graduations (id, name, level) VALUES (10,'BLACK', '3º DAN');
--INSERT INTO graduations (id, name, level) VALUES (11,'BLACK', '4º DAN');
--INSERT INTO graduations (id, name, level) VALUES (12,'BLACK', '5º DAN');
--INSERT INTO graduations (id, name, level) VALUES (13,'BLACK', '6º DAN');
--INSERT INTO graduations (id, name, level) VALUES (14,'RED_AND_WHITE', NULL);
--INSERT INTO graduations (id, name, level) VALUES (15,'RED', NULL);
--
--INSERT INTO users(username, enabled, password)
--SELECT 'root', 1, '1234'
--	FROM users
--		WHERE NOT EXISTS
--		    (SELECT 1
--			     FROM users
--			     	WHERE username = 'root');
--
/*
	INSERT INTO users(username, enabled, password) VALUES('root', 1, '1234');
INSERT INTO addresses (id, city, complement, estate, logradouro, neighborhood, number, reference_point, zip_code) VALUES(1, 'SÃO PAULO', '8A', 'SP', 'RUA JOAQUIM MARIANO', 'VILA PORTUGUESA', 138, 'AO LADO DA IGREJA', '03987-050');
INSERT INTO user (id,profession, academic_education, academic_education_status, birth_date, cell_phone,  father, gender, landline, main_email, mother, name, rg, secondary_email, social_name, address_id, user_login_id)
	VALUES (1, 'ANALISTA E DESENVOLVEDOR DE SISTEMAS', 'SUPERIOR', 1, STR_TO_DATE('1998-11-03', '%Y-%m-%d'), '11991816561', 'DARCI RAMOS', 1, '1127017986', 'IGOR.DSR.TEC@HOTMAIL.COM', 'HELENA MARIA DA SILVA', 'IGOR DA SILVA RAMOS', '38.728.697-4', 'IGOR.DSR.TEC@GMAIL.COM', 'IGOR', 1, 'root');


**/

INSERT INTO questions (id, description, type) VALUES ('1', 'Pratica alguma tipo de atividade física?',   'D');
INSERT INTO questions (id, description, type) VALUES ('2', 'Fumante?',   'D');
INSERT INTO questions (id, description, type) VALUES ('3', 'Tem algum problema Cardíaco?',   'D');
INSERT INTO questions (id, description, type) VALUES ('4', 'Ja teve Covid?',   'D');
INSERT INTO questions (id, description, type) VALUES ('5', 'Tem algum problema cardiaco?',   'D');
INSERT INTO questions (id, description, type) VALUES ('6', 'Tem Diabetes?',   'D');
INSERT INTO questions (id, description, type) VALUES ('7', 'Tem algum problema ósseo ou articular?',   'D');
INSERT INTO questions (id, description, type) VALUES ('8', 'Sente dores musculares ou articulares?',   'D');
INSERT INTO questions (id, description, type) VALUES ('9', 'Possui alguma fratura, traumatismo ou lesão?',   'D');
INSERT INTO questions (id, description, type) VALUES ('10', 'Fez alguma cirurgia?',   'D');
INSERT INTO questions (id, description, type) VALUES ('11', 'Toma algum medicamento de uso continuo?',   'D');
INSERT INTO questions (id, description, type) VALUES ('12', 'Alguma problema de saude de gostaria de mencionar?',   'D');
INSERT INTO questions (id, description, parent_id, type) VALUES ('13', 'Quais?', '1', 'E');
INSERT INTO questions (id, description, parent_id, type) VALUES ('14', 'Quantos cigarros ao dia?', '2', 'E');
INSERT INTO questions (id, description, parent_id, type) VALUES ('15', 'Qual?', '3', 'E');
INSERT INTO questions (id, description, parent_id, type) VALUES ('16', 'Foi internado?', '4', 'D');
INSERT INTO questions (id, description, parent_id, type) VALUES ('17', 'Qual?', '5', 'E');
INSERT INTO questions (id, description, parent_id, type) VALUES ('18', 'Qual?', '7', 'E');
INSERT INTO questions (id, description, parent_id, type) VALUES ('19', 'Onde?', '8', 'E');
INSERT INTO questions (id, description, parent_id, type) VALUES ('20', 'Onde?', '9', 'E');
INSERT INTO questions (id, description, parent_id, type) VALUES ('21', 'Quais?', '10', 'E');
INSERT INTO questions (id, description, parent_id, type) VALUES ('22', 'Pra qual finalidade?', '11', 'E');
INSERT INTO questions (id, description, parent_id, type) VALUES ('23', 'Pra qual finalidade?', '12', 'E');
INSERT INTO questions (id, description, parent_id, type) VALUES ('24', 'Com que frequencia?', '13', 'E');
INSERT INTO questions (id, description, parent_id, type) VALUES ('25', 'Quanto tempo?', '16', 'E');

INSERT INTO tests  (id, description) VALUES ('1','Ficha Médica');

INSERT INTO quiz  (id, test_id, question_id) VALUES ('1','1', '1');
INSERT INTO quiz  (id, test_id, question_id) VALUES ('2','1', '2');
INSERT INTO quiz  (id, test_id, question_id) VALUES ('3','1', '3');
INSERT INTO quiz  (id, test_id, question_id) VALUES ('4','1', '4');
INSERT INTO quiz  (id, test_id, question_id) VALUES ('5','1', '5');
INSERT INTO quiz  (id, test_id, question_id) VALUES ('6','1', '6');
INSERT INTO quiz  (id, test_id, question_id) VALUES ('7','1', '7');
INSERT INTO quiz  (id, test_id, question_id) VALUES ('8','1', '8');
INSERT INTO quiz  (id, test_id, question_id) VALUES ('9','1', '9');
INSERT INTO quiz  (id, test_id, question_id) VALUES ('10','1', '10');
INSERT INTO quiz  (id, test_id, question_id) VALUES ('11','1', '11');
INSERT INTO quiz  (id, test_id, question_id) VALUES ('12','1', '12');
INSERT INTO quiz  (id, test_id, question_id) VALUES ('13','1', '13');
INSERT INTO quiz  (id, test_id, question_id) VALUES ('14','1', '14');
INSERT INTO quiz  (id, test_id, question_id) VALUES ('15','1', '15');
INSERT INTO quiz  (id, test_id, question_id) VALUES ('16','1', '16');
INSERT INTO quiz  (id, test_id, question_id) VALUES ('17','1', '17');
INSERT INTO quiz  (id, test_id, question_id) VALUES ('18','1', '18');
INSERT INTO quiz  (id, test_id, question_id) VALUES ('19','1', '19');
INSERT INTO quiz  (id, test_id, question_id) VALUES ('20','1', '20');
INSERT INTO quiz  (id, test_id, question_id) VALUES ('21','1', '21');
INSERT INTO quiz  (id, test_id, question_id) VALUES ('22','1', '22');
INSERT INTO quiz  (id, test_id, question_id) VALUES ('23','1', '23');
INSERT INTO quiz  (id, test_id, question_id) VALUES ('24','1', '24');
INSERT INTO quiz  (id, test_id, question_id) VALUES ('25','1', '25');

/*
WITH RECURSIVE questions_path AS
  (SELECT *, 1 AS lvl
   FROM questions
   WHERE parent_id IS NULL
   UNION ALL SELECT c.*,
                    cp.lvl + 1
   FROM questions_path AS cp
   INNER JOIN questions AS c ON cp.id = c.parent_id)
SELECT *
FROM questions_path
order by parent_id, id, lvl;

WITH RECURSIVE questions_path AS
  (SELECT *,
          1 AS lvl
   FROM questions
   WHERE parent_id IS NULL
   UNION ALL SELECT c.*,
                    cp.lvl + 1
   FROM questions_path AS cp
   INNER JOIN questions AS c ON cp.id = c.parent_id)
SELECT qp.*
FROM questions_path qp
INNER JOIN quiz q on(qp.id = q.id)
INNER JOIN tests t ON (q.test_id = t.id)
ORDER BY parent_id,
         id,
         lvl;

*/