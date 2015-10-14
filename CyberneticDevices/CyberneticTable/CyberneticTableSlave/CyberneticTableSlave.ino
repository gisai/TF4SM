/*
Código para la lectura de tag NFC mediante el chip RDM8800,
un microprocesador Arduino Nano y un módulo Bluetooth HC-06
Módulo esclavo de la balda
@author Borja Bordel Sánchez
@date Mayo 2015
 */

 // Librería para simular un puerto UART en el Arduino
#include <SoftwareSerial.h>

//Librería para controlar el lector RDM8800
#include <RDM8800.h>

// Pines
#define PIN_PERMISSION 17
#define PIN_LED 9
#define PIN_SOLICITATION 16

// Estructuras de datos necesarias

//lector
RDM8800 rdm8800(11,10); // TX, RX
//Conexión con el Arduino gestor
SoftwareSerial mySerialMAIN(3, 4); // RX, TX

//Código inicial que se ejecuta al pulsar RESET
void setup() {
   mySerialMAIN.begin(19200);

   //inicializamos el lector NFC
   rdm8800.iniciaLector();
   
   //colocamos los pines al valor inicial
   pinMode(PIN_LED, OUTPUT);
   pinMode (PIN_SOLICITATION, OUTPUT);
   pinMode (PIN_PERMISSION, INPUT);
}

// Bucle principal
void loop() {
	
    String ID = rdm8800.leeIdNFC();
   
   //Si hay una lectura ...
   if (!ID.equals(STRING_NULL)) {
    	// Encendemos el LED
    	digitalWrite(PIN_LED, HIGH);  
    	//Solicitamos permiso para transmitir hacia 
    	//el Arduino gestor y esperamos respuesta
    	digitalWrite(PIN_SOLICITATION, HIGH);
    	while (!digitalRead(PIN_PERMISSION)) {} 
    	// Enviamos la trama
    	mySerialMAIN.print("BALDA D ");
    	mySerialMAIN.print(ID);
    	// Bajamos el PIN de solicitar permiso
    	digitalWrite(PIN_SOLICITATION, LOW);
    	// Como el RDM 8800 solo garantiza la corrección en la primera
    	// lectura, el resto se descartan
    	rdm8800.esperaCambio ();
    	// Objeto retirado, LED apagado
    	digitalWrite(PIN_LED, LOW);
    	// Repetimos el proceso de escritura para avisar
    	// de la retirada del objeto
    	digitalWrite(PIN_SOLICITATION, HIGH);
    	while (!digitalRead(PIN_PERMISSION)) {} 
    	mySerialMAIN.println  ("BALDA D");
    	digitalWrite(PIN_SOLICITATION, LOW);
   }
}
