# Crear el archivo README.md usando pypandoc (formato requerido)

import pypandoc

readme_content = """
# 🧪 Amazon Test Automation Framework

Framework de automatización funcional para **Amazon.com**, desarrollado con **Selenium WebDriver + Cucumber (BDD)** bajo el patrón **Page Object Model (POM)** para garantizar mantenibilidad, reutilización y estabilidad.

---

## 📖 Descripción General

El framework automatiza flujos críticos de e-commerce:

- 🔎 Búsqueda de productos  
- 🎛 Aplicación de filtros  
- ✅ Validación de resultados  
- 🛒 Selección de productos  
- 📦 Verificación de detalles de compra  

Diseñado para pruebas funcionales robustas y escalables.

---

## 🛠 Tecnologías Utilizadas

- Java 8+
- Selenium WebDriver 4.x
- Cucumber 7.x
- TestNG 7.x
- WebDriverManager 5.x
- Maven 3.x

---

## 📁 Estructura del Proyecto

src/
└── java/pages/
    │       ├── pasosBasicos.java
    │       ├── pasosEspeciales.java
    │       ├── validaciones.java
    │       ├── paginaPrincipal.java
    │       ├── resultados.java
    │       └── filtros.java
    ├── runner/
    │       ├── hooks.java
    │       └── testAmazon.java
    ├── steps/
    │       └── amazon.java
    └── resources/features
                    └──Amazon.feature

---

## 🔄 Flujo de Ejecución

1. Runner ejecuta Cucumber.
2. Features definen escenarios en Gherkin.
3. Step Definitions ejecutan acciones.
4. Page Objects interactúan con Selenium.
5. Hooks capturan evidencias.
6. Se generan reportes automáticos.

---

## 🚀 Configuración y Ejecución

### Requisitos

- JDK 8+
- Maven
- Google Chrome

### Ejecutar pruebas

mvn clean test
mvn test -Dcucumber.filter.tags="@Busqueda"

---

## 📊 Reportes

Generados automáticamente en:

- target/cucumber-reports.html
- target/cucumber-reports/cucumber.json
- target/cucumber-reports/cucumber.xml
- target/cucumber-timeline/

---

## ⭐ Buenas Prácticas

- Page Object Model (POM)
- Reutilización de métodos base
- WebDriverWait para estabilidad
- SoftAssert para validaciones múltiples
- Manejo de excepciones para elementos opcionales
- Uso de JavaScript Executor (scroll y elementos ocultos)
- Captura automática de screenshots en hooks
- Manejo de ventanas, modales y toaster messages

---

## 🔍 Particularidades del Framework

- Selección inteligente de productos priorizando “Más vendidos”.
- Clasificación automática de resultados (patrocinados, orgánicos, tendencias).
- Manejo de listas ocultas mediante JavaScript.
- Validación por capas (imagen, título, precio y valoración).

---

## 📝 Notas Importantes

- WebDriver compartido entre todas las páginas.
- Navegador en modo incógnito.
- Ocultamiento de navigator.webdriver para reducir bloqueos.
- Preferencia por SoftAssert para reportes completos.

---

## 👨‍💻 Autor

Luis Miguel Abril Sanguña  
QA Automation Engineer
"""

file_path = "/mnt/data/README.md"

pypandoc.convert_text(
    readme_content,
    'md',
    format='md',
    outputfile=file_path,
    extra_args=['--standalone']
)

file_path
