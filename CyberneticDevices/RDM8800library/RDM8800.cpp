/*
  RDM8800.cpp - Librería para 
  trabajar con un lector NFC tipo RDM8800
  @author Borja Bordel Sánchez
  @date Mayo 2015
*/


#include "Arduino.h"
#include "RDM8800.h"

/*
Constructor
*/
RDM8800::RDM8800(int pinTX, int pinRX) {
	SoftwareSerial puerto(pinRX, pinTX);	
}

/*
Inicia la operación 
del módulo lector
*/
void RDM8800::iniciaLector () {
	puerto.begin(SPEED);
}

/*
Lee el mensaje completo generado por el lector
al acercársele un tag
@return mensaje completo o String vacío si no hay tag
*/
String RDM8800::leeMensajeNFC () {
	if (puerto.available()){
		char buffer [LONGITUD_MENSAJE];
		for (unsigned char j=0;j<LONGITUD_MENSAJE;j++) 
		{
			delay(1);
			buffer[j] = puerto.read();
		}
		return String(buffer);
	}
	return STRING_NULL;
}

/*
Lee el ID de un tag obtenido por el lector
@return ID del tag o String vacío si no hay tag
*/
String RDM8800::leeIdNFC () {
	if (puerto.available()){
		char buffer1 [LONGITUD_MENSAJE];
		char buffer2 [LONGITUD_ID];
		for (unsigned char j=0;j<LONGITUD_MENSAJE;j++) 
		{
			delay(1);
			buffer[j] = puerto.read();
		}
		unsigned char i = 0;
		for (unsigned char j=(LONGITUD_MENSAJE-LONGITUD_ID+1);j<LONGITUD_MENSAJE;j++) 
		{
			buffer2[i] = buffer1[j];
			i++;
		}
		return String(buffer2);
	}
	return STRING_NULL;
}

/*
Método que bloquea la ejecuación
hasta que el lector queda libre
*/
void esperaCambio () {
	char trash;
	while(puerto.available()){
		delay(1);
		trash = puerto.read();
  }
}
