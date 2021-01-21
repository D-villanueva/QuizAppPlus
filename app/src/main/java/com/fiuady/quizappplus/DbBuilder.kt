package com.fiuady.quizappplus

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.fiuady.quizappplus.db.AppDatabase
import com.fiuady.quizappplus.db.Questions

class DbBuilder:ViewModel() {

    fun buildBd(context: Context) :AppDatabase{
        val db = Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "quizapp.db"
        ).allowMainThreadQueries()
            .addCallback(object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    //temas
                    db.execSQL("INSERT INTO themes (id, title) VALUES (0, 'Cine')");
                    db.execSQL("INSERT INTO themes (id, title) VALUES (1, 'Musica')");
                    db.execSQL("INSERT INTO themes (id, title) VALUES (2, 'Ciencia')");
                    db.execSQL("INSERT INTO themes (id, title) VALUES (3, 'Deportes')");
                    db.execSQL("INSERT INTO themes (id, title) VALUES (4, 'Arte')");
                    db.execSQL("INSERT INTO themes (id, title) VALUES (5, 'Videojuegos')");
                    //preguntas--cine
                    db.execSQL("INSERT INTO questions VALUES (1,0, 'Pensamos demasiado y sentimos muy poco')")
                    db.execSQL("INSERT INTO questions VALUES (2,0,'Hakuna Matata...')")
                    db.execSQL("INSERT INTO questions VALUES (3,0,'Yo soy tu padre...')")
                    db.execSQL("INSERT INTO questions VALUES (4,0,'Puede que no sea muy listo, pero sé lo que es el amor...')")
                    db.execSQL("INSERT INTO questions VALUES (5,0,'Dicen que los mejores brillan con más fuerza en las situaciones más difíciles...')")
                    //preguntas--musica
                    db.execSQL("INSERT INTO questions VALUES (6,1,'A quién pertenece \"La sonata Claro de Luna?\"')")
                    db.execSQL("INSERT INTO questions VALUES (7,1,'A quién pertenece \"Las cuatro estaciones?\"')")
                    db.execSQL("INSERT INTO questions VALUES (8,1,'A quién pertenece \"Ballet de Rosamunda?\"')")
                    db.execSQL("INSERT INTO questions VALUES (9,1,'\"La flauta mágica – La reina de la noche pertenece a.. ?\"')")
                    db.execSQL("INSERT INTO questions VALUES (10,1,'\"El destino toca a la puerta pertenece a... ?\"')")
                    //preguntas--ciencia
                    db.execSQL("INSERT INTO questions VALUES (11,2,'¿Qué tipo de carga tienen los electrones?')")
                    db.execSQL("INSERT INTO questions VALUES (12,2,'¿Qué es el H2O?')")
                    db.execSQL("INSERT INTO questions VALUES (13,2,'¿Cuánto es 10 + 90 -50 +30')")
                    db.execSQL("INSERT INTO questions VALUES (14,2,'¿Cuál es la ciencia que estudia los insectos?')")
                    db.execSQL("INSERT INTO questions VALUES (15,2,'¿Cuál es el nombre científico de los glóbulos rojos?')")
                    //preguntas--deporte
                    db.execSQL("INSERT INTO questions VALUES (16,3,'¿Cuánto vale un tiro de media cancha en basquetbol?')")
                    db.execSQL("INSERT INTO questions VALUES (17,3,'¿En que año fueron los últimos juegos olímpicos?')")
                    db.execSQL("INSERT INTO questions VALUES (18,3,'¿Qué deporte no se juega en cesped?')")
                    db.execSQL("INSERT INTO questions VALUES (19,3,'¿Quién es el único jugador de la historia que ha obtenido 3 veces la Copa del Mundo?')")
                    db.execSQL("INSERT INTO questions VALUES (20,3,'¿Es el lugar en donde descansan los jugadores de baseball?')")
                    //preguntas--arte
                    db.execSQL("INSERT INTO questions VALUES (21,4,'¿Quien es el autor de la mona lisa?')")
                    db.execSQL("INSERT INTO questions VALUES (22,4,'Para Vincent van Gogh también fue muy importante una persona que le brindó apoyo financiero de manera continua y desinteresada, ¿quién fue?')")
                    db.execSQL("INSERT INTO questions VALUES (23,4,'El Juicio Final o El Juicio Universal es un mural realizado al fresco que se encuentra en la Capilla Sixtina y fue pintado por:')")
                    db.execSQL("INSERT INTO questions VALUES (24,4,'¿Quién es el pintor de Las meninas?')")
                    db.execSQL("INSERT INTO questions VALUES (25,4,'Hacer arte nunca ha sido sencillo y esta influyente familia del Renacimiento destacó por ser mecenas de importantes artistas y científicos de su época:')")
                    //preguntas--videojuegos
                    db.execSQL("INSERT INTO questions VALUES (26,5,'¿Qué videojuego fue primero?')")
                    db.execSQL("INSERT INTO questions VALUES (27,5,'¿Cuál es un videojuego de autos de carrera?')")
                    db.execSQL("INSERT INTO questions VALUES (28,5,'¿Cómo se llama el enemigo de Sonic?')")
                    db.execSQL("INSERT INTO questions VALUES (29,5,'¿De que material es la primera espada que recibes en The Legend of Zelda ')")
                    db.execSQL("INSERT INTO questions VALUES (30,5,'¿Cómo se llama la ciudad en la que se desenvuelve Resident Evil?')")

                    //settings
                    //questions_answers
                    db.execSQL("INSERT INTO questions_answers VALUES (1,1,'El Gran Dictador',1)")
                    db.execSQL("INSERT INTO questions_answers VALUES (2,1,'Vida de Perros',0)")
                    db.execSQL("INSERT INTO questions_answers VALUES (3,1,'El gordo y el flaco',0)")
                    db.execSQL("INSERT INTO questions_answers VALUES (4,1,'La clase osciosa',0)")

                    db.execSQL("INSERT INTO questions_answers VALUES (5,2,'Pinocho',0)")
                    db.execSQL("INSERT INTO questions_answers VALUES (6,2,'Zootopia',0)")
                    db.execSQL("INSERT INTO questions_answers VALUES (7,2,'El Rey León',1)")
                    db.execSQL("INSERT INTO questions_answers VALUES (8,2,'Cenicienta',0)")

                    db.execSQL("INSERT INTO questions_answers VALUES (9,3,'Harry Potter',0)")
                    db.execSQL("INSERT INTO questions_answers VALUES (10,3,'El señor de los anillos',0)")
                    db.execSQL("INSERT INTO questions_answers VALUES (11,3,'Star Wars',1)")
                    db.execSQL("INSERT INTO questions_answers VALUES (12,3,'Narnia',0)")

                    db.execSQL("INSERT INTO questions_answers VALUES (13,4,'Forest Gump',1)")
                    db.execSQL("INSERT INTO questions_answers VALUES (14,4,'Milagros inesperados',0)")
                    db.execSQL("INSERT INTO questions_answers VALUES (15,4,'Quisiera ser grande',0)")
                    db.execSQL("INSERT INTO questions_answers VALUES (16,4,'En busca de la felicidad',0)")

                    db.execSQL("INSERT INTO questions_answers VALUES (17,5,'Mi vecino Totoro',0)")
                    db.execSQL("INSERT INTO questions_answers VALUES (18,5,'Recuerdos del ayer',0)")
                    db.execSQL("INSERT INTO questions_answers VALUES (19,5,'El castillo ambulate',1)")
                    db.execSQL("INSERT INTO questions_answers VALUES (20,5,'Mary y la flor de la bruja',0)")

                    db.execSQL("INSERT INTO questions_answers VALUES (21,6,'Bach',0)")
                    db.execSQL("INSERT INTO questions_answers VALUES (22,6,'Beethoven',1)")
                    db.execSQL("INSERT INTO questions_answers VALUES (23,6,'Vivaldi',0)")
                    db.execSQL("INSERT INTO questions_answers VALUES (24,6,'Tchaikovsky',0)")

                    db.execSQL("INSERT INTO questions_answers VALUES (25,7,'Bach',0)")
                    db.execSQL("INSERT INTO questions_answers VALUES (26,7,'Beethoven',0)")
                    db.execSQL("INSERT INTO questions_answers VALUES (27,7,'Vivaldi',1)")
                    db.execSQL("INSERT INTO questions_answers VALUES (28,7,'Tchaikovsky',0)")

                    db.execSQL("INSERT INTO questions_answers VALUES (29,8,'Sibelius',0)")
                    db.execSQL("INSERT INTO questions_answers VALUES (30,8,'Beethoven',0)")
                    db.execSQL("INSERT INTO questions_answers VALUES (31,8,'Schubert',1)")
                    db.execSQL("INSERT INTO questions_answers VALUES (32,8,'Tchaikovsky',0)")

                    db.execSQL("INSERT INTO questions_answers VALUES (33,9,'Paganini',0)")
                    db.execSQL("INSERT INTO questions_answers VALUES (34,9,'Sibelius',0)")
                    db.execSQL("INSERT INTO questions_answers VALUES (35,9,'Mozart',1)")
                    db.execSQL("INSERT INTO questions_answers VALUES (36,9,'Tchaikovsky',0)")

                    db.execSQL("INSERT INTO questions_answers VALUES (37,10,'Brahms',0)")
                    db.execSQL("INSERT INTO questions_answers VALUES (38,10,'Dvořák',0)")
                    db.execSQL("INSERT INTO questions_answers VALUES (39,10,'Beethoven',1)")
                    db.execSQL("INSERT INTO questions_answers VALUES (40,10,'Grieg',0)")

                    db.execSQL("INSERT INTO questions_answers VALUES (41,11,'Carga positiva',0)")
                    db.execSQL("INSERT INTO questions_answers VALUES (42,11,'Carga negativa',1)")
                    db.execSQL("INSERT INTO questions_answers VALUES (43,11,'Carga neutra',0)")
                    db.execSQL("INSERT INTO questions_answers VALUES (44,11,'No tiene cargas',0)")

                    db.execSQL("INSERT INTO questions_answers VALUES (45,12,'Agua',1)")
                    db.execSQL("INSERT INTO questions_answers VALUES (46,12,'Sodio',0)")
                    db.execSQL("INSERT INTO questions_answers VALUES (47,12,'Sal',0)")
                    db.execSQL("INSERT INTO questions_answers VALUES (48,12,'Hidrógeno',0)")

                    db.execSQL("INSERT INTO questions_answers VALUES (49,13,'30',0)")
                    db.execSQL("INSERT INTO questions_answers VALUES (50,13,'80',1)")
                    db.execSQL("INSERT INTO questions_answers VALUES (51,13,'100',0)")
                    db.execSQL("INSERT INTO questions_answers VALUES (52,13,'25',0)")

                    db.execSQL("INSERT INTO questions_answers VALUES (53,14,'psicología',0)")
                    db.execSQL("INSERT INTO questions_answers VALUES (54,14,'filosofía',0)")
                    db.execSQL("INSERT INTO questions_answers VALUES (55,14,'entomología',1)")
                    db.execSQL("INSERT INTO questions_answers VALUES (56,14,'paleontología',0)")

                    db.execSQL("INSERT INTO questions_answers VALUES (57,15,'leucocitos',0)")
                    db.execSQL("INSERT INTO questions_answers VALUES (58,15,'Megacariocitos',0)")
                    db.execSQL("INSERT INTO questions_answers VALUES (59,15,'eritrocitos',1)")
                    db.execSQL("INSERT INTO questions_answers VALUES (60,15,'Hematocitos',0)")

                    db.execSQL("INSERT INTO questions_answers VALUES (61,16,'3',1)")
                    db.execSQL("INSERT INTO questions_answers VALUES (62,16,'2',0)")
                    db.execSQL("INSERT INTO questions_answers VALUES (63,16,'5',0)")
                    db.execSQL("INSERT INTO questions_answers VALUES (64,16,'1',0)")

                    db.execSQL("INSERT INTO questions_answers VALUES (65,17,'2000',0)")
                    db.execSQL("INSERT INTO questions_answers VALUES (66,17,'2018',0)")
                    db.execSQL("INSERT INTO questions_answers VALUES (67,17,'2016',1)")
                    db.execSQL("INSERT INTO questions_answers VALUES (68,17,'2004',0)")

                    db.execSQL("INSERT INTO questions_answers VALUES (69,18,'Baloncesto',1)")
                    db.execSQL("INSERT INTO questions_answers VALUES (70,18,'Futbol Americano',0)")
                    db.execSQL("INSERT INTO questions_answers VALUES (71,18,'Futbol Soccer',0)")
                    db.execSQL("INSERT INTO questions_answers VALUES (72,18,'Rugby',0)")
                    db.execSQL("INSERT INTO questions_answers VALUES (73,19,'Zinedine Zidane',0)")
                    db.execSQL("INSERT INTO questions_answers VALUES (74,19,'Diego Maradona',0)")
                    db.execSQL("INSERT INTO questions_answers VALUES (75,19,'Hugo Sanchez',0)")
                    db.execSQL("INSERT INTO questions_answers VALUES (76,19,'Pelé',1)")
                    db.execSQL("INSERT INTO questions_answers VALUES (77,20,'Primera Base',0)")
                    db.execSQL("INSERT INTO questions_answers VALUES (78,20,'Gradas',0)")
                    db.execSQL("INSERT INTO questions_answers VALUES (79,20,'Dogout',1)")
                    db.execSQL("INSERT INTO questions_answers VALUES (80,20,'Palco',0)")
                    db.execSQL("INSERT INTO questions_answers VALUES (81,21,'Vicent Van Gogh',0)")
                    db.execSQL("INSERT INTO questions_answers VALUES (82,21,'Leonardo Da Vinci',1)")
                    db.execSQL("INSERT INTO questions_answers VALUES (83,21,'Salvador Dalí',0)")
                    db.execSQL("INSERT INTO questions_answers VALUES (84,21,'Miguel Ángel',0)")
                    db.execSQL("INSERT INTO questions_answers VALUES (85,22,'Sus padres',0)")
                    db.execSQL("INSERT INTO questions_answers VALUES (86,22,'Su hermano Cornelius',0)")
                    db.execSQL("INSERT INTO questions_answers VALUES (87,22,'Su hermano Theo',1)")
                    db.execSQL("INSERT INTO questions_answers VALUES (88,22,'Un adinerado de Zundert',0)")
                    db.execSQL("INSERT INTO questions_answers VALUES (89,23,'Pinturicchio',0)")
                    db.execSQL("INSERT INTO questions_answers VALUES (90,23,'Miguel Ángel',1)")
                    db.execSQL("INSERT INTO questions_answers VALUES (91,23,'Pietro Perugino',0)")
                    db.execSQL("INSERT INTO questions_answers VALUES (92,23,'Sandro Botticelli',0)")
                    db.execSQL("INSERT INTO questions_answers VALUES (93,24,'Giotto',0)")
                    db.execSQL("INSERT INTO questions_answers VALUES (94,24,'Kandinsky',0)")
                    db.execSQL("INSERT INTO questions_answers VALUES (95,24,'Caravaggio',0)")
                    db.execSQL("INSERT INTO questions_answers VALUES (96,24,'Diego Velázquez',1)")
                    db.execSQL("INSERT INTO questions_answers VALUES (97,25,'Médici',1)")
                    db.execSQL("INSERT INTO questions_answers VALUES (98,25,'Gozzolli',0)")
                    db.execSQL("INSERT INTO questions_answers VALUES (99,25,'Alighieri',0)")
                    db.execSQL("INSERT INTO questions_answers VALUES (100,25,'Pitti',0)")
                    db.execSQL("INSERT INTO questions_answers VALUES (101,26,'Pong',1)")
                    db.execSQL("INSERT INTO questions_answers VALUES (102,26,'Pac-man',0)")
                    db.execSQL("INSERT INTO questions_answers VALUES (103,26,'Tetris',0)")
                    db.execSQL("INSERT INTO questions_answers VALUES (104,26,'Mario bros',0)")
                    db.execSQL("INSERT INTO questions_answers VALUES (105,27,'Snake',0)")
                    db.execSQL("INSERT INTO questions_answers VALUES (106,27,'Frogger',0)")
                    db.execSQL("INSERT INTO questions_answers VALUES (107,27,'R-type',0)")
                    db.execSQL("INSERT INTO questions_answers VALUES (108,27,'Top gear',1)")
                    db.execSQL("INSERT INTO questions_answers VALUES (109,28,'Robotech',0)")
                    db.execSQL("INSERT INTO questions_answers VALUES (110,28,'Tails',0)")
                    db.execSQL("INSERT INTO questions_answers VALUES (111,28,'Dr. Robotnik',1)")
                    db.execSQL("INSERT INTO questions_answers VALUES (112,28,'Dr. Malo',0)")
                    db.execSQL("INSERT INTO questions_answers VALUES (113,29,'Hueso',0)")
                    db.execSQL("INSERT INTO questions_answers VALUES (114,29,'Acero',0)")
                    db.execSQL("INSERT INTO questions_answers VALUES (115,29,'Madera',1)")
                    db.execSQL("INSERT INTO questions_answers VALUES (116,29,'Hierro',0)")
                    db.execSQL("INSERT INTO questions_answers VALUES (117,30,'Metrópolis',0)")
                    db.execSQL("INSERT INTO questions_answers VALUES (118,30,'Raccoon city',1)")
                    db.execSQL("INSERT INTO questions_answers VALUES (119,30,'New York',0)")
                    db.execSQL("INSERT INTO questions_answers VALUES (120,30,'Resident city',0)")

                }

            }).build()
        return db
    }


}