/*
Código para la lectura de tag NFC mediante el chip RDM8800,
un microprocesador Arduino Nano y un módulo Bluetooth HC-06
Módulo esclavo de la balda con comunicación bidireccional
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

#define PIN_S0 14
#define PIN_S1 15

// Estructuras de datos necesarias

//lector
RDM8800 rdm8800(11,10); // TX, RX
//Conexión con el Arduino gestor
SoftwareSerial mySerialMAIN(3, 4); // RX, TX

// Bufferes
char bufferBT_read;

//Código inicial que se ejecuta al pulsar RESET
void setup() {
	// conexión hacia el maestro
   mySerialMAIN.begin(19200);
   // conexión desde el maestro
   Serial.begin(19200);
   
   //inicializamos el lector NFC
   rdm8800.iniciaLector();
   
   pinMode(PIN_LED, OUTPUT);
   pinMode (PIN_SOLICITATION, OUTPUT);
   pinMode (PIN_PERMISSION, INPUT);
   
      
   pinMode(PIN_S0, OUTPUT);
   digitalWrite(PIN_S0, HIGH);
   pinMode(PIN_S1, OUTPUT);
   digitalWrite(PIN_S1, HIGH);
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
  
  // Si hay dato desde el maestro
  if(Serial.available()) {
    delay(1);
    bufferBT_read = Serial.read();     
	switch (bufferBT_read) {
		case '1':			
            digitalWrite(PIN_S0, HIGH);
			digitalWrite(PIN_S1, LOW);
			break;
		case '2':
      digitalWrite(PIN_S0, LOW);
			digitalWrite(PIN_S1, HIGH);			
			delay(10000);
			digitalWrite(PIN_S0, HIGH);
			digitalWrite(PIN_S1, HIGH);
			break;
		case '3':
			digitalWrite(PIN_S0, LOW);
			digitalWrite(PIN_S1, LOW);
			delay(10000);
			digitalWrite(PIN_S0, HIGH);
			digitalWrite(PIN_S1, HIGH);
			break;
		case '4':
			digitalWrite(PIN_S0, HIGH);
			digitalWrite(PIN_S1, HIGH);
			break;
	}
  }
}
