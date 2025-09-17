-- CODIGO PARA CONSTRUIR LA BASE DE DATOS

CREATE DATABASE lab05_db;
USE lab05_db;

-- Tabla Alumno
CREATE TABLE Alumno (
    idAlumno INT AUTO_INCREMENT PRIMARY KEY,
    nombres VARCHAR(100) NOT NULL,
    apellidos VARCHAR(100) NOT NULL,
    dni VARCHAR(15) UNIQUE NOT NULL,
    email VARCHAR(100)
);

-- Tabla PeriodoAcademico
CREATE TABLE PeriodoAcademico (
    idPeriodo INT AUTO_INCREMENT PRIMARY KEY,
    nombrePeriodo VARCHAR(20) NOT NULL,
    fechaInicio DATE NOT NULL,
    fechaFin DATE NOT NULL,
    estado VARCHAR(20) NOT NULL
);

-- Tabla Matricula
CREATE TABLE Matricula (
    idMatricula INT AUTO_INCREMENT PRIMARY KEY,
    idAlumno INT,
    idPeriodo INT,
    fechaMatricula DATE NOT NULL,
    estado VARCHAR(20) NOT NULL,
    FOREIGN KEY (idAlumno) REFERENCES Alumno(idAlumno),
    FOREIGN KEY (idPeriodo) REFERENCES PeriodoAcademico(idPeriodo)
);

-- Tabla Curso
CREATE TABLE Curso (
    idCurso INT AUTO_INCREMENT PRIMARY KEY,
    nombreCurso VARCHAR(100) NOT NULL,
    creditos INT NOT NULL
);

-- Tabla DetalleMatricula
CREATE TABLE DetalleMatricula (
    idDetalle INT AUTO_INCREMENT PRIMARY KEY,
    idMatricula INT,
    idCurso INT,
    estado VARCHAR(20) NOT NULL,
    FOREIGN KEY (idMatricula) REFERENCES Matricula(idMatricula),
    FOREIGN KEY (idCurso) REFERENCES Curso(idCurso)
);

-- Tabla Evaluacion
CREATE TABLE Evaluacion (
    idEvaluacion INT AUTO_INCREMENT PRIMARY KEY,
    idCurso INT,
    nombre VARCHAR(50) NOT NULL,
    peso DECIMAL(5,2) NOT NULL,
    FOREIGN KEY (idCurso) REFERENCES Curso(idCurso)
);

-- Tabla Nota
CREATE TABLE Nota (
    idNota INT AUTO_INCREMENT PRIMARY KEY,
    idDetalle INT,
    idEvaluacion INT,
    nota DECIMAL(5,2),
    FOREIGN KEY (idDetalle) REFERENCES DetalleMatricula(idDetalle),
    FOREIGN KEY (idEvaluacion) REFERENCES Evaluacion(idEvaluacion)
);

-- Tabla SesionClase
CREATE TABLE SesionClase (
    idSesion INT AUTO_INCREMENT PRIMARY KEY,
    idCurso INT,
    idPeriodo INT,
    fecha DATE NOT NULL,
    tema VARCHAR(200),
    FOREIGN KEY (idCurso) REFERENCES Curso(idCurso),
    FOREIGN KEY (idPeriodo) REFERENCES PeriodoAcademico(idPeriodo)
);

-- Tabla Asistencia
CREATE TABLE Asistencia (
    idAsistencia INT AUTO_INCREMENT PRIMARY KEY,
    idSesion INT,
    idDetalle INT,
    estado VARCHAR(20) NOT NULL,
    FOREIGN KEY (idSesion) REFERENCES SesionClase(idSesion),
    FOREIGN KEY (idDetalle) REFERENCES DetalleMatricula(idDetalle)
);


	