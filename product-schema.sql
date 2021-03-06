/*
CREATE TABLE Producto(
id_producto INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
nombre VARCHAR(24) NOT NULL,
precio DOUBLE NOT NULL,
disponible BOOLEAN NOT NULL,

CONSTRAINT primary_key PRIMARY KEY(id_producto)
);
*/

-- INSERT INTO Producto(nombre, precio, disponible) VALUES('MacBook Pro 2012', 2000000, true);
SELECT * FROM Producto


CREATE TABLE Proveedor(
id_proveedor INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
nombre VARCHAR(60) NOT NULL,
CONSTRAINT PK_Proveedor PRIMARY KEY(id_proveedor)
);

CREATE TABLE Producto(
id_producto INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
nombre VARCHAR(24) NOT NULL,
precio DOUBLE NOT NULL,
disponible BOOLEAN NOT NULL,
id_proveedor INTEGER NOT NULL,

CONSTRAINT PK_Producto PRIMARY KEY(id_producto),
CONSTRAINT FK_Proveedor_Producto FOREIGN KEY (id_proveedor) REFERENCES Proveedor(id_proveedor)
);