/*
 * io.c
 *
 *  Created on: Oct 13, 2023
 *      Author: omarzaari
 */

#include <stdio.h>
#include <stdlib.h>


FILE* openFile(const char* fileName, const char* mode) {
    FILE* file = fopen(fileName, mode);
    if (file == NULL) {
        perror("Error opening file");
    }
    return file;
}

void closeFile(FILE* file) {
    if (file != NULL) {
        fclose(file);
    }
}

size_t readFile(FILE* file, char* buffer, size_t size) {
    return fread(buffer, 1, size, file);
}

char* readLine(FILE* file, char* buffer, size_t size) {
    return fgets(buffer, size, file);
}

