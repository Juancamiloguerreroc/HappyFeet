# 🐾 Sistema de Gestión Integral para Veterinaria "Happy Feet"

## 📋 Descripción del Proyecto

Happy Feet es un sistema integral de gestión veterinaria desarrollado para resolver los desafíos operativos de clínicas veterinarias modernas. El sistema centraliza la gestión de pacientes, historiales médicos, inventario, facturación y actividades especiales, eliminando la dependencia de fichas de papel y hojas de cálculo dispersas.

### Contexto del Problema

La clínica veterinaria "Happy Feet" enfrentaba serios desafíos con su sistema manual:
- **Historiales clínicos incompletos** que ponían en riesgo la salud de las mascotas
- **Fugas de inventario** sin control en tiempo real
- **Agendamiento caótico** con citas solapadas y largos tiempos de espera
- **Facturación manual** lenta y propensa a errores

Este sistema digitaliza y automatiza todos estos procesos, garantizando eficiencia operativa y mejorando la calidad del servicio.

---

## 🛠️ Tecnologías Utilizadas

- **Lenguaje:** Java 17 (JDK 17)
- **Base de Datos:** MySQL 8.0
- **Conectividad:** JDBC (Java Database Connectivity)
- **Gestor de Dependencias:** Maven
- **Control de Versiones:** Git/GitHub
- **Arquitectura:** MVC (Model-View-Controller)
- **Principios de Diseño:** SOLID
- **Paradigma:** Programación Orientada a Objetos + Programación Funcional (Streams, Lambdas)

---

## ✨ Funcionalidades Implementadas

### 📌 Módulo 1: Gestión de Pacientes
- Registro completo de mascotas (datos básicos, médicos, identificación)
- Gestión de dueños con información de contacto y emergencia
- Asociación mascota-dueño con posibilidad de transferencia
- Historial médico detallado por mascota

### 🏥 Módulo 2: Servicios Médicos y Citas
- Sistema de agenda para programación de citas
- Registro de consultas médicas con diagnósticos y recomendaciones
- Gestión de procedimientos especiales (cirugías, tratamientos complejos)
- Prescripción de medicamentos con deducción automática de inventario
- Seguimiento postoperatorio y próximos controles

### 📦 Módulo 3: Inventario y Farmacia
- Control de stock de medicamentos, vacunas e insumos médicos
- Alertas automáticas de stock bajo y productos próximos a vencer
- Restricción de uso de productos vencidos
- Gestión de proveedores para reabastecimiento
- Trazabilidad completa con registro de movimientos de inventario

### 💰 Módulo 4: Facturación y Reportes
- Generación automática de facturas detalladas en texto plano
- Cálculo automático de subtotales, impuestos y descuentos
- Reportes gerenciales:
  - Servicios más solicitados
  - Desempeño del equipo veterinario
  - Estado de inventario
  - Análisis de facturación por período

### 🎯 Módulo 5: Actividades Especiales
- **Días de Adopción:** Registro de mascotas disponibles y generación de contratos
- **Jornadas de Vacunación:** Registro masivo optimizado para campañas
- **Club de Mascotas Frecuentes:** Sistema de puntos y beneficios para clientes leales

### 🔒 Características Técnicas Destacadas
- Manejo robusto de excepciones con logging en archivo
- Validaciones de integridad referencial
- Uso de Streams y Lambdas para operaciones eficientes
- Implementación de patrones de diseño (Singleton, Factory, DAO, Strategy, Observer, entre otros)
- Arquitectura MVC estricta con separación de responsabilidades

---

## 🗄️ Modelo de Base de Datos

El sistema utiliza una base de datos relacional MySQL con 30+ tablas organizadas en módulos:

### Tablas Principales por Módulo:

**Gestión de Pacientes:**
- `duenos`, `mascotas`, `especies`, `razas`

**Servicios Médicos:**
- `veterinarios`, `citas`, `consultas_medicas`, `procedimientos_especiales`, `historial_medico`

**Inventario:**
- `inventario`, `proveedores`, `prescripciones`, `movimientos_inventario`

**Facturación:**
- `facturas`, `items_factura`, `servicios`

**Actividades Especiales:**
- `mascotas_adopcion`, `adopciones`, `jornadas_vacunacion`, `club_mascotas`

### Diagrama ER
El diagrama completo de la base de datos se encuentra en la carpeta `/database/`. Las relaciones principales incluyen:
- Una mascota pertenece a un dueño
- Las consultas y procedimientos se asocian a mascotas y veterinarios
- Las prescripciones deducen automáticamente del inventario
- Las facturas consolidan servicios y productos

---

## 🚀 Instrucciones de Instalación y Ejecución

### Requisitos Previos

Asegúrate de tener instalado:
- **JDK 17** o superior ([Descargar aquí](https://www.oracle.com/java/technologies/downloads/))
- **Apache Maven 3.8+** ([Descargar aquí](https://maven.apache.org/download.cgi))
- **MySQL Server 8.0+** ([Descargar aquí](https://dev.mysql.com/downloads/mysql/))
- **Git** ([Descargar aquí](https://git-scm.com/downloads))

### Paso 1: Clonar el Repositorio

```bash
git clone https://github.com/Juancamiloguerreroc/HappyFeet.git
cd HappyFeet
```

### Paso 2: Configurar la Base de Datos

1. **Inicia MySQL Server** y accede a la consola:
```bash
mysql -u root -p
```

2. **Ejecuta el script de creación de esquema:**
```sql
source database/schema.sql
```

3. **Ejecuta el script de datos iniciales:**
```sql
source database/data.sql
```

### Paso 3: Configurar la Conexión a la Base de Datos

Crea y edita el archivo `src/main/java/com/happyfeet/.env` y actualiza las credenciales:

```java
DB_URL = "jdbc:mysql://localhost:3306/happy_feet_veterinaria";
DB_USER = "tu_usuario";
DB_PASSWORD = "tu_contraseña";
```

**Nota:** Por seguridad, considera usar variables de entorno o archivos de configuración externos para las credenciales.

### Paso 4: Compilar el Proyecto

```bash
mvn clean compile
```

### Paso 5: Ejecutar la Aplicación

```bash
mvn exec:java -Dexec.mainClass="com.happyfeet.Main"
```

O si prefieres ejecutar desde el JAR:

```bash
mvn clean package
java -jar target/HappyFeet-1.0-SNAPSHOT.jar
```

---

## 📖 Guía de Uso

Al iniciar la aplicación, se mostrará un menú principal en consola con las siguientes opciones:

```
╔════════════════════════════════════════╗
║   VETERINARIA HAPPY FEET - SISTEMA    ║
╚════════════════════════════════════════╝

1. Gestionar Citas
2. Gestionar Mascotas
3. Gestionar Veterinarios
4. Gestionar Dueños
5. Gestionar Servicios
6. Gestionar Procedimientos Especiales
7. Gestionar Inventario
8. Gestionar Adopciones
9. Gestionar Jornadas de Vacunación
10. Gestionar Club de Mascotas
11. Gestionar Facturas
0. Salir

### Flujo de Trabajo Típico:

1. **Registrar un nuevo dueño y mascota** (Módulo 1)
2. **Agendar una cita** para la mascota (Módulo 2)
3. **Realizar consulta médica** y prescribir tratamiento (Módulo 2)
4. **El sistema deduce automáticamente** los medicamentos del inventario (Módulo 3)
5. **Generar factura** al finalizar la atención (Módulo 4)
6. **Acumular puntos** en el club de mascotas frecuentes (Módulo 5)

### Ejemplos de Operaciones:

- **Buscar historial médico:** Ingresa el ID de la mascota para ver todas sus consultas y procedimientos
- **Verificar stock:** Consulta en tiempo real las existencias y productos por vencer
- **Generar reportes:** Obtén estadísticas de servicios, veterinarios y finanzas por período

---

## 🏗️ Arquitectura del Sistema

### Estructura de Paquetes (MVC)

```
com.happyfeet/
│
├── controller/          # Controladores que gestionan el flujo
│   ├── AdopcionController.java
│   ├── CitaController.java
│   ├── ClubMascotasController.java
│   └── ...
│
├── model/              # Modelos de datos y lógica de negocio
│   ├── entities/       # Entidades que mapean tablas de BD
│   │   ├── Mascota.java
│   │   ├── Dueno.java
│   │   ├── Factura.java
│   │   └── ...
│   ├── enums/          # Enumeraciones
│   └── utils/          # Utilidades del modelo
│
├── repository/         # Capa de acceso a datos (DAO)
│   ├── MascotaRepository.java
│   ├── DuenoRepository.java
│   └── ...
│
├── service/           # Lógica de negocio
│   ├── InventarioService.java
│   ├── FacturacionService.java
│   └── ...
│
├── view/              # Interfaz de usuario (consola)
│   ├── MascotaView.java
│   ├── CitaView.java
│   └── ...
│
├── ConexionDB/        # Gestión de conexión a BD
│   └── DatabaseConnection.java
│
└── Main.java          # Punto de entrada
```

### Patrones de Diseño Implementados

1. **Singleton:** Para la conexión a la base de datos
2. **DAO (Data Access Object):** Capa de repositorios
3. **Factory:** Para la creación de objetos complejos
4. **Strategy:** Para diferentes tipos de reportes
5. **Observer:** Para alertas de inventario
6. **Template Method:** Para flujos de facturación

### Principios SOLID Aplicados

- **S (Single Responsibility):** Cada clase tiene una única responsabilidad
- **O (Open/Closed):** Extensible sin modificar código existente
- **L (Liskov Substitution):** Jerarquías de herencia correctas
- **I (Interface Segregation):** Interfaces específicas por funcionalidad
- **D (Dependency Inversion):** Dependencias hacia abstracciones

---

---

## 📝 Logging y Manejo de Errores

El sistema implementa un robusto manejo de excepciones:

- Todas las excepciones son capturadas y registradas
- Los logs se guardan en `application.log` en la raíz del proyecto
- Formato de log: `[TIMESTAMP] [NIVEL] [CLASE] - Mensaje`

Ejemplo:
```
[2024-11-04 10:30:45] [ERROR] [InventarioService] - Stock insuficiente para producto ID: 15
[2024-11-04 10:31:12] [INFO] [FacturaController] - Factura generada exitosamente: F-00123
```

---

## 🧪 Pruebas

El sistema incluye datos de prueba precargados en `database/data.sql`:

- 50+ dueños registrados
- 100+ mascotas de diferentes especies
- 20+ veterinarios
- 200+ productos en inventario
- Historial médico completo para demostración

Para ejecutar pruebas:
```bash
mvn test
```
