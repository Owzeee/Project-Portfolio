/*
 * sort.c
 *
 *  Created on: Oct 13, 2023
 *      Author: omarzaari
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "sort.h"
#include "generate.h"

		void sorting(int rowCount, char columns[], FILE *Nfile, FILE *firstNameFile, FILE *lastNameFile, FILE *countryFile, FILE *emailSuffixFile){
			for (int i = 0; i < rowCount; i++) {
                    char firstName[100] = "";
                    char lastName[100] = "";
                    char country[100] = "";
                    char phoneNumber[15] = "";
                    char email[200] = "";
                    char sinNumber[10] = "";
                    char password[9] = "";
                    int columnOrder[20] = {0};
                    int columnIndex = 0;
                    char Id[10] = "";

                    char tempColumns[20];
                    strcpy(tempColumns, columns);
                    char *token = strtok(tempColumns, ",");

                    while (token != NULL) {
                        int column = atoi(token);
                        columnOrder[columnIndex++] = column;

                        switch (column) {
                            case 1:
                                handleId(Id, i);
                                break;
                            case 2:
                                handleFirstName(firstNameFile, firstName);
                                break;
                            case 3:
                                handleLastName(lastNameFile, lastName);
                                break;
                            case 4:
                                handleCountry(countryFile, country);
                                break;
                            case 5:
                                handlePhoneNumber(NULL, phoneNumber);
                                break;
                            case 6:
                                handleEmail(firstName, lastName, emailSuffixFile, email);
                                break;
                            case 7:
                                handleSIN(sinNumber);
                                break;
                            case 8:
                                handlePassword(password);
                            	break;
                            default:
                                printf("Invalid column input. Try again: ");

                        }
                        token = strtok(NULL, ",");
                    }
                    for (int j = 0; j < columnIndex; j++) {
                        if (j != 0) {
                            fprintf(Nfile, ", ");
                        }
                        switch (columnOrder[j]) {
                            case 1:
                                fprintf(Nfile, "%s", Id);
                                break;
                            case 2:
                                fprintf(Nfile, "%s", firstName);
                                break;
                            case 3:
                                fprintf(Nfile, "%s", lastName);
                                break;
                            case 4:
                                fprintf(Nfile, "%s", country);
                                break;
                            case 5:
                                fprintf(Nfile, "%s", phoneNumber);
                                break;
                            case 6:
                                fprintf(Nfile, "%s", email);
                                break;
                            case 7:
                                fprintf(Nfile, "%s", sinNumber);
                                break;
                            case 8:
                                fprintf(Nfile, "%s", password);
                                break;
                            default:
                                printf("Invalid column input : %d\n", columnOrder[j]);
                                break;
                        }
                    }
                    fprintf(Nfile, "\n");
                }
		}
