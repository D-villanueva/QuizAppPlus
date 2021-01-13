package com.fiuady.quizappplus

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.fiuady.quizappplus.db.AppDatabase

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
                    db.execSQL("INSERT INTO users(id, nombre, activo) VALUES (0, 'Diego', 0)")
                    db.execSQL("INSERT INTO users(id, nombre, activo) VALUES (1, 'David', 1)")
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
                    db.execSQL("INSERT INTO settings VALUES (0,1,5,1,1,1,1,1,1,1,0,1)")

                    //questions_answers
                    db.execSQL("INSERT INTO questions_answers VALUES (28,'Robotech',0)")
                    db.execSQL("INSERT INTO questions_answers VALUES (28,'Tails',0)")
                    db.execSQL("INSERT INTO questions_answers VALUES (28,'Dr. Robotnik',1)")
                    db.execSQL("INSERT INTO questions_answers VALUES (28,'Dr. Malo',0)")
                }

            }).build()
        return db
    }
}