# 🧪 Amazon Test Automation Framework

Framework de automatización de pruebas funcionales para **Amazon.com**, diseñado para validar flujos críticos de e-commerce utilizando **Selenium + Cucumber (BDD)** bajo el patrón **Page Object Model (POM)**.

---

## 📚 Tabla de Contenidos

- [Descripción General](#-descripción-general)
- [Tecnologías Utilizadas](#-tecnologías-utilizadas)
- [Estructura del Proyecto](#-estructura-del-proyecto)
- [Flujo de Trabajo](#-flujo-de-trabajo)
- [Archivos Feature (Gherkin)](#-archivos-feature-gherkin)
- [Step Definitions](#-step-definitions)
- [Page Objects](#-page-objects)
- [Clases de Soporte](#-clases-de-soporte)
- [Configuración y Ejecución](#-configuración-y-ejecución)
- [Reportes Generados](#-reportes-generados)
- [Buenas Prácticas Implementadas](#-buenas-prácticas-implementadas)

---

## 📖 Descripción General

Este framework automatiza pruebas funcionales en Amazon.com para validar flujos clave de compra:

- 🔎 Búsqueda de productos  
- 🎛 Aplicación de filtros  
- ✅ Validación de resultados  
- 🛒 Selección de productos  
- 📦 Verificación de detalles y opciones de compra  

El framework combina:

- **Page Object Model (POM)** → Separación clara entre lógica y UI
- **BDD con Cucumber** → Escenarios legibles para negocio y QA
- **TestNG** → Validaciones y ejecución flexible

---

## 🛠 Tecnologías Utilizadas

| Tecnología | Versión | Propósito |
|------------|---------|-----------|
| Java | 8+ | Lenguaje principal |
| Selenium WebDriver | 4.x | Automatización del navegador |
| Cucumber | 7.x | Framework BDD (Gherkin) |
| TestNG | 7.x | Asserts y ejecución |
| WebDriverManager | 5.x | Gestión automática de drivers |
| Maven | 3.x | Gestión de dependencias |

---

## 📁 Estructura del Proyecto

```text
proyecto/
├── src/
│   ├── main/
│   │   └── java/
│   │       └── pages/
│   │           ├── pasosBasicos.java
│   │           ├── pasosEspeciales.java
│   │           ├── validaciones.java
│   │           ├── paginaPrincipal.java
│   │           ├── resultados.java
│   │           └── filtros.java
│   └── test/
│       ├── java/
│       │   ├── steps/
│       │   │   └── amazon.java
│       │   └── runner/
│       │       ├── testAmazon.java
│       │       └── hooks.java
│       └── resources/
│           └── features/
│               └── Amazon.feature
└── target/ (reportes generados)
