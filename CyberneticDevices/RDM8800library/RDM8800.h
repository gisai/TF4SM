/*
  RDM8800.h - Librería para 
  trabajar con un lector NFC tipo RDM8800
  @author Borja Bordel Sánchez
  @date Mayo 2015
*/

#ifndef RDM8800_h
#define RDM8800_h

#include "Arduino.h"
#include <SoftwareSerial.h>

//Constantes propias del chip RDM8800

#define LONGITUD_MENSAJE 85
#define LONGITUD_ID 15
#define SPEED 9600

#define STRING_NULL String("")

class RDM8800 {
	public:
		RDM8800 (int pinTX, int pinRX);  //constructor
		void iniciaLector (); //inicia la operación con el lector
		String leeMensajeNFC (); //lee el mensaje completo creado por el lector
		String leeIdNFC (); //lee sólo el ID del tag
		void esperaCambio ();  //bloquea la ejecuación hasta que se libere un lector ocupado
	private:
		SoftwareSerial puerto;
}

#endif
