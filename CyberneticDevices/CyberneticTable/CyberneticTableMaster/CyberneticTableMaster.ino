/*
Código para la lectura de tag NFC mediante el chip RDM8800,
se integra en el Arduino gestor de los lectores que van incluidos 
en la balda
@author Borja Bordel Sánchez
@date Mayo 2015
 */

 // Librería para simular un puerto UART en el Arduino
#include <SoftwareSerial.h>

// Tamaño máximo del mensaje de llegada
#define LONG_TRAMA 45

// Pines del protocolo de linea
#define PIN_SOLICITATION_UART1 12
#define PIN_PERMISSION_UART1 13
#define PIN_SOLICITATION_UART2 14
#define PIN_PERMISSION_UART2 15
#define PIN_SOLICITATION_UART3 16
#define PIN_PERMISSION_UART3 17
#define PIN_SOLICITATION_UART4 18
#define PIN_PERMISSION_UART4 19

// Estructuras de datos necesarias
SoftwareSerial uartBT(10, 11); // RX, TX

SoftwareSerial uart1(2,3); // RX, TX
SoftwareSerial uart2(4,5); // RX, TX
SoftwareSerial uart3(6,7); // RX, TX
SoftwareSerial uart4(8,9); // RX, TX

//Buffer de lectura
char buffer_read [LONG_TRAMA];

//Código inicial que se ejecuta al pulsar RESET
void setup() {
   uartBT.begin(19200);

   // set the data rate for the SoftwareSerial port
   uart1.begin(19200);
   uart2.begin(19200);
   uart3.begin(19200);
   uart4.begin(19200);
   
   // Inicialización de los pines
   pinMode(PIN_SOLICITATION_UART1, INPUT);
   pinMode(PIN_PERMISSION_UART1, OUTPUT);
   digitalWrite(PIN_PERMISSION_UART1, LOW);
   pinMode(PIN_SOLICITATION_UART2, INPUT);
   pinMode(PIN_PERMISSION_UART2, OUTPUT);
   digitalWrite(PIN_PERMISSION_UART2, LOW);
   pinMode(PIN_SOLICITATION_UART3, INPUT);
   pinMode(PIN_PERMISSION_UART3, OUTPUT);
   digitalWrite(PIN_PERMISSION_UART3, LOW);
   pinMode(PIN_SOLICITATION_UART4, INPUT);
   pinMode(PIN_PERMISSION_UART4, OUTPUT);
   digitalWrite(PIN_PERMISSION_UART4, LOW);  
}

// Bucle principal
void loop() {
  
  // Revisamos si hay solicitud de trasmisitir del
  // primer arduino
  if (digitalRead(PIN_SOLICITATION_UART1)) {
	// Si la hay, le damos permiso de transmision
    digitalWrite(PIN_PERMISSION_UART1, HIGH);
	// Escuchamos
    uart1.listen();
    //Lectura mientras haya datos o el Arduino mantenga la
	// solicitud de escritura
    unsigned char j=0;	
    while (uart1.available() || digitalRead(PIN_SOLICITATION_UART1)){
      delay(1);
      buffer_read[j] = uart1.read();      
      if (buffer_read[j] != -1) {j++;}    
    }
	// Terminada la transmision se retira el permiso
	// y transmitimos por BT
    digitalWrite(PIN_PERMISSION_UART1, LOW);
	
	for (unsigned char i = 0 ; i < j ; i++) {
		uartBT.write(buffer_read[i]);
	}
  }
  
  // Pasamos a revisr si quiere transmitir el segundo Arduino...
  if (digitalRead(PIN_SOLICITATION_UART2)){
    digitalWrite(PIN_PERMISSION_UART2, HIGH);
    uart2.listen();
    //Si hay una lectura ...
    unsigned char j=0;	
    while (uart2.available() || digitalRead(PIN_SOLICITATION_UART2)){
      delay(1);
      buffer_read[j] = uart2.read();      
      if (buffer_read[j] != -1) {j++;}    
    }
    digitalWrite(PIN_PERMISSION_UART2, LOW);
    for (unsigned char i = 0 ; i < j ; i++) {
		uartBT.write(buffer_read[i]);
	}  
  }
  
  // El tercero...
  if (digitalRead(PIN_SOLICITATION_UART3)){
    digitalWrite(PIN_PERMISSION_UART3, HIGH);
    uart3.listen();
    //Si hay una lectura ...
    unsigned char j=0;	
    while (uart3.available() || digitalRead(PIN_SOLICITATION_UART3)){
      delay(1);
      buffer_read[j] = uart3.read();      
      if (buffer_read[j] != -1) {j++;}    
    }
    digitalWrite(PIN_PERMISSION_UART3, LOW);
	
    for (unsigned char i = 0 ; i < j ; i++) {
		uartBT.write(buffer_read[i]);
	}  
  }
  
  // El cuarto...
  if (digitalRead(PIN_SOLICITATION_UART4)){
    digitalWrite(PIN_PERMISSION_UART4, HIGH);
    uart4.listen();
    //Si hay una lectura ...
    unsigned char j=0;	
    while (uart4.available() || digitalRead(PIN_SOLICITATION_UART4)){
      delay(1);
      buffer_read[j] = uart4.read();      
      if (buffer_read[j] != -1) {j++;}    
    }
    digitalWrite(PIN_PERMISSION_UART4, LOW);
	
    for (unsigned char i = 0 ; i < j ; i++) {
		uartBT.write(buffer_read[i]);
	}   
  }
 
}
