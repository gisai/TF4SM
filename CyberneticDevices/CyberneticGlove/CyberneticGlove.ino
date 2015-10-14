/*
Código para la lectura de tag NFC mediante el chip RDM8800,
un microprocesador Arduino Nano y un módulo BT HC-06
@author Borja Bordel Sánchez
@date Mayo 2015
 */

 // Librería para simular un puerto UART en el Arduino
#include <SoftwareSerial.h>
//Librearía para controlar el lector RDM8800
#include <RDM8800.h>

// Estructuras de datos necesarias
RDM8800 rdm8800(11,10); // TX, RX
SoftwareSerial mySerialBT(3, 4); // RX, TX

//Código inicial que se ejecuta al pulsar RESET
void setup() {
	//inicializamos el BT
   mySerialBT.begin(19200);
   
   //inicializamos el lector NFC
   rdm8800.iniciaLector();
}

// Bucle principal
void loop() {
		
   String ID = rdm8800.leeIdNFC();
   
   //Si hay una lectura ...
   if (!ID.equals(STRING_NULL)) {
	   mySerialBT.print("GUANTE ");
	   mySerialBT.print(ID);
	   // Como el RDM 8800 solo garantiza la corrección en la primera
	   // lectura, el resto se descartan
	   rdm8800.esperaCambio ();
	   mySerialBT.println("GUANTE");
   }	
}
