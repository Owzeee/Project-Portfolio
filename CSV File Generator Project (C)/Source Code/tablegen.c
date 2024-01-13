/*
 ============================================================================
 Name        : assignment1.c
 Author      : Omar zaari
 Version     :
 Copyright   : Your copyright notice
 Description : Hello World in C, Ansi-style
 ============================================================================
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>
#include "io.h"
#include "tablegen.h"
#include "generate.h"
#include "sort.h"

int main(void) {
    char continueChoice;
	do{
    system("clear");
	TableGenMenu();
    int choice = 0;
    scanf("%d", &choice);

    switch (choice) {
        case 1: {
    	    char columns[20];
        	int isValidInput = 1;
        	do {
                system("clear");
        	    ColumnOptions();
        	    scanf("%19s", columns);

        	    isValidInput = 1;

        	    char tempColumns[20];
        	    strcpy(tempColumns, columns);
        	    char *token = strtok(tempColumns, ",");

        	    while (token != NULL) {
        	        int column = atoi(token);
        	        if (column < 1 || column > 8) {
        	            printf("Invalid column input. Try again ");
        	            isValidInput = 0;
        	            break;
        	        }
        	        token = strtok(NULL, ",");
        	    }
        	} while (!isValidInput);
        	int columnOrder[20] = {0};
        	int columnIndex = 0;
        	determineColumnOrder(columns, columnOrder, &columnIndex);

            printf("Enter row count (1 < n < 1M): ");

            int rowCount = 0;
            scanf("%d", &rowCount);

            printf("Enter output file name (no suffix): ");
            char fileName[100];
            scanf("%s", fileName);

            printf("\nSummary of properties: \n"
                   " Columns: %s\n"
                   " Row count: %d\n"
                   " File Name: %s\n\n"
                   "Table written successfull to %s.cvs",
                   columns, rowCount, fileName, fileName);

            char newFileNameWithExt[100];
            snprintf(newFileNameWithExt, sizeof(newFileNameWithExt), "%s.csv", fileName);

            FILE *Nfile = fopen(newFileNameWithExt, "w");
                if (Nfile == NULL) {
                    perror("Error opening new file");
                    return 1;
                }
                printHeaderToFile(columnOrder, columnIndex, Nfile);
                fprintf(Nfile, "\n");
                FILE *firstNameFile = fopen("first_names.txt", "r");
                if (firstNameFile == NULL) {
                    perror("Error opening first_names.txt");
                    fclose(Nfile);
                    return 1;
                }

                FILE *lastNameFile = fopen("last_names.txt", "r");
                if (lastNameFile == NULL) {
                    perror("Error opening last_names.txt");
                    fclose(Nfile);
                    fclose(firstNameFile);
                    return 1;
                }
                FILE *countryFile = fopen("countries.txt", "r");
                FILE *emailSuffixFile = fopen("email_suffixes.txt", "r");
                if (countryFile == NULL || emailSuffixFile == NULL) {
                     perror("Error opening necessary files");
                     	 return 1;
                }

                sorting(rowCount, columns, Nfile, firstNameFile, lastNameFile, countryFile, emailSuffixFile);
                fclose(Nfile);
                fclose(firstNameFile);
                fclose(lastNameFile);
                fclose(countryFile);
                fclose(emailSuffixFile);
                printf("\n\nPress 'c' or 'C' to continue ");
                scanf(" %c", &continueChoice);
            }
        break;
        case 2:
        	printf("Thank you for using Tablegen!\n");
            return EXIT_SUCCESS;
    	}
	}while(continueChoice == 'c' || continueChoice == 'C');

    return EXIT_SUCCESS;
}
