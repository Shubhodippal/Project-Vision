#include <SoftwareSerial.h>
#include <Servo.h>
#include "pitches.h"

Servo ultrasonic_servo;
const int servopin = A2;

SoftwareSerial BTSerial(2,3);

#define pwma 5
#define pwmb 6

#define motor1Pin1 9
#define motor1Pin2 13
#define motor2Pin1 7
#define motor2Pin2 4

#define led_red 10
#define led_blue 11
#define led_green 12

#define speaker 9

#define trigPin A5
#define echoPin A4

int state = 0;

int melody1[] = 
{
  NOTE_C4, NOTE_G3, NOTE_G3, NOTE_A3, NOTE_G3, 0, NOTE_B3, NOTE_C4
};

int noteDurations1[] = 
{
  4, 8, 8, 4, 4, 4, 4, 4
};

int melody3[] = {
  NOTE_D5, NOTE_E5, NOTE_F5, NOTE_G5, NOTE_G5, NOTE_F5, NOTE_E5, NOTE_D5, NOTE_D5, NOTE_E5, NOTE_F5, NOTE_G5, NOTE_G5, NOTE_F5, NOTE_E5, NOTE_D5, 
  NOTE_D5, NOTE_C5, NOTE_C5, NOTE_D5, NOTE_E5, NOTE_E5, NOTE_D5, NOTE_D5, NOTE_E5, NOTE_F5, NOTE_G5, NOTE_G5, NOTE_F5, NOTE_E5, NOTE_D5, 
  NOTE_D5, NOTE_E5, NOTE_F5, NOTE_G5, NOTE_G5, NOTE_F5, NOTE_E5, NOTE_D5, NOTE_D5, NOTE_E5, NOTE_F5, NOTE_G5, NOTE_G5, NOTE_F5, NOTE_E5, NOTE_D5,
  NOTE_D5, NOTE_C5, NOTE_C5, NOTE_D5, NOTE_E5, NOTE_E5, NOTE_D5, NOTE_D5, NOTE_E5, NOTE_F5, NOTE_G5, NOTE_G5, NOTE_F5, NOTE_E5, NOTE_D5
};

int noteDurations3[] = {
  4, 4, 4, 4, 4, 4, 2, 2, 4, 4, 4, 4, 4, 4, 2, 2, 4,
  4, 4, 4, 4, 4, 2, 2, 4, 4, 4, 4, 4, 4, 2, 2, 4,
  4, 4, 4, 4, 4, 4, 2, 2, 4, 4, 4, 4, 4, 4, 2, 2,
  4, 4, 4, 4, 4, 2, 2, 4, 4, 4, 4, 4, 4, 2, 2
};

int melody2[] = {
  NOTE_E4, NOTE_E4, NOTE_E4, NOTE_E4, NOTE_E4, NOTE_E4, NOTE_E4, NOTE_G4, NOTE_C4, NOTE_D4, NOTE_E4, NOTE_F4, NOTE_F4, NOTE_F4, NOTE_F4, NOTE_F4,
  NOTE_E4, NOTE_E4, NOTE_E4, NOTE_E4, NOTE_E4, NOTE_D4, NOTE_D4, NOTE_E4, NOTE_D4, NOTE_G4, NOTE_G4, NOTE_G4, NOTE_F4, NOTE_E4, NOTE_D4, NOTE_C4,
  NOTE_C4, NOTE_D4, NOTE_E4, NOTE_F4, NOTE_F4, NOTE_E4, NOTE_E4, NOTE_D4, NOTE_D4, NOTE_E4, NOTE_D4, NOTE_G4, NOTE_G4, NOTE_G4, NOTE_F4, NOTE_E4,
  NOTE_D4, NOTE_C4, NOTE_D4, NOTE_E4, NOTE_F4, NOTE_F4, NOTE_E4, NOTE_E4, NOTE_D4, NOTE_D4, NOTE_E4, NOTE_D4, NOTE_G4, NOTE_G4, NOTE_G4, NOTE_F4, NOTE_E4
};

int noteDurations2[] = {
  4, 4, 4, 4, 4, 4, 2, 2, 4, 4, 4, 4, 4, 4, 4, 4,
  4, 4, 4, 4, 4, 4, 2, 2, 4, 4, 4, 4, 4, 4, 4, 4,
  4, 4, 4, 4, 4, 4, 2, 2, 4, 4, 4, 4, 4, 4, 4, 4,
  4, 4, 4, 4, 4, 4, 2, 2, 4, 4, 4, 4, 4, 4, 4, 4
};

void setup() 
{
  
  pinMode(pwma, OUTPUT);
  pinMode(pwmb, OUTPUT);

  pinMode(motor1Pin1, OUTPUT);
  pinMode(motor1Pin2, OUTPUT);
  pinMode(motor2Pin1, OUTPUT);
  pinMode(motor2Pin2, OUTPUT);

  pinMode(led_red, OUTPUT);
  pinMode(led_blue, OUTPUT);
  pinMode(led_green, OUTPUT);

  pinMode(speaker, OUTPUT);

  pinMode(trigPin, OUTPUT);
  pinMode(echoPin, INPUT);

  ultrasonic_servo.attach(servopin);

  ultrasonic_servo.write(90);
  ultrasonic_servo.write(0);
  ultrasonic_servo.write(180);
  ultrasonic_servo.write(90);

  Serial.begin(9600);
  BTSerial.begin(9600);
}

void loop() 
{
  if (BTSerial.available()) 
  {
    state = BTSerial.read();
  }
  
  Serial.println(state);

  long duration, distance;

  digitalWrite(trigPin, LOW);
  delayMicroseconds(2);
  digitalWrite(trigPin, HIGH);
  delayMicroseconds(10);
  digitalWrite(trigPin, LOW);

  duration = pulseIn(echoPin, HIGH);
  distance = (duration/2) / 29.1;
  
  if(state==0)
  {
    digitalWrite(led_green, HIGH);
    digitalWrite(led_blue, HIGH);
    
  }
  else if(state==48)
  {
    rest();
  }
  else if(state==49)
  {
    if (BTSerial.available() > 0) 
    {
      state = BTSerial.read();
    }
    Serial.println(state);
    ultrasonic_servo.write(100);
    if (distance >= 20)
    {
      forward();
      green_blink_short();
    }
  }
  else if(state==50)
  {
    if (BTSerial.available() > 0) 
    {
      state = BTSerial.read();
    }
    Serial.println(state);
    ultrasonic_servo.write(40);
    if (distance >= 20)
    {
      right();
      digitalWrite(led_blue, HIGH);
    }
  }
  else if(state==51)
  {
    if (BTSerial.available() > 0) 
    {
      state = BTSerial.read();
    }
    Serial.println(state);
    ultrasonic_servo.write(180);
    if (distance >= 20)
    {
      left();
      digitalWrite(led_green, HIGH);
    }
  }
  else if(state==52)
  {
    if (BTSerial.available() > 0) 
    {
      state = BTSerial.read();
    }
    Serial.println(state);
    backward();
    blue_blink_short();
  }
  else if(state==53)
  {
    if (BTSerial.available() > 0) 
    {
      state = BTSerial.read();
    }
    Serial.println(state);

    for(int i = 0 ; i <= 180 ; i++)
    {
      ultrasonic_servo.write(i);
    }

    for(int j = 180 ; j >= 0 ; j--)
    {
      ultrasonic_servo.write(j);
    }

    digitalWrite(motor1Pin1, LOW);
    digitalWrite(motor1Pin2, HIGH);
    digitalWrite(motor2Pin1, HIGH);
    digitalWrite(motor2Pin2, LOW);    
    analogWrite(pwma, 170);
    analogWrite(pwmb, 170);

    digitalWrite(led_red, HIGH);
    digitalWrite(led_green, LOW);
    digitalWrite(led_blue, LOW);
    delay(50);
    digitalWrite(led_red, LOW);
    digitalWrite(led_green, HIGH);
    digitalWrite(led_blue, LOW);
    delay(50);
    digitalWrite(led_red, LOW);
    digitalWrite(led_green, LOW);
    digitalWrite(led_blue, HIGH);
    delay(50);
    melody();

  }
  else if(state==54)
  {
    if (BTSerial.available() > 0) 
    {
      state = BTSerial.read();
    }
    Serial.println(state);
    melodyy();
  }
  else if(state==55)
  {
    if (BTSerial.available() > 0) 
    {
      state = BTSerial.read();
    }
    Serial.println(state);
    melodyyy();
  }
  else
  {
    if (BTSerial.available() > 0) 
    {
      state = BTSerial.read();
    }
    Serial.println(state);
    rest();
  }
}
void backward()
{
  digitalWrite(motor1Pin1, HIGH);
  digitalWrite(motor1Pin2, LOW);
  digitalWrite(motor2Pin1, HIGH);
  digitalWrite(motor2Pin2, LOW);
  analogWrite(pwma, 180);
  analogWrite(pwmb, 180);
  delay(100);
}
void forward()
{
  digitalWrite(motor1Pin1, LOW);
  digitalWrite(motor1Pin2, HIGH);
  digitalWrite(motor2Pin1, LOW);
  digitalWrite(motor2Pin2, HIGH);
  analogWrite(pwma, 200);
  analogWrite(pwmb, 200);
  delay(10);
}
void right()
{
  digitalWrite(motor1Pin1, HIGH);
  digitalWrite(motor1Pin2, LOW);
  digitalWrite(motor2Pin1, LOW);
  digitalWrite(motor2Pin2, HIGH);
  analogWrite(pwma, 200);
  analogWrite(pwmb, 200);
  delay(10);
}
void left()
{
  digitalWrite(motor1Pin1, LOW);
  digitalWrite(motor1Pin2, HIGH);
  digitalWrite(motor2Pin1, HIGH);
  digitalWrite(motor2Pin2, LOW);
  analogWrite(pwma, 200);
  analogWrite(pwmb, 200);
  delay(10);
}
void rest()
{
  digitalWrite(motor1Pin1, LOW);
  digitalWrite(motor1Pin2, LOW);
  digitalWrite(motor2Pin1, LOW);
  digitalWrite(motor2Pin2, LOW);

  digitalWrite(led_red, HIGH);
  digitalWrite(led_green, LOW);
  digitalWrite(led_blue, LOW);

  digitalWrite(speaker, LOW);

}
void green_blink_short()
{
  digitalWrite(led_blue, LOW);   
  digitalWrite(led_red, LOW);   
  digitalWrite(led_green, LOW); 
  delay(75);             
  digitalWrite(led_green, HIGH);
  digitalWrite(led_blue, LOW);   
  digitalWrite(led_red, LOW);   
  delay(75); 
}
void blue_blink_short()
{
  digitalWrite(led_blue, LOW);   
  digitalWrite(led_red, LOW);   
  digitalWrite(led_green, LOW);  
  delay(75);             
  digitalWrite(led_blue, HIGH); 
  digitalWrite(led_red, LOW);   
  digitalWrite(led_green, LOW);  
  delay(75); 
}
void red_blink_short()
{
  digitalWrite(led_blue, LOW);   
  digitalWrite(led_red, LOW);   
  digitalWrite(led_green, LOW);
  delay(75);             
  digitalWrite(led_red, HIGH);
  digitalWrite(led_blue, LOW); 
  digitalWrite(led_green, LOW);  
  delay(75); 
}
void blue_green_blink()
{   
  digitalWrite(led_red, LOW);
  digitalWrite(led_green, LOW);
  digitalWrite(led_blue, HIGH);
  delay(75);
  digitalWrite(led_green, HIGH);
  digitalWrite(led_blue, LOW);   
  digitalWrite(led_red, LOW);
  delay(75);
}
void blue_red_blink()
{
  digitalWrite(led_green, LOW);
  digitalWrite(led_red, LOW);
  digitalWrite(led_blue, HIGH);
  delay(75);
  digitalWrite(led_green, LOW);
  digitalWrite(led_red, HIGH);
  digitalWrite(led_blue, LOW);
  delay(75);
}
void red_green_blink()
{
  digitalWrite(led_blue, LOW);
  digitalWrite(led_green, LOW);
  digitalWrite(led_red, HIGH);
  delay(75);
  digitalWrite(led_blue, LOW);
  digitalWrite(led_green, HIGH);
  digitalWrite(led_red, LOW);
  delay(75);
}
void red_blue_green_blink()
{
  digitalWrite(led_green, LOW);
  digitalWrite(led_blue, LOW);
  digitalWrite(led_red, HIGH);
  delay(75);
  digitalWrite(led_green, LOW);
  digitalWrite(led_blue, HIGH);
  digitalWrite(led_red, LOW);
  delay(75);
  digitalWrite(led_green, HIGH);
  digitalWrite(led_blue, LOW);
  digitalWrite(led_red, LOW);
  delay(75);
}
void melody()
{
  for (int thisNote = 0; thisNote < 8; thisNote++) 
  {
    int noteDuration = 1000 / noteDurations1[thisNote];
    tone(8, melody1[thisNote], noteDuration);

    int pauseBetweenNotes = noteDuration * 1.30;
    delay(pauseBetweenNotes);
    
    noTone(8);
  }
}
void melodyy()
{
  for (int i = 0; i < 64; i++) 
  {
    int noteDuration = 1000 / noteDurations2[i];
    tone(8, melody2[i], noteDuration);
    int pauseBetweenNotes = noteDuration * 1.30;
    delay(pauseBetweenNotes);
    noTone(8);
  }
}
void melodyyy()
{
  for (int i = 0; i < 64; i++) 
  {
    int noteDuration = 1000 / noteDurations3[i];
    tone(8, melody3[i], noteDuration);
    int pauseBetweenNotes = noteDuration * 1.30;
    delay(pauseBetweenNotes);
    noTone(8);
  }
}
