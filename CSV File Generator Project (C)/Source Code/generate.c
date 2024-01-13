/*
 * generate.c
 *
 *  Created on: Oct 13, 2023
 *      Author: omarzaari
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "generate.h"

void handleFirstName(FILE *srcFile, char *nameBuffer) {
    if (fgets(nameBuffer, 100, srcFile) != NULL) {
        nameBuffer[strcspn(nameBuffer, "\n")] = 0;
    }
}

void handleLastName(FILE *srcFile, char *nameBuffer) {
    if (fgets(nameBuffer, 100, srcFile) != NULL) {
        nameBuffer[strcspn(nameBuffer, "\n")] = 0;
    }
}

void printHeaderToFile(int columnOrder[], int columnIndex, FILE *Nfile) {
    for (int j = 0; j < columnIndex; j++) {
        if (j != 0) {
            fprintf(Nfile, ", ");
        }
        switch (columnOrder[j]) {
            case 1:
                fprintf(Nfile, "User ID");
                break;
            case 2:
                fprintf(Nfile, "First name");
                break;
            case 3:
                fprintf(Nfile, "Last name");
                break;
            case 4:
                fprintf(Nfile, "Country");
                break;
            case 5:
                fprintf(Nfile, "Phone Number");
                break;
            case 6:
                fprintf(Nfile, "Email Address");
                break;
            case 7:
                fprintf(Nfile, "SIN");
                break;
            case 8:
                fprintf(Nfile, "Password");
                break;
            default:
                break;
        }
    }
}

void handleCountry(FILE *srcFile, char *countryBuffer) {
    if (fgets(countryBuffer, 100, srcFile) != NULL) {
        countryBuffer[strcspn(countryBuffer, "\n")] = 0;
    }
}

void handlePhoneNumber(FILE *Nfile, char *phoneNumber) {
    int indexCodes[10] = {398, 270, 925, 867, 209, 429, 908, 997, 444, 219};
    int randomIndex = rand() % 10;
    int randomTail = rand() % 10000;
    sprintf(phoneNumber, "%d-%04d", indexCodes[randomIndex], randomTail);
}

void handleSIN(char *sinNumber) {
    sprintf(sinNumber, "%09d", rand() % 1000000000);
}

void handleEmail(const char *firstName, const char *lastName, FILE *emailSuffixFile, char *email) {
    char suffix[100];
    int lines = 0;
    while (fgets(suffix, sizeof(suffix), emailSuffixFile)) {
        lines++;
    }

    int randomLine = rand() % lines;

    rewind(emailSuffixFile);
    for (int i = 0; i <= randomLine; i++) {
        fgets(suffix, sizeof(suffix), emailSuffixFile);
    }

    suffix[strcspn(suffix, "\n")] = 0;
    sprintf(email, "%c%s@%s", firstName[0], lastName, suffix);
}

void handleId(char *ID, int currentRow){
	sprintf(ID, "%d", currentRow + 1);
}

void handlePassword(char *password) {
    const char *chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    for (int i = 0; i < 8; i++) {
        password[i] = chars[rand() % 62];
    }
    password[8] = 0;
}

void determineColumnOrder(const char* columns, int columnOrder[], int* columnIndex) {
    char tempColumns[20];
    strcpy(tempColumns, columns);
    char *token = strtok(tempColumns, ",");
    *columnIndex = 0;

    while (token != NULL) {
        int column = atoi(token);
        columnOrder[*columnIndex] = column;
        (*columnIndex)++;
        token = strtok(NULL, ",");
    }
}
