/*
 * io.h
 *
 *  Created on: Oct 13, 2023
 *      Author: omarzaari
 */

#ifndef GENERATE_H_
#define GENERATE_H_

void printHeaderToFile(int columnOrder[], int columnIndex, FILE *Nfile);
void handleCountry(FILE *srcFile, char *countryBuffer);
void handleEmail(const char *firstName, const char *lastName, FILE *emailSuffixFile, char *email);
void handleFirstName(FILE *firstNameFile, char *firstName);
void handleLastName(FILE *lastNameFile, char *lastName);
void handleSIN(char *sinNumber);
void handleId(char *ID, int currentRow);
void handlePassword(char *password);
void handlePhoneNumber(FILE *Nfile, char *phoneNumber);
void determineColumnOrder(const char* columns, int columnOrder[], int* columnIndex);
#endif /* GENERATE_H_ */
