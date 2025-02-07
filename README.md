# 🎀 Catálogo de Juguetes - Práctica 6

Este proyecto es una aplicación Android desarrollada en **Kotlin**, enfocada en la organización del catálogo de juguetes del universo **Barbie**. Su objetivo es proporcionar una experiencia amigable para que los niños puedan visualizar y seleccionar sus juguetes favoritos. 🚀

## 📌 Características Principales

### 🏠 Pantalla Principal

- Muestra un listado de juguetes en una sola columna.
- Incluye un **Floating Action Button (FAB)** para acceder a la sección de favoritos.
- Cada juguete se muestra con:
  - 📸 **Imagen**
  - 📏 **Divisor visual**
  - 🏷 **Nombre del producto**
  - 💰 **Precio**
  - ❤️ **Icono de favorito** en la esquina superior derecha.

### ⭐ Funcionalidades

- **Marcar como favorito**: Agrega o elimina un juguete de la lista de favoritos.
- **Eliminar juguete**: Borra un juguete del **RecyclerView** (no persistente, los datos se recargan al reiniciar la app).

### 🎯 Pantalla de Favoritos

- Muestra solo los juguetes marcados como favoritos.
- Utiliza el mismo **Adapter** que la pantalla principal.
- Incluye un **FAB** para volver a la pantalla principal (**sin usar `onBackPressed()`**).

## 🛠 Tecnologías y Librerías Utilizadas

- 📦 **Glide** (Carga de imágenes)
- 🔗 **Retrofit** (Consumo de API REST)
- 📝 **Gson** (Conversión JSON)
- 🔄 **Corrutinas** (Manejo de llamadas asíncronas)
- 🎭 **ViewModel y LiveData** (Arquitectura MVVM)

## 📦 Dependencias

```kotlin
val lifecycleVersion = "2.6.2"
val glideVersion = "4.12.0"
val coroutinesVersion = "1.6.4"
val retrofitVersion = "2.9.0"
val lifecycleruntimektxVersion = "2.5.1"

// ViewModel
implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion")
// Glide
implementation("com.github.bumptech.glide:glide:$glideVersion")
kapt("com.github.bumptech.glide:compiler:$glideVersion")
// Coroutines
implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVersion")
// Retrofit
implementation("com.squareup.retrofit2:retrofit:$retrofitVersion")
// Gson
implementation("com.squareup.retrofit2:converter-gson:$retrofitVersion")
// Lifecycle Runtime
implementation("androidx.lifecycle:lifecycle-runtime-ktx:$lifecycleruntimektxVersion")
```

## 🔗 Fuente de Datos

Los juguetes se obtienen desde la siguiente API: [🔗 Juguetes API](http://juguetes.navelsystems.com/juguetes)

## 📜 Modelo de Datos

```kotlin
data class Joy(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val price: Double
)
```

## 🚀 Configuración Inicial

1. Crear un proyecto Android con **Empty Views Activity**.
2. Habilitar `viewBinding` en `build.gradle`.
3. Agregar `ksp` en la sección de plugins de **Gradle**.
4. Implementar la arquitectura **MVVM** para una correcta organización del código.

## 🌳 Arbol de directorios
### Componentes

- **adapters/**: Contiene los adaptadores para la visualización de los elementos.
  - `JoyAdapter.kt`: Adapta los datos de los juguetes para la vista.

- **models/**: Define los modelos de datos que se utilizan en la aplicación.
  - `Joy.kt`: Representa un juguete con sus propiedades.

- **network/**: Contiene las clases encargadas de manejar las peticiones de red.
  - `ApiService.kt`: Define la interfaz para la API.
  - `RetrofitClient.kt`: Configura Retrofit para las peticiones.

- **viewmodels/**: Define los `ViewModel`s que gestionan los datos de la interfaz.
  - `JoyViewModel.kt`: Administra los datos relacionados con los juguetes.

- **mainModule/**: Contiene las clases principales del funcionamiento de la app.
  - `MainActivity.kt`: La actividad principal de la aplicación.
  - `FavoritosActivity.kt`: Actividad para mostrar los juguetes favoritos.
- **JoyApplication.kt**: La clase principal de la aplicación.

### Recursos

- **res/layout/**: Contiene los archivos XML de la interfaz de usuario.
  - `activity_main.xml`: El layout de la actividad principal.
  - `activity_favoritos.xml`: El layout de la actividad de favoritos.
  - `item_joy.xml`: El layout para cada elemento de la lista de juguetes.

## 📊 Evaluación y Requisitos

- ✅ Implementación completa según las especificaciones.
- ✅ Uso correcto de clases como `Application`, `Services` y `ViewModel`.
- ✅ Anotaciones adecuadas para las llamadas a la API.
- ✅ Correcta organización del código y buenas prácticas en **Kotlin**.

---

Este repositorio forma parte de la asignatura **PMDM** y sigue las directrices establecidas para la entrega de la **Práctica 6**. 🏆
