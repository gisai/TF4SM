# TF4SM
Implementación del Traceability Framework For Small Manufacturing (TF4SM).
Release 3.1

El proyecto cuenta con dos grandes partes diferenciadas: varios archivos Arduino para su ejecución en las plataformas cibernéticas del sistema (guante y mesa cibernéticos); y una aplicación Java para la monitorización del sistema y creación y verificación de workflows.

Respecto de los archivos Arduino, estos incluyen a su vez dos elementos: una librería C++ para el control de los lectores NFC que debe ser previamente instaladas en el entorno Arduino; y un sketck .ino por cada dispositivo (que deben ser compilados y cargados en los dispositivos mediante el entorno Arduino).

Se provee también una rchivo TXT con un ejemplo de descripción de inventario, donde los identificadores NFC de los productos deben listarse en una columna justificada a la izquierda. Se añade, igualmente, un archivo TXT describiendo el lenguaje con el que se pueden definir workflows en el sistema.

